package com.dg.testesbiryani.model

data class TestesUiState(
    val list: List<User> = listOf(),
    val counter : Int = 0
)

data class User(var id: Int,  var name: String, val className : String)