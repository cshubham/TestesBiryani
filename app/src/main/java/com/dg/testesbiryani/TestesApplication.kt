package com.dg.testesbiryani

import android.app.Application
import android.util.Log
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class TestesApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Log.d("shch", "onCreate of application ")
    }

    override fun onTerminate() {
        super.onTerminate()
        Log.d("shch", "onTermination of application")
    }
}