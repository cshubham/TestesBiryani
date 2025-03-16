package com.dg.testesbiryani.check

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CoolClass @Inject constructor() {
    fun doSome() {
        CheckObject.BuilderCheck().setSomeId("id").build()
    }
}