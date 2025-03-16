package com.dg.testesbiryani

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dg.testesbiryani.todo.RecyclerAdapter
import com.dg.testesbiryani.vm.ToastViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity2 : AppCompatActivity() {
    val toastViewModel : ToastViewModel by viewModels()
    //val uiState = toastViewModel.uiState
    val i by ch()

    fun ch() : Lazy<Unit> {
        return lazy {

        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        Log.d("shch", " act2 create")
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val text = findViewById<TextView>(R.id.textmain2)
        val editText = findViewById<EditText>(R.id.edittext)
        val addTodo = findViewById<Button>(R.id.todobutton)
        val fetchTodo = findViewById<Button>(R.id.fetchtodo)
        val result  = findViewById<TextView>(R.id.result)
        val recyclerView  = findViewById<RecyclerView>(R.id.recycler_view)

        recyclerView.layoutManager = LinearLayoutManager(this)





        addTodo.setOnClickListener {
            val text = editText.getText().toString().trim()
            toastViewModel.addTodo(text)
            editText.setText("")
            Log.d("toast", "$text")
        }


        fetchTodo.setOnClickListener {
            lifecycleScope.launch {
                delay(1000)
                val intent = Intent(this@MainActivity2, MainActivity3::class.java)
                startActivity(intent)
            }
        }

        val data = toastViewModel.fetchTodo()
        val adapter = RecyclerAdapter(data) {
            Log.d("shch", "item click listener : $it" )
        }
        recyclerView.adapter = adapter


        lifecycleScope.launch {
            toastViewModel.uiState.collect {
                adapter.updatedata(it.listTodo)
                recyclerView.scrollToPosition(it.listTodo.size - 1)
                //adapter.notifyDataSetChanged()
                //val data = toastViewModel.fetchTodo()

            }
        }

        text.setOnClickListener {
//            val intent = Intent(this@MainActivity2, MainActivity3::class.java)
//            startActivity(intent)

            supportFragmentManager.beginTransaction().replace(R.id.frame, FirstFragment()).commit()

//            val scanner = Scanner(System.`in`)
//            val x = scanner.nextInt()
//            Log.d("shch", " toast " + x)
        }

        lifecycleScope.launch {
            toastViewModel.locationFlow().collectLatest {
                text.text = it.toString()
            }
        }
    }

    override fun onStart() {
        super.onStart()

        Log.d("shch", " act2 start")
    }

    override fun onResume() {
        super.onResume()
        lifecycleScope.launch {
            //Thread.sleep(10000)
        }
        Log.d("shch", " act2 resume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("shch", " act2 pause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("shch", " act2 stop")
    }
    override fun onDestroy() {
        super.onDestroy()
        Log.d("shch", " act2 destroy")
    }
}




fun stash() {
//    GlobalScope.launch {
//        for(i in 0 .. 10) {
//            delay(1000)
//            Log.d("shch", "testing main2 global scope")
//        }
//    }
//
////        lifecycleScope.launch {
////            delay(1000)
////            val intent = Intent(this@MainActivity2, MainActivity3::class.java)
////            startActivity(intent)
////        }
//
//    val activityManager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
//
//    val appTasks = activityManager.appTasks
//    for (appTask in appTasks) {
//        val taskInfo = appTask.taskInfo
//        Log.d("TaskInfo2", "Task ID: ${taskInfo.taskId}")
//        Log.d("TaskInfo2", "Base Intent: ${taskInfo.baseIntent}")
//        Log.d("TaskInfo2", "Top Activity: ${taskInfo.topActivity}")
//        Log.d("TaskInfo2", "Task Description: ${taskInfo.description}")
//    }
//
//    val text = findViewById<TextView>(R.id.textmain2)
//
//    text.setOnClickListener {
//        val intent = Intent(this@MainActivity2, MainActivity3::class.java)
//        startActivity(intent)
//
//    }
}