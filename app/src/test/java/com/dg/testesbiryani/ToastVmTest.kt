package com.dg.testesbiryani

import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.dg.testesbiryani.vm.ToastViewModel
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineScheduler
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ToastVmTest {
    private val testScheduler = TestCoroutineScheduler()
    @OptIn(ExperimentalCoroutinesApi::class)
    private val dispatcher =
        //Dispatchers.IO
        //StandardTestDispatcher(scheduler = testScheduler)
        UnconfinedTestDispatcher()
    private var vm : ToastViewModel = ToastViewModel(
        someInterface = mockk(),
        proLogger = mockk(),
        ioDispatcher = dispatcher,
        savedStateHandle = mockk(),
        context = ApplicationProvider.getApplicationContext()
    )


    @Test
    fun testUiStateUpdates() = runTest {
        vm.addTodo("shch")
        assertEquals(vm.fetchTodo().size, 1)
        assert(vm.fetchTodo().contains("shch"))
    }
}