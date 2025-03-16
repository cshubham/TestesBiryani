package com.dg.testesbiryani.check

import android.util.Log
import com.dg.testesbiryani.ProLogger
import javax.inject.Inject

fun interface SomeInterface {
    fun some()
}

class SomeImpl @Inject constructor(
    private val proLogger: ProLogger
) : SomeInterface {
    override fun some() {
        Log.d("shch", "hilt test " + proLogger.hashCode())
    }
}

class SomeImpl2 @Inject constructor() : SomeInterface {
    override fun some() {
        Log.d("shch", "hilt test 2")
    }
}



