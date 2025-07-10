package com.app.gyros.Sensors

import android.content.Context
import android.hardware.SensorManager
import androidx.test.core.app.ApplicationProvider
import org.junit.Test

class GyroscopeTest {

    @Test
    fun detectedTest(){
        val context = ApplicationProvider.getApplicationContext<Context>()

        val sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val gyroscope = Gyroscope(sensorManager)

        val result = gyroscope.detectSensor()

        println("Has a Gyroscope? $result")
    }

}