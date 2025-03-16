package com.dg.testesbiryani.vm

import android.util.Log
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TestComposeViewModel2 @Inject constructor(): ViewModel() {
    init {
        Log.d("shch", "test compse vm 2")
    }
}