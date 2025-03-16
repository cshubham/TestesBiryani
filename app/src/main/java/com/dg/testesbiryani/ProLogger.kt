package com.dg.testesbiryani

import android.util.Log

class ProLogger {
    fun LOG(str: String) {
        Log.d("prologger", " " + str)
        Log.d("shch", " " + str)
    }

    @JvmOverloads
    @JvmName("naam")
    fun LOGx(str: String, defString: String = "69") {
        Log.d("prologger", " " + str + " " + defString)
    }
}