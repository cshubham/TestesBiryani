package com.dg.testesbiryani

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.dg.testesbiryani.retrofit.ApiService
import com.dg.testesbiryani.room.TestesDatabase
import com.dg.testesbiryani.room.repo.TestesDao
import com.dg.testesbiryani.room.repo.TestesEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@ExperimentalCoroutinesApi
/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

//@RunWith(RobolectricTestRunner::class)
@RunWith(AndroidJUnit4::class)
class ExampleUnitTest {

    lateinit var mockWebServer: MockWebServer
    lateinit var database: TestesDatabase
    lateinit var testesDao: TestesDao

    @Before
    fun init() {
        mockWebServer = MockWebServer()
        mockWebServer.start()

        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            TestesDatabase::class.java
        ).allowMainThreadQueries().build()

        testesDao = database.testesDao()
    }

    @Test
    fun addition_isCorrect() = runBlocking {

        System.setProperty("javax.net.ssl.trustStoreType", "JKS")
        assertEquals(4, 2 + 2)


        val retrofit = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(ApiService::class.java)

        val mockResponse = MockResponse()
            .setBody("[{\"id\":1,\"name\":\"John Doe\"}]")
            .setResponseCode(200)
        mockWebServer.enqueue(mockResponse)


        val users = apiService.getData()
        assert(users.isNotEmpty())
        assert(users[0].name == "John Doe")
    }

    @Test
    fun roomTest() = runBlocking {
        testesDao.insert(
            TestesEntity(
                id = 1,
                user = "shubham"
            )
        )
        assertEquals( testesDao.getAll().first().first().user , "shubham")
    }
}