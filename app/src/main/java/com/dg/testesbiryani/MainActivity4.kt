package com.dg.testesbiryani

import android.os.Bundle
import android.util.Log
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
import com.dg.testesbiryani.toasttest.Adapter.ToastTestRecyclerAdapter
import com.dg.testesbiryani.toasttest.vm.ToastTestViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity4 : AppCompatActivity() {
    val viewModel : ToastTestViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main4)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val editText = findViewById<EditText>(R.id.edittext)
        val addTodo = findViewById<TextView>(R.id.addtodo)
        val recyclerView = findViewById<RecyclerView>(R.id.test_recycler_view)

        addTodo.setOnClickListener {
            val text = editText.text.toString()
            viewModel.addTodo(text)
            editText.setText("")
        }

        val adapter = ToastTestRecyclerAdapter(viewModel.uiState.value.list) {
            Log.d("check", "clicked item $it")
            viewModel.removeItemAtPos(it)
        }
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        lifecycleScope.launch {
            viewModel.uiState.collectLatest {
                Log.d("checkTest", " listtt " + it.list)
                adapter.updateList(it.list)
                recyclerView.scrollToPosition(it.list.size)
            }
        }
    }
}