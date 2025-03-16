package com.dg.testesbiryani

import android.app.IntentService
import android.content.Intent
import android.util.Log
import androidx.core.app.JobIntentService

class MyIntentService() : JobIntentService() {
//    override fun onHandleIntent(intent: Intent?) {
//    }

    override fun onHandleWork(intent: Intent) {
        Log.d("shch", "1st IntentService of Jeewan " + Thread.currentThread().name)
    }
}