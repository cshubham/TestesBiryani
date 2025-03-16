package com.dg.testesbiryani

import android.app.ActivityManager
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.os.Looper
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.work.Constraints
import androidx.work.Data
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.Operation.State
import androidx.work.Operation.State.SUCCESS
import androidx.work.WorkManager
import androidx.work.WorkRequest
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import com.bumptech.glide.Glide
import com.dg.testesbiryani.check.Checker
import com.dg.testesbiryani.ui.theme.checkLog1
import com.dg.testesbiryani.uipost.MainActivity5
import com.dg.testesbiryani.vm.TestesViewModel
import com.dg.testesbiryani.work.MyWorker
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.time.Duration
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.ThreadPoolExecutor
import kotlin.reflect.KMutableProperty
import kotlin.reflect.full.declaredMemberFunctions
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.full.memberProperties
import kotlin.reflect.jvm.isAccessible

private const val URL = "https://image.lexica.art/full_webp/ad52d483-c709-4808-b629-737e034adb74"
private const val URL_L = "https://images.pexels.com/photos/210186/pexels-photo-210186.jpeg?cs=srgb&dl=dawn-landscape-nature-210186.jpg&fm=jpg"
private const val URL_LB = "https://i.ytimg.com/vi/c7oV1T2j5mc/maxresdefault.jpg"

@AndroidEntryPoint
class MainActivity : ComponentActivity(), LifecycleObserver {

    val testesViewModel : TestesViewModel by viewModels()
    val threadPool = Executors.newFixedThreadPool(1).asCoroutineDispatcher()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        checkLog1()

        val l = listOf(listOf(1,2,3), listOf(5,6))
        l.flatMap { it }
//        listOf(1,2).flatMap { it }
        Log.d("shch",
            "onCreate: ${savedInstanceState?.getString("TESTES")}")
//        lifecycleScope.launch {
//            val bitmap = getBitmap(this@MainActivity, URL_LB)
//            Log.d("shch", "just bitmap " + bitmap)
//            bitmap ?: return@launch
//            Log.d("shch", "onCreate: bitmap " + bitmap.height + " " + bitmap.width + " aspect " + bitmap.height.toFloat() / bitmap.width)
//
//        }
        val flow = MutableSharedFlow<Int>(2)
        val ld = MutableLiveData(0)
        lifecycleScope.launch {
            for(i in 0 .. 9) {
                delay(2000)
                if(i % 2 == 0) {
                flow.emit(i -1)
                } else {
                    flow.emit(i)
                }
                ld.value = i
            }

//            val intent = Intent().apply {
//                component = ComponentName("com.glance.surfaceart", "com.glance.surfaceart.feature.routingModule.presentation.activities.SurfaceArtActivity")
//            }
//            startActivity(intent)
        }

        lifecycleScope.launch {
            //repeatOnLifecycle(Lifecycle.State.STARTED) {
                flow.distinctUntilChanged().collect {
                    Log.d("shch", " flow collection " + it)
                }
                Log.d("shch", " flow collection complete " )
            //}
        }

        lifecycleScope.launch {
                ld.observe(this@MainActivity) {
                   // Log.d("shch", " live data collection " + it)
                }
        }
        Handler(HandlerThread("dsd").also { it.start() }.looper).post {
            Log.d("shch", "handler post " + Thread.currentThread().name)
        }
        setContent {
            val printVal = remember { mutableStateOf<String?>("hell") }
            val uiState = testesViewModel.stateFlow.collectAsStateWithLifecycle()
            val users = testesViewModel.pagedUsers.collectAsLazyPagingItems()
            val emitFlowCheck = testesViewModel.emitCheckStateFlow.collectAsStateWithLifecycle()
            val sharedFlow = testesViewModel.sharedFlow.collectAsStateWithLifecycle(0)


            //stash1()
            Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                Spacer(modifier = Modifier.padding(innerPadding))
                Log.d("shch", " padding  " + innerPadding)
                //StashCompose2(innerPadding)
                //StashCompose3(innerPadding)
                val list = uiState.value.list

               // Log.d("shch", " list size check recomp " + list.size)
//                Column(modifier = Modifier
//                    .padding(32.dp)
//                    .fillMaxWidth()
//                    .height(300.dp)
//                    .background(Color.LightGray)
//                ) {
//                    LazyColumn {
////                        items(list.size) {
////                            Log.d("shch", " list size check inside " + list[it].name)
////                            Text(text = list[it].name)
////                        }
//
//                        items(items = list, key = { it.id }) {
//                            Log.d("shch", " list size check inside " + it.name)
//                            Text(text = it.name)
//                        }
//                    }
//                }

                val ctrScope = rememberCoroutineScope()

                LaunchedEffect(key1 = Unit) {
                    lifecycleScope.launch {

                    }
                    ctrScope.launch {

                    }
                }

                Box(modifier = Modifier.fillMaxSize()) {
                    Column(verticalArrangement = Arrangement.Center) {
                      //  Log.d("shch", " emit flow check " + sharedFlow.value.toString())
                    Text(text = "sharedFlow.value.toString()", modifier = Modifier
                        .padding(100.dp)
                        .background(Color.Cyan)
                        .clickable {
                            //   delay(1000)
                            val intent = Intent(this@MainActivity, MainActivity5::class.java)
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                            intent.putExtra("sh", "ch")
                            startActivity(intent)
                            //testesViewModel.updateStateList()
                        }
                        .padding(50.dp)

                    )

                        LazyColumn(
                            modifier = Modifier
                                .background(Color.LightGray)
                                .height(100.dp)
                        ) {
                            items(users.itemCount) {
                                Text(text = users[it]?.userName ?: "haha", modifier = Modifier.padding(10.dp))
                            }
                        }

                        Spacer(modifier = Modifier.size(20.dp))

                        LazyColumn(
                            modifier = Modifier
                                .background(Color.LightGray)
                                .height(100.dp)
                        ) {
                            items(users.itemCount) {
                                Text(text = users[it]?.userName ?: "haha", modifier = Modifier.padding(10.dp))
                            }
                        }

                        val pagerState = rememberPagerState { users.itemCount }
//                        HorizontalPager(pagerState, modifier = Modifier.fillMaxWidth(), contentPadding = PaddingValues(12.dp)) {
//                            Log.d("shch", "page tracking " + it)
//                            Column(
//                                modifier = Modifier
//                                    .background(Color.Yellow)
//                                    .fillMaxWidth()
//                                    .padding(12.dp)
//                            ) {
//                                Text(text = it.toString())
//                                Text(text = users[it].toString())
//                            }
//                        }

                        Spacer(modifier = Modifier.size(20.dp))

                        LazyColumn(
                            modifier = Modifier
                                .background(Color.LightGray)
                                .height(100.dp)
                        ) {
                            items(uiState.value.list.size) {
                                Text(text = uiState.value.list[it].name , modifier = Modifier.padding(10.dp))
                            }
                        }

                    }
                }

            }

        }
        Log.d("shch1", "test generics " + printSame(2) + " " + printType(2))

        lifecycleScope.launch(Job()) {
//            delay(1000)
//            val intent = Intent(this@MainActivity, MainActivity2::class.java)
//            startActivity(intent)
        }

        lifecycleScope.launch {
            Log.d("shch", "Check Thread " + Thread.currentThread().name)
            delay(1)
            launch {
                Log.d("shch", "Check Thread inside " + Thread.currentThread().name)
            }
        }

        //bindService()

        val exceptionHandler = CoroutineExceptionHandler { _, ex ->
            Log.e("shch", "Caught $ex")
        }
        CoroutineScope(Job()).launch {
            Log.d("shch", "Check Thread3 " + Thread.currentThread().name)
        }

        GlobalScope.launch {
            Log.d("shch", "Check Thread4 " + Thread.currentThread().name)
        }

        kotlin.runCatching {
            val bundleRes = this.contentResolver.call(Uri.parse("content://com.glance.surfaceart.sa.provider"),"check1",  "check", Bundle())
            Log.d("shch", " content resolver check " + bundleRes?.getString("sa"))
        }.onFailure {
            Log.d("shch", " content resolver error " + it)
        }

        val meta = packageManager.getApplicationInfo(
            packageName,
            PackageManager.GET_META_DATA
        )

        Log.d("shch", "meta check " + meta.metaData.getString("camera_provider_metadata"))

        val activityManager = getSystemService(ACTIVITY_SERVICE) as ActivityManager

        val appTasks = activityManager.appTasks
        for (appTask in appTasks) {
            val taskInfo = appTask.taskInfo
            Log.d("TaskInfo1", "Task ID: ${taskInfo.taskId}")
            Log.d("TaskInfo1", "Base Intent: ${taskInfo.baseIntent}")
            Log.d("TaskInfo1", "Top Activity: ${taskInfo.topActivity}")
            Log.d("TaskInfo1", "Task Description: ${taskInfo.description}")
        }

        LocalBroadcastManager.getInstance(this).sendBroadcast(Intent())
        checkInline ({
            Log.d("shch", "inline lambda 1 $it" + l.flatten())
            //return
        }, {
            Log.d("shch", "inline lambda 2 $it" + l.flatten())
return@checkInline
        }, {
            Log.d("shch", "inline lambda 3 $it" + l.flatten())
        })

        testesViewModel.getAll()

        val listw = listOf("ram", " rahim", "singh")

        listw.forEach {
            val workRequest: WorkRequest =
                OneTimeWorkRequestBuilder<MyWorker>()
                    .setInitialDelay(Duration.ofSeconds(1))
                    .setInputData(Data.Builder().putString("key", it).build())
                    .setConstraints(Constraints.Builder().setRequiresBatteryNotLow(true).build())
                    .build()
            val r = WorkManager.getInstance(this).enqueue(workRequest)

            //WorkManager.getInstance(this).cancelWorkById(workRequest.id)
        }

//        IntentService
// cal
        val list = listOf(1,2,3,4,5,6)
        val group = list
            .partition { it % 2 == 0 }
            .first
            .map {
                it.toDouble()
            }

        println(list)
        Log.d("shch", " list parted " + group)


        exploreReflection()

        lifecycleScope.launch(Dispatchers.Main) {
            Log.d("shch", " lifecycle scope launch 1 ")
            launch(Dispatchers.Main.immediate) {
                Log.d("shch", " lifecycle scope launch 2 ")
            }
            Log.d("shch", " lifecycle scope launch 3 ")
        }

        val checker = Checker()
        checker.checkMe().run {
            null
        } ?: run {

        }

        val s = "ss koi lopa"
        Log.d("shch", "splitted " + s.split(" "))
        val sRev = s.split(" ").map { it.reversed() }
        Log.d("shch", "splitted " + sRev)


        val intentServiceIntent = Intent(this, MyIntentService::class.java)
        startService(intentServiceIntent)


        val service = Intent(this, MyService::class.java)
        lifecycleScope.launch(Dispatchers.IO) {
            Log.d("shch"," service delay")
           // val notification = getNotification(context = this@MainActivity)
            startForegroundService(service)
        }

        /*Handler(Looper.getMainLooper()).post {
            Thread.sleep(10000)
            Log.d("shch", "handler post " + Thread.currentThread().name)
        }*/


        lifecycleScope.launch {
            delay(1000)
            Log.d("shch", "testblock lifecycleScope " + Thread.currentThread().name)
        }

//        runBlocking {
//            Log.d("shch", "testblock run blocking pre" + Thread.currentThread().name)
//            delay(4000)
//            Log.d("shch", "testblock run blocking " + Thread.currentThread().name)
//        }

        lifecycleScope.launch {
            delay(1000)
            Log.d("shch", "testblock2 lifecycleScope " + Thread.currentThread().name)
        }


//        lifecycleScope.launch(threadPool) {
//            Log.d("shch", "testblock_pool_1 lifecycleScope pre " + Thread.currentThread().name)
////            delay(2000)
//            testesViewModel.heavyWork()
//            Log.d("shch", "testblock_pool_1 lifecycleScope post " + Thread.currentThread().name)
//
//        }

        lifecycleScope.launch(threadPool) {
            Log.d("shch", "testblock_pool_1 lifecycleScope pre2 " + Thread.currentThread().name)
            delay(1000)
            Log.d("shch", "testblock_pool_1 lifecycleScope post2 " + Thread.currentThread().name)
        }

//        val job = lifecycleScope.launch {
//            while (true && this.isActive) {
//                Log.d("shch", "loop")
//            }
//        }
//        job.cancel()
        GlobalScope.launch {
            checkTransaction()
        }
    }

    val coroutineScope = CoroutineScope(Dispatchers.IO)

    @OptIn(InternalCoroutinesApi::class)
    private suspend fun checkTransaction() {
        Log.d("shch", "transaction check pre")
        val expHandler = CoroutineExceptionHandler { o, t ->
            Log.d("shch", "transaction check exception handled parent" + t.message)
        }

        val parentJob = coroutineScope.launch(expHandler) { //
            val job1 = launch {
                try {
                    delay(2000)
                    if (isActive)
                        Log.d("shch", "transaction check 1")
                } catch (e: CancellationException) {
                    Log.d("shch", "transaction check 1 cance.l " + e.message)
                }
            }

//            val expHandler = CoroutineExceptionHandler { o, t ->
//                Log.d("shch", " exception handled " + t.message)
//            }
            val job2 = launch(Dispatchers.IO) {
                delay(1000)
                Log.d("shch", "transaction check 2")
                throw IllegalArgumentException("shch rocks!")
//                throw CancellationException("shch rocks!")
            }

            val job3 = launch {
                delay(2000)
                Log.d("shch", "transaction check 3")
            }.invokeOnCompletion {
                Log.d("shch", "transaction check 3 " + it)
            }

//            job2.cancel()
            Log.d("shch", "transaction check end")

        }
        delay(100)
    //    parentJob.cancel()
        Log.d("shch", "transaction check post")
    }

    override fun onStart() {
        super.onStart()
        Log.d("shch", "lifecycle main act1 onstart")
    }

    private fun exploreReflection() {
        val userClass = User::class
        println("Properties:")
        userClass.declaredMemberProperties.also {
            Log.d("shch", " reflection feilds " + it)
        }

        println("\nMethods:")
        userClass.declaredMemberFunctions.also {
            Log.d("shch", " reflection methods " + it)
        }

        val user = User(name = "Shubham", age = 23)

        Log.d("shch", " print user actual " + user.toString())

        userClass.memberProperties.forEach { prop ->
            Log.d("shch", " property check " + prop.name + " " + (prop is KMutableProperty<*>))
            if(prop.name == "name") {
                prop.isAccessible = true
            } else if(prop.name == "age") {
                prop.isAccessible = true
                (prop as KMutableProperty<*>).setter.call(user, 24)
            }
        }

        Log.d("shch", " print user modified " + user.age)

        //Glide.with(this).load(URL_LB)


    }


    @Composable
    private fun stash1() {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            Greeting(
                name = "Android",
                modifier = Modifier.padding(innerPadding)
            )



            Box(
                modifier = Modifier
                    .size(300.dp)
                    .background(Color.Red)
            ) {
                AsyncImage(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Cyan)
                        .padding(10.dp)
                        .clipToBounds(),
    //                        model = URL_LB,
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(getBitmap(LocalContext.current, URL_L)) // Pass the Bitmap here
                        .build(),
                    contentScale = ContentScale.Crop,
                    contentDescription = null,
                    onState = {
                        Log.d("shch", "onState: $it")
                    }
                )
            }

        }

        Box(
            modifier = Modifier
                .size(100.dp)
                .padding(10.dp)
                .clipToBounds() // Try toggling this on and off
                .background(Color.Gray)
        ) {
            Box(
                modifier = Modifier
                    .size(150.dp)
                    .offset(
                        x = (-25).dp,
                        y = (-25).dp
                    ) // Move part of the child outside the parent's boundaries
                    .background(Color.Red)
            )
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.d("shch", "onRestoreInstanceState: ${savedInstanceState.getString("TESTES")}")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d("shch", "onSaveInstanceState: ${outState.getString("TESTES")}")
        outState.putString("TESTES", "BIRYANI")
    }

    override fun onStop() {
        super.onStop()
        Log.d("shch", "lifecycle onStop: ")
    }

    override fun onPause() {
        super.onPause()
        Log.d("shch", "lifecycle onPause ")

    }
    override fun onResume() {
        super.onResume()
        Log.d("shch", "lifecycle resumed")
        lifecycleScope.launch {
            Log.d("shch", "test heavyLoadCheck 1 " + Thread.currentThread().name)
            withContext(Dispatchers.IO) {
             //   Thread.sleep(10000)
            }
            Log.d("shch", "test heavyLoadCheck 2 " + Thread.currentThread().name)
        }

    }

    override fun onRestart() {
        super.onRestart()
        Log.d("shch", "lifecycle restart")

    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("shch", "lifecycle act1 destroy")
    }

     inline fun checkInline(
         lambda: (String) -> Unit,
         crossinline lambda2: (String) -> Unit,
         lambda3: (String) -> Unit,
     ) {
         Log.d("shch", "inline 1" )
         lambda("shch_o")
         Log.d("shch", "inline 2")
         lambda2("shch_payal")
         Log.d("shch", "inline 3" )
         lambda3("shch_shining")
         Log.d("shch", "inline 4" )
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

