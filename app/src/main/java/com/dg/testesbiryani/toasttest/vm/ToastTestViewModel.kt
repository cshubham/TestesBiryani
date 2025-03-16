package com.dg.testesbiryani.toasttest.vm

import androidx.lifecycle.ViewModel
import com.dg.testesbiryani.toasttest.model.ToastTestState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ToastTestViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(ToastTestState())
    val uiState = _uiState.asStateFlow()

    init {

    }
    fun addTodo(text: String) {
        _uiState.value = _uiState.value.copy(
            list = _uiState.value.list + text
        )
        //repo.updateServer(test)
    }

    fun removeItemAtPos(pos: Int) {
        val updatedList = _uiState.value.list.toMutableList()
        updatedList.removeAt(pos)
        _uiState.value = _uiState.value.copy(
            list = updatedList
        )
    }

}