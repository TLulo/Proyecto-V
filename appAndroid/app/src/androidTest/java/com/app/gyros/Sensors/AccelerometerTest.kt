package com.app.gyros.Sensors

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorManager
import androidx.lifecycle.ViewModel
import androidx.test.core.app.ApplicationProvider
import com.app.gyros.Sensors.Utils.SensorViewModel
import org.junit.Assert.*
import org.junit.Test


class AccelerometerTest {
    @Test
    fun hasTypeSensorTest(){
        val context = ApplicationProvider.getApplicationContext<Context>()

        val sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val accelerometer = Accelerometer(sensorManager)

        val result = accelerometer.hasTypeSensor()

        assertEquals(sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), result)
    }

    @Test
    fun detectedTest(){
        val context = ApplicationProvider.getApplicationContext<Context>()

        val sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val accelerometer = Accelerometer(sensorManager)

        val result = accelerometer.detectSensor()
        assertEquals(sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null, result)
    }

    @Test
    fun testGetAndSetViewModel() {
        val context = ApplicationProvider.getApplicationContext<Context>()

        val sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager

        val accelerometer = Accelerometer(sensorManager)
        val viewModel = SensorViewModel()

        accelerometer.initializeViewModel(viewModel)
        val retrievedViewModel = accelerometer.get_ViewModel()

        assertEquals(viewModel, retrievedViewModel)
    }

    @Test
    fun start(){
        val context = ApplicationProvider.getApplicationContext<Context>()

        val sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager

        val accelerometer = Accelerometer(sensorManager)

        if(accelerometer.detectSensor()){
            accelerometer.start()
        }else{
            assertNull(accelerometer.hasTypeSensor())
        }
    }

    @Test
    fun stop(){
        val context = ApplicationProvider.getApplicationContext<Context>()

        val sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager

        val accelerometer = Accelerometer(sensorManager)

        accelerometer.stop()
    }
}