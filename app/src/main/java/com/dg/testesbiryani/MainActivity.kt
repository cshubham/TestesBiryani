package com.dg.testesbiryani

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import coil3.compose.AsyncImage
import com.dg.testesbiryani.ui.theme.TestesBiryaniTheme

private const val URL = "https://image.lexica.art/full_webp/ad52d483-c709-4808-b629-737e034adb74"
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        Log.d("shch", "onCreate: ${savedInstanceState?.getString("TESTES")}")
        setContent {
            TestesBiryaniTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                    AsyncImage(
                        model = URL,
                        contentDescription = null,
                        onState = {
                            Log.d("shch", "onState: $it")
                        }
                    )
                }
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

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TestesBiryaniTheme {
        Greeting("Android")
    }
}