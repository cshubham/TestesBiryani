package com.dg.testesbiryani.room.repo

import android.provider.Settings.Global
import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.dg.testesbiryani.retrofit.ApiService
import com.dg.testesbiryani.room.TestesDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import java.util.Scanner
import javax.inject.Inject
import kotlin.coroutines.coroutineContext


class TestesRepo @Inject constructor(
    private val testesDatabase: TestesDatabase,
    private val retrofit: Retrofit
) {
    private val testesDao = testesDatabase.testesDao()
     val userDao = testesDatabase.userDao()
    private val apiService by lazy { retrofit.create(ApiService::class.java)}

    init {
        testesDatabase.testesDao()
    }

    init {


    }
    fun insert(testesTable: TestesEntity) {
        testesDao.insert(testesTable)
    }

    fun getAll(): Flow<List<TestesEntity>> {
        return testesDao.getAll()
    }

    fun getDao(): TestesDao {
        return testesDao
    }

    suspend fun inserUserDaoTable() {
        userDao.apply {
            insertUser(User("1", "shubham"))
            insertOrder(Order("1", "1", 100.0))
            insertOrder(Order("2", "1", 200.0))

            insertUser(User("2", "payal"))
            insertOrder(Order("3", "2", 150.0))
            insertOrder(Order("4", "2", 250.0))

            for(i in 3 .. 190) {
                insertUser(User(i.toString(), "shchp$i"))
            }

//            delay(4000)
//
//            for(i in 11 .. 23) {
//                insertUser(User(i.toString(), "shchp$i"))
//            }

        }
    }

     fun getPagedUsers(): Flow<PagingData<User>> {
         Log.d("shch"," check paged users " + Thread.currentThread().name)
        return Pager(
            config = PagingConfig(
                pageSize = 5,
                prefetchDistance = 4
            ),
            pagingSourceFactory = {
                userDao.getUsers().also {
                Log.d("shch"," check paged users mid " + it + " " + Thread.currentThread().name + it)
                }
            }
        ).flow.also {
            Log.d("shch"," check paged users 2 " + it)
        }
    }

    suspend fun getUsersWithOrders(): List<UserWithOrders> {
        //coroutineContext.ensureActive()
        return userDao.getUsersWithOrders()
    }

    suspend fun fetchData() {
        val data = apiService.getData()
        Log.d("shch", " fetched data " + data)

    }
}