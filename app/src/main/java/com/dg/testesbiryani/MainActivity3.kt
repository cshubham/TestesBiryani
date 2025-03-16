package com.dg.testesbiryani

import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main2)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val activityManager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager

        val appTasks = activityManager.appTasks
        for (appTask in appTasks) {
            val taskInfo = appTask.taskInfo
            Log.d("TaskInfo3", "Task ID: ${taskInfo.taskId}")
            Log.d("TaskInfo3", "Base Intent: ${taskInfo.baseIntent}")
            Log.d("TaskInfo3", "Top Activity: ${taskInfo.topActivity}")
            Log.d("TaskInfo3", "Task Description: ${taskInfo.description}")
        }

        lifecycleScope.launch(Job()) {
            delay(1000)
            val intent = Intent(this@MainActivity3, MainActivity4::class.java)
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }
        Log.d("shch", " rela log")
    }
}