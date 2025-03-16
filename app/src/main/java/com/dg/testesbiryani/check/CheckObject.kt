package com.dg.testesbiryani.check

object CheckObject {
    var id : String? = null
    class BuilderCheck {
        fun setSomeId(idl: String) : BuilderCheck {
            id = idl
            return this
        }

        fun build(): CheckObject {
            return CheckObject
        }

    }
}