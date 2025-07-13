package com.app.gyros.Sensors

import android.hardware.Sensor
import android.hardware.SensorManager
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Assert.assertEquals
import org.junit.Test

class AccelerometerTest {

    @Test
    fun `TestHasTypeSensor return Sensor with sensor exist`(){
        val mockSensorManager = mockk<SensorManager>()
        val mockSensor = mockk<Sensor>()

        every { mockSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) } returns mockSensor

        val accelerometer = Accelerometer(mockSensorManager)

        val result = accelerometer.hasTypeSensor()

        assertEquals(mockSensor,result)
    }

    @Test
    fun `TestHasTypeSensor return Null with sensor does not exist`(){
        val mockSensorManager = mockk<SensorManager>()

        every { mockSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) } returns null

        val accelerometer = Accelerometer(mockSensorManager)

        val result = accelerometer.hasTypeSensor()

        assertEquals(null,result)
    }

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