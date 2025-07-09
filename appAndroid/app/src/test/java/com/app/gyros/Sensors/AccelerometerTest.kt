package com.app.gyros.Sensors

import android.hardware.Sensor
import android.hardware.SensorManager
import org.junit.Test
//import org.junit.jupiter.api.Assertions.*
import io.mockk.mockk
import io.mockk.every
import org.junit.Assert.*

class AccelerometerTest {

    @Test
    fun `TestDetect return true when sensor exist`(){
        val mockSensorManager = mockk<SensorManager>()
        val mockSensor = mockk<Sensor>()

        every { mockSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) } returns mockSensor

        val accelerometer = Accelerometer(mockSensorManager)

        val result = accelerometer.detectSensor()

        assertTrue(result)
    }

    @Test
    fun `TestDetect return false when sensor does not exist`(){
        val mockSensorManager = mockk<SensorManager>()

        every { mockSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) } returns null

        val accelerometer = Accelerometer(mockSensorManager)

        val result = accelerometer.detectSensor()

        assertFalse(result)
    }

}