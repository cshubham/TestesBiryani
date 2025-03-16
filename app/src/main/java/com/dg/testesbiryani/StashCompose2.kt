package com.dg.testesbiryani

import android.util.Log
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import kotlinx.coroutines.delay
import java.util.UUID

@Composable
fun StashCompose2(innerPadding: PaddingValues) {
    Box(modifier = Modifier
        .fillMaxSize()
        .padding(innerPadding)) {

        val transition = rememberInfiniteTransition()
        val alpha = transition.animateFloat(
            initialValue = 0f,
            targetValue = 1f,
            animationSpec = infiniteRepeatable(animation = tween(500))
        )

        val remCheck = remember { mutableStateOf(2) }
        val remSaveCheck = rememberSaveable { mutableStateOf(2) }
        val isTimeout = remember { mutableStateOf(2) }

        Text(modifier = Modifier.clickable {
            remCheck.value++
        }, text = "Check " + remCheck.value + " " + remSaveCheck.value)

        LaunchedEffect(remCheck.value, remSaveCheck.value) {
            Log.d("shch", " remCheck " + remCheck.value + " " + remSaveCheck.value)
            delay(1000)
            //remCheck.value = 3
            remSaveCheck.value = 3
        }



        Timer(onTimeout = { hash, valuse, v2 ->
            Log.d("shch", "inside timer " + remCheck.value + " hash " + valuse + " " + v2)
        }, remCheck)

//        LaunchedEffect(Unit) {
//            for (i in 0 .. 3) {
//                isTimeout.value = i + 100
//                delay(500)
//            }
//        }



       // Text("Timeout occurred! " + isTimeout.value)



    }
}

@Composable
fun Timer(onTimeout: (String, v: Int, v2: Int) -> Unit, value: MutableState<Int>) {
   // val updatedValue = rememberUpdatedState(value.value)
    LaunchedEffect(Unit) {
        delay(3000) // Simulate 5 seconds
        onTimeout(UUID.randomUUID().toString(), value.value, 69) // Call the timeout action
    }
}



@Composable
fun TimerScreen() {
    val isTimeout = remember { mutableStateOf(false) }


}