package com.app.gyros.Sensors

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorManager
import androidx.test.core.app.ApplicationProvider
import org.junit.Assert.assertEquals
import org.junit.Test

class GyroscopeTest {

    @Test
    fun hasTypeSensorTest(){
        val context = ApplicationProvider.getApplicationContext<Context>()

        val sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val gyroscope = Gyroscope(sensorManager)

        val result = gyroscope.hasTypeSensor()

        assertEquals(sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE), result)
    }

    @Test
    fun detectedTest(){
        val context = ApplicationProvider.getApplicationContext<Context>()

        val sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val gyroscope = Gyroscope(sensorManager)

        val result = gyroscope.detectSensor()

        assertEquals(sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE) != null, result)
    }

}