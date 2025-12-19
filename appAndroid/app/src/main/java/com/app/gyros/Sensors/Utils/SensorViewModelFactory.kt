package com.app.gyros.Sensors.Utils

import android.content.Context
import android.hardware.SensorManager
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.app.gyros.Sensors.AbstractSensor
import com.app.gyros.Sensors.Accelerometer

class SensorViewModelFactory(
    private val context: Context
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>) : T {
        val sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager

        val sensor = Accelerometer(sensorManager)

        val controller = SensorController(sensor)
        val viewModel = SensorViewModel(controller)

        sensor.initializeViewModel(viewModel)

        return viewModel as T
    }
}