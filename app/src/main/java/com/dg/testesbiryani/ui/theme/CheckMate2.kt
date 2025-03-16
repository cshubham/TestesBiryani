package com.dg.testesbiryani.ui.theme

import android.util.Log

fun checkLog1() {
    Log.d("shch", " 1 " + Thread.currentThread().name)
}

fun checkLog2() {
    Log.d("shch", " 2 " + Thread.currentThread().name)
}