package com.dg.testesbiryani.retrofit

import android.util.Log


data class ResponseData(
    @JvmField val id: String,
    val name: String,
    val full_name: String = "cool"
) {
    companion object {
        @JvmStatic
        fun companionInResponseData() {
            Log.d("shch", "Companion in ResponseData")
        }
    }
}