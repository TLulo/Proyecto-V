package com.app.gyros.Sensors.Utils

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.app.gyros.Sensors.AbstractSensor
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class SensorViewModel(val senController: SensorController) : ViewModel() {
    private val _sensorValues = MutableStateFlow(Triple(0f, 0f, 0f))
    val sensorValues : StateFlow<Triple<Float,Float,Float>> = _sensorValues.asStateFlow()
    private val _hasSensor = MutableStateFlow(false)
    val hasSensor: StateFlow<Boolean> = _hasSensor

    fun updateValues(x: Float, y: Float, z: Float) {
        _sensorValues.value = Triple(x, y, z)
    }

    init {
        hasSensor()
    }

    fun hasSensor(){
        _hasSensor.value = senController.hasSensor()
    }

    fun onResume(){
        senController.start()
    }

    fun onStop(){
        senController.stop()
    }
}