package com.dg.testesbiryani.vm

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.dg.testesbiryani.ProLogger
import com.dg.testesbiryani.check.CheckRepo
import com.dg.testesbiryani.check.CoolClass
import com.dg.testesbiryani.check.SomeImpl
import com.dg.testesbiryani.check.SomeInterface
import com.dg.testesbiryani.model.TestesUiState
import com.dg.testesbiryani.model.User
import com.dg.testesbiryani.room.repo.TestesEntity
import com.dg.testesbiryani.room.repo.TestesRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.yield
import java.lang.Math.pow
import javax.inject.Inject
import kotlin.system.measureTimeMillis

@HiltViewModel
class TestesViewModel @Inject constructor(
    private val testesRepo: TestesRepo,
    private val someInterface: Set<@JvmSuppressWildcards SomeInterface>,
    private val proLogger: ProLogger,
    private val checkRepo: CheckRepo,
    private val flowStream : StateFlow<Boolean>,
    private val ioDispatcher: CoroutineDispatcher,
    private val coolClass: CoolClass
): ViewModel() {
    private lateinit var flow : Flow<Int>
    private val _stateFlow = MutableStateFlow(TestesUiState())
    val stateFlow: StateFlow<TestesUiState> = _stateFlow

    val pagedUsers: Flow<PagingData<com.dg.testesbiryani.room.repo.User>> =
        testesRepo.getPagedUsers()
            .cachedIn(viewModelScope)


    val sharedFlow = MutableSharedFlow<Int>()
    val emitCheckStateFlow = MutableStateFlow(0)

    val livedata = MutableLiveData<Int>()

    //Log.d()
//    user.id

    init {
        viewModelScope.launch {
            flowStream.collectLatest {
                Log.d("shch", "worker flow res in vm " + it)
            }
        }
//        viewModelScope.launch {
//            val job1 = checkCounterUpdates2()
//            //val job2 = checkCounterUpdates2()
//        }
//        viewModelScope.launch {
//            val job1 = checkCounterUpdates()
//            val job2 = checkCounterUpdates()
//            job1.join()
//            job2.join()
//            Log.d("shch", "counter latest value " + _stateFlow.value.counter)
//        }
        someInterface.forEach {
            it.some()
            val s = "ss"
            s.substring(1, 1)
            Log.d("shch", " init called vm testes " + it.hashCode() + " " + proLogger.hashCode())
        }
        updateEntries()
        //fetchData()
        val user = User(1,"s", "on")
        //val (a, b) = User(1, "s")
        Log.d("shch", "data class demystified " + user)

        viewModelScope.launch(Dispatchers.Default.limitedParallelism(2)) {

        }
        flow = flow {
            for (i in 1..3) {
                emit(i)
            }
        }.flowOn(Dispatchers.IO)

        livedata.postValue(2)
        //checklaunchInRunBlocking()
        viewModelScope.launch {
            checkStructuredConcurrency()
        }
    }

    private fun checkCounterUpdates() = viewModelScope.launch(ioDispatcher) {
            repeat(10000) {
                _stateFlow.value.copy(
                    counter = _stateFlow.value.counter + 1
                ).also { _stateFlow.value = it }
            }
    }

    private fun checkCounterUpdates2() = viewModelScope.launch {
        repeat(100) { index ->
            delay(1)
            Log.d("shch", " index updated " + index)
            sharedFlow.emit(index)
        }
    }

    private suspend fun checkStructuredConcurrency() {
        yield()
        Log.d("shch", " launch in structure entry")
        val job = viewModelScope.launch {
            Log.d("shch", " launch in structure starts 1 " + Thread.currentThread().name)
            delay(1000)
            Log.d("shch", " launch in structure starts 2 " + Thread.currentThread().name)
            launch() {
                Log.d("shch", " launch in structure starts 3 " + Thread.currentThread().name)
                delay(1000)
                Log.d("shch", " launch in structure starts 4 " + Thread.currentThread().name)
            }.join()

            val i = coroutineScope { 3 }
            Log.d("shch", " launch in structure starts 5 " + Thread.currentThread().name)
        }
        //job.cancel()
        Log.d("shch", " launch in structure exit")
    }

    private fun checklaunchInRunBlocking() {
        Log.d("shch", " launch in run blocking pre " + Thread.currentThread().name)
        runBlocking {
            Log.d("shch", " launch in run blocking starts 1" + Thread.currentThread().name)
            viewModelScope.launch {
                Log.d("shch", " launch in run blocking starts 2" + Thread.currentThread().name)
                delay(2000)
                Log.d("shch", " launch in run blocking starts 3")
            }
            Log.d("shch", " launch in run blocking starts 4")
        }
        Log.d("shch", " launch in run blocking post ")
    }

    val hotFlow = testesRepo.getDao().getAll()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    private fun updateEntries() = viewModelScope.launch(Dispatchers.IO) {
        val entry1 = User(2, "payal", "two")
        val l = listOf(
            User(1, "shubham", "one"),
            entry1,
            User(3, "soho", "three")
        )

        _stateFlow.value = _stateFlow.value.copy(
            list = l
        )

        delay(2000)


        val ll = _stateFlow.value.list.toMutableList()
     //   Log.d("shch","list size check " + " " + _stateFlow.value.hashCode() + " " + _stateFlow.hashCode() + " " + l.joinToString() + " " + _stateFlow.value.list.joinToString())

        ll[1] = ll[1].copy(className = "payalx")

        for(i in 4 .. 100) {
            ll.add(User(i, "shch_lazy_col_$i", "class$i"))
        }

        _stateFlow.value = _stateFlow.value.copy(
            list = ll.toList()
        )


        handleUserDaoEntry()
    }

    private suspend fun handleUserDaoEntry() {
        testesRepo.inserUserDaoTable()
        delay(1000)
        testesRepo.getUsersWithOrders().let {
            Log.d("shch", " test user dao " + it.joinToString(";;"))
        }
    }

    fun getAll() = viewModelScope.launch(Dispatchers.IO) {
//        testesRepo.getAll().collect {
//            it.forEach {
//                Log.d("shch", " emtries " + it.user)
//            }
//
//        }

        hotFlow.collect {
            it.forEach {
                Log.d("shch", " emtries " + it.user)
            }

        }
    }

    fun fetchData() = viewModelScope.launch{
        testesRepo.fetchData()
    }

    fun heavyWork() {
        Log.d("shch", "testblock_pool heavy work starts " + Thread.currentThread().name)
        val t = measureTimeMillis {
            repeat(10000) {
                repeat(1000) {
                    pow(100.0, 100.0)
                }
            }
        }
        Log.d("shch", "testblock_pool heavy work starts " + t + " " + Thread.currentThread().name)
    }

    fun updateStateList() {
        val l = _stateFlow.value.list.toMutableList()
        l[0] = User(10, "shch2","d")
        _stateFlow.value = _stateFlow.value.copy(list =  l)
    }

}