package com.app.gyros.Sensors

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorManager
import androidx.test.core.app.ApplicationProvider
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
}