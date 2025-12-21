package com.app.gyros.Sensors.Utils

import android.content.Context
import android.hardware.SensorManager
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.app.gyros.Sensors.Accelerometer
import com.app.gyros.Sensors.Gyroscope

class SensorViewModelFactory(
    private val context: Context
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>) : T {
        val sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager

        val accelerometer = Accelerometer(sensorManager)
        val gyroscope = Gyroscope(sensorManager)

        val controller = SensorController(accelerometer,gyroscope)
        val viewModel = SensorViewModel(controller)

        accelerometer.initializeViewModel(viewModel)
        gyroscope.initializeViewModel(viewModel)

        return viewModel as T
    }
}