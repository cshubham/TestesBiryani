package com.dg.testesbiryani.vm

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dg.testesbiryani.ProLogger
import com.dg.testesbiryani.check.SomeInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


data class ToastUiState(
    val listTodo : List<String> = emptyList()
)

@HiltViewModel
class ToastViewModel @Inject constructor(
    @ApplicationContext val context: Context,
    private val savedStateHandle: SavedStateHandle,
    private val proLogger: ProLogger,
    private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {
    private val _uiState = MutableStateFlow(ToastUiState())
    val uiState: StateFlow<ToastUiState> = _uiState
    val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    val channel = Channel<Int>()

    init {
        Log.d("shch", "init called toast vm " + "someInterface.hashCode()" + " " + proLogger.hashCode())
    }

    fun addTodo(todo: String) = viewModelScope.launch(ioDispatcher) {
        _uiState.value = _uiState.value.copy(
            listTodo = _uiState.value.listTodo + todo
        )
    }

    fun fetchTodo(): List<String> {
        return _uiState.value.listTodo
    }

    @SuppressLint("MissingPermission")
    fun locationFlow(): Flow<Location> = callbackFlow {
        val listener = object : LocationListener {
            override fun onLocationChanged(location: Location) {
                trySend(location)
            }

            override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
                Log.d("shch", " nknk ")
            }
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0L, 0f, listener)
        awaitClose {
            Log.d("toast" , "await close ..")
            locationManager.removeUpdates(listener)
        }
    }
}