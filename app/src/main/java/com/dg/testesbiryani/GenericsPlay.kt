package com.dg.testesbiryani

import android.util.Log

fun <T> printSame(value: T): T {
    return value
}

inline fun <reified T> printType(value: T): String? {
    Log.d("shch1", "  checkType " + (value is Int) + " " + " type check " + T::class)
    return value!!::class.simpleName
}

fun <T : Number> sum(list: List<T>): Double {
    var res: Double = 0.0
    list.forEach {
        res += it.toDouble()
    }
    return list.sumOf { it.toDouble() }
}

fun <T> printTypeOf(value: T): String? {
    return value!!::class.simpleName
}