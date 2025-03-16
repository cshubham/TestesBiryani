package com.dg.testesbiryani

import android.util.Log

class CheckMate {
    fun cool() {
        Log.d("shch", "cool in checkMate")
    }
}

class User(val name: String, var age: Int) {
    fun displayInfo() {
        println("Name: $name, Age: $age")
    }
}