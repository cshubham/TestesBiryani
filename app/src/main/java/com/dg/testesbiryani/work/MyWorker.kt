package com.dg.testesbiryani.work

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.dg.testesbiryani.ProLogger
import dagger.assisted.Assisted
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.pow
import kotlin.system.measureTimeMillis
import kotlin.time.measureTime



//@HiltWorker
class MyWorker @Inject constructor(
    val context: Context,
    val params: WorkerParameters,
) : Worker(context, params) {
    @Inject
    lateinit var proLogger: ProLogger
    @Inject
    lateinit var flow : StateFlow<Boolean>

    init {
        val entryPoint = EntryPointAccessors.fromApplication(context, EntryPointInterface::class.java)
        proLogger = entryPoint.getProLogger()
        flow = entryPoint.getFlow()
    }

    override fun doWork(): Result {
        val data = inputData.getString("key")
        proLogger.LOG(" worker started prolog " + Thread.currentThread().name + " " + data)
        var res : Double = 1.0
        var time = measureTimeMillis {
            repeat(10000000) {
                res = 2.0.pow(10.0)
            }
        }
//        time/=1000
        GlobalScope.launch {
            (flow as MutableStateFlow).emit(true)
        }
        proLogger.LOG(" worker done prolog $res $time" )


        return Result.success(Data.Builder().putBoolean("worked", true).build())
    }
}


@EntryPoint
@InstallIn(SingletonComponent::class)
interface EntryPointInterface {
    fun getProLogger(): ProLogger
    fun getFlow() : StateFlow<Boolean>
}