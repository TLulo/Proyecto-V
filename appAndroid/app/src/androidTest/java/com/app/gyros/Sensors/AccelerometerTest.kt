package com.app.gyros.Sensors

import android.content.Context
import android.hardware.SensorManager
import androidx.test.core.app.ApplicationProvider
import org.junit.Test


class AccelerometerTest {

    @Test
    fun detectedTest(){
        val context = ApplicationProvider.getApplicationContext<Context>()

        val sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val accelerometer = Accelerometer(sensorManager)

        val result = accelerometer.detectSensor()

        println("Has a accelerometer? $result")
    }
}