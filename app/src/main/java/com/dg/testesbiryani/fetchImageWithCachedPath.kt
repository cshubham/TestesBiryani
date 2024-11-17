package com.dg.testesbiryani

import android.content.Context
import android.graphics.drawable.BitmapDrawable
import android.util.Log
import androidx.compose.ui.platform.LocalContext
import coil3.Bitmap
import coil3.ImageLoader
import coil3.asDrawable
import coil3.imageLoader
import coil3.request.ImageRequest
import coil3.request.SuccessResult
import coil3.request.allowHardware
import coil3.toBitmap
import coil3.util.DebugLogger
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

fun fetchImageWithCachedPath(
    context: Context,
    imageUrl: String,
    onImageDownloaded: (cachedFilePath: String?) -> Unit
) {
    val imageLoader = ImageLoader(context)
    val request = ImageRequest.Builder(context)
        .data(imageUrl)
        .listener(
            onSuccess = { request, result ->
                // This callback is triggered when the image is successfully loaded.
                val cachedFilePath = getCachedFilePath(imageLoader, imageUrl)
                Log.d("shch", "coil success " + cachedFilePath)
                onImageDownloaded(cachedFilePath)
            },
            onError = { request, throwable ->
                // Handle error if needed
                Log.d("shch", "coil error " + throwable.throwable.message)
                onImageDownloaded(null)
            }
        )
        .build()

    // Enqueue the image request
    imageLoader.enqueue(request)
}

fun getCachedFilePath(imageLoader: ImageLoader, url: String): String? {
    return runBlocking {
        val cacheKey = imageLoader.diskCache?.directory.toString()
//        cacheKey?.let { key ->
//            imageLoader.diskCache?.get(key)?.data?.toString() // Get file path from disk cache
//        }
        cacheKey
    }
}

 fun getBitmap(context: Context, url: String): android.graphics.Bitmap? = runBlocking{
    return@runBlocking withContext(Dispatchers.IO) {


        val request = ImageRequest.Builder(context)
            .data(url)
            .build()

        val bitmap = context.imageLoader.execute(request).image?.toBitmap()
        return@withContext bitmap
    }
}
