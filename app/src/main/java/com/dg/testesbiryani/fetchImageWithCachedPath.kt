package com.dg.testesbiryani

import android.content.Context
import android.util.Log
import coil3.ImageLoader
import coil3.request.ImageRequest
import kotlinx.coroutines.runBlocking

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
