package com.dg.testesbiryani.di

import android.content.Context
import android.util.Log
import androidx.room.Room
import com.dg.testesbiryani.ProLogger
import com.dg.testesbiryani.check.SomeImpl
import com.dg.testesbiryani.check.SomeImpl2
import com.dg.testesbiryani.check.SomeInterface
import com.dg.testesbiryani.room.TestesDatabase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(includes = [BoundModule::class])
@InstallIn(SingletonComponent::class)
object TestesModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): TestesDatabase {
        return Room.databaseBuilder(
            context = context.applicationContext,
            name = "TESTES_DB",
            klass = TestesDatabase::class.java
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val requestInterceptor = Interceptor { chain ->
            Log.d("shch", "Request Interceptor: Request started.")
            val response = chain.proceed(chain.request())
            Log.d("shch", "Request Interceptor: Response received.")
            response
        }

        val responseInterceptor = Interceptor { chain ->
            Log.d("shch", "Response Interceptor: Processing response.")
            val response = chain.proceed(chain.request())
            Log.d("shch", "Response Interceptor: Response processed.")
            response
        }

        val networkInterceptor = Interceptor { chain ->
            Log.d("shch", "Network Interceptor: Sending network request.")
            val builder = chain.request().newBuilder().cacheControl(CacheControl.FORCE_CACHE).build()
            val response = chain.proceed(builder)
            Log.d("shch", "Network Interceptor: Network response received.")
            response
        }

        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
            )
            .addInterceptor(requestInterceptor)
            .addInterceptor(responseInterceptor)
            .addNetworkInterceptor(networkInterceptor)
            .build()
    }

    @Provides
    fun provideProLogger() : ProLogger {
        return ProLogger()
    }

    @Provides
    fun provideDispatcherIo(): CoroutineDispatcher {
        return Dispatchers.IO
    }

    @Provides
    @Singleton
    fun provideFlow() : StateFlow<Boolean> {
        return MutableStateFlow(false)
    }
}

@Module
@InstallIn(SingletonComponent::class)
abstract class BoundModule {
    @Binds
    @Singleton
    @IntoSet
    abstract fun bindSome(someInterface: SomeImpl): SomeInterface

    @Binds
    @Singleton
    @IntoSet
    abstract fun bindSome2(someInterface: SomeImpl2): SomeInterface
}
