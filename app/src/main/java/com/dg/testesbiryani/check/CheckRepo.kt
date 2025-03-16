package com.dg.testesbiryani.check

import com.dg.testesbiryani.ProLogger
import javax.inject.Inject

class CheckRepo @Inject constructor(
    val proLogger: ProLogger
) {

    val list: MutableList<in Check1> = mutableListOf()
    val list2: MutableList<Check1> = mutableListOf()

   init {
      // list2 = list

       list.add(Check1())
       list.add(Check2())
//       list.add(Check3())

       val dogBox: Box<Check2> = Box(Check2())
       val animalBox: Box<Check1> = dogBox
   }

}


open class Check1()

class Check2 : Check1()

class Check3()

class Box<out T>(val item: T)