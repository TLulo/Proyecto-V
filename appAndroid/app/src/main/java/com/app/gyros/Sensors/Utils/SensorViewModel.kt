package com.app.gyros.Sensors.Utils

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class SensorViewModel : ViewModel() {
    var SensorValues by mutableStateOf(Triple(0f, 0f, 0f))
        private set

    fun updateValues(x: Float, y: Float, z: Float) {
        SensorValues = Triple(x, y, z)
    }
}