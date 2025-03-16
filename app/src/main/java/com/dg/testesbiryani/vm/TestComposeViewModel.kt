package com.dg.testesbiryani.vm

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dg.testesbiryani.model.TestComposeUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TestComposeViewModel @Inject constructor(

): ViewModel() {
    fun doSomething(hashCode: Int) {
        Log.d("shch", "test compse vm hash " + hashCode)
    }

    val uiState = MutableStateFlow(TestComposeUiState())
    init {
        Log.d("shch", "test compse vm")
        viewModelScope.launch {

        }
    }
}