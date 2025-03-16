package com.dg.testesbiryani.uipost

import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.lifecycleScope
import com.dg.testesbiryani.R
import com.dg.testesbiryani.vm.TestComposeViewModel
import com.dg.testesbiryani.vm.TestComposeViewModel2
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import okhttp3.internal.format

@AndroidEntryPoint
class MainActivity5 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TestCompose(lifecycleScope)
            DummyCompose()
        }
    }
}

@Composable
fun DummyCompose(
    viewModel: TestComposeViewModel2 = hiltViewModel(),
) {
    Row(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.Red)) {
        Text(text = "Hee")
    }
}


@Composable
fun TestCompose(lifecycleScope: LifecycleCoroutineScope) {
    val viewModel: TestComposeViewModel = hiltViewModel()
    LaunchedEffect(Unit) {
        viewModel.doSomething(viewModel.hashCode())
    }
    val ctrScope = rememberCoroutineScope()
    LaunchedEffect(key1 = Unit) {
        lifecycleScope.launch {
            for(i in 0 .. 10) {
                Log.d("shch", " scope test lifecycle " + i)
                delay(1000)
            }
        }
        ctrScope.launch {
            for(i in 0 .. 10) {
                Log.d("shch", " scope test ctrScope " + i)
                delay(1000)
            }
        }
    }
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .padding(top = 20.dp, start = 20.dp, end = 20.dp)
            .verticalScroll(scrollState)
            .fillMaxSize()
            .background(Color.LightGray)
    ) {
        TopBar(
            Modifier
                .fillMaxSize()
                .background(Color.Magenta)
                .padding(10.dp))
        Spacer(modifier = Modifier.size(20.dp))
        HeaderImage()
        Spacer(modifier = Modifier.size(10.dp))
        TitleText()
        Spacer(modifier = Modifier.size(10.dp))
        SubText()
        Spacer(modifier = Modifier.size(10.dp))
        SubText()
    }
}

@Composable
private fun SubText() {
    Text(
        text = "Deprecated Gradle features were used in this build, making it incompatible with Gradle 9.0.\n" +
                "\n" +
                "You can use '--warning-mode all' to show the individual deprecation warnings and determine if they come from your own scripts or plugins.\n" +
                "\n" +
                "For more on this, please refer to https://docs.gradle.org/8.9/userguide/command_line_interface.html#sec:command_line_warnings in the Gradle documentation.\n" +
                "\n" +
                "BUILD SUCCESSFUL in 2s\n" +
                "42 actionable tasks: 6 executed, 36 up-to-date"
    )
}

@Composable
private fun TitleText() {
    Text(
        text = "From Java Programming language to Kotlin -- \nThe Complete Guide",
        fontSize = 40.sp,
        fontWeight = FontWeight.W500
    )
}

@Composable
private fun HeaderImage() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp),
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(20.dp)),
            painter = painterResource(R.drawable.ic_launcher_background),
            contentDescription = "",
            contentScale = ContentScale.FillWidth
        )
        Image(painter = painterResource(R.drawable.ic_launcher_foreground), "")
    }
}

@Composable
fun TopBar(modifier: Modifier = Modifier) {
    Row(
        modifier =
        modifier
//        Modifier
//            .fillMaxSize()
//            .background(Color.Gray)
//            .padding(vertical = 10.dp)
        ,
        verticalAlignment = Alignment.CenterVertically,
      /*  horizontalArrangement = Arrangement.SpaceBetween*/
    ) {
        Icon(painter = painterResource(R.drawable.ic_back_btn), contentDescription = "back")

        Row(modifier = Modifier
            .align(Alignment.CenterVertically)
            .weight(1f),
            horizontalArrangement = Arrangement.Center) {
            Icon(
                modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape)
                    .background(Color.Cyan),
                painter = painterResource(R.drawable.ic_launcher_foreground),
                contentDescription = ""
            )
            Spacer(modifier = Modifier.size(10.dp))
            Column {
                Text(text = "Published in..")
                Text(text = "Android Developers")
            }
        }

        Spacer(modifier = Modifier.size(20.dp))
    }
}