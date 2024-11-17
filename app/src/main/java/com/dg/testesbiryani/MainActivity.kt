package com.dg.testesbiryani

import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import coil3.compose.AsyncImage
import coil3.compose.rememberAsyncImagePainter
import coil3.request.ImageRequest
import coil3.size.Scale
import kotlinx.coroutines.launch

private const val URL = "https://image.lexica.art/full_webp/ad52d483-c709-4808-b629-737e034adb74"
private const val URL_L = "https://images.pexels.com/photos/210186/pexels-photo-210186.jpeg?cs=srgb&dl=dawn-landscape-nature-210186.jpg&fm=jpg"
private const val URL_LB = "https://i.ytimg.com/vi/c7oV1T2j5mc/maxresdefault.jpg"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        Log.d("shch", "onCreate: ${savedInstanceState?.getString("TESTES")}")
        lifecycleScope.launch {
            val bitmap = getBitmap(this@MainActivity, URL_LB)
            Log.d("shch", "just bitmap " + bitmap)
            bitmap ?: return@launch
            Log.d("shch", "onCreate: bitmap " + bitmap.height + " " + bitmap.width + " aspect " + bitmap.height.toFloat() / bitmap.width)

        }
        setContent {
            Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )



                Box(modifier = Modifier
                    .size(300.dp)
                    .background(Color.Red)) {
                    AsyncImage(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Cyan)
                            .padding(10.dp)
                            .clipToBounds(),
//                        model = URL_LB,
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(getBitmap(LocalContext.current, URL_L)) // Pass the Bitmap here
                            .build(),
                        contentScale = ContentScale.Crop,
                        contentDescription = null,
                        onState = {
                            Log.d("shch", "onState: $it")
                        }
                    )
                }

            }

            Box(
                modifier = Modifier
                    .size(100.dp)
                    .padding(10.dp)
                    .clipToBounds() // Try toggling this on and off
                    .background(Color.Gray)
            ) {
                Box(
                    modifier = Modifier
                        .size(150.dp)
                        .offset(
                            x = (-25).dp,
                            y = (-25).dp
                        ) // Move part of the child outside the parent's boundaries
                        .background(Color.Red)
                )
            }

        }
        fetchImageWithCachedPath(this, URL) {}
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.d("shch", "onRestoreInstanceState: ${savedInstanceState.getString("TESTES")}")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d("shch", "onSaveInstanceState: ${outState.getString("TESTES")}")
        outState.putString("TESTES", "BIRYANI")
    }

    override fun onStop() {
        super.onStop()
        Log.d("shch", "onStop: ")
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

