package com.app.gyros.Sensors

import android.hardware.Sensor
import android.hardware.SensorManager
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class GyroscopeTest {

    @Test
    fun `TestHasTypeSensor return Sensor with sensor exist`(){
        val mockSensorManager = mockk<SensorManager>()
        val mockSensor = mockk<Sensor>()

        every { mockSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE) } returns mockSensor

        val gyroscope = Gyroscope(mockSensorManager)

        val result = gyroscope.hasTypeSensor()

        assertEquals(mockSensor,result)
    }

    @Test
    fun `TestHasTypeSensor return Null with sensor does not exist`(){
        val mockSensorManager = mockk<SensorManager>()

        every { mockSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE) } returns null

        val gyroscope = Gyroscope(mockSensorManager)

        val result = gyroscope.hasTypeSensor()

        assertEquals(null,result)
    }

    @Test
    fun `TestDetect return true when sensor exist`() {
        val mockSensorManager = mockk<SensorManager>()
        val mockSensor = mockk<Sensor>()

        every { mockSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE) } returns mockSensor

        val gyroscope = Gyroscope(mockSensorManager)

        val result = gyroscope.detectSensor()

        assertTrue(result)
    }

    @Test
    fun `TestDetect return false when sensor does not exist`() {
        val mockSensorManager = mockk<SensorManager>()

        every { mockSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE) } returns null

        val gyroscope = Gyroscope(mockSensorManager)

        val result = gyroscope.detectSensor()

        assertFalse(result)
    }
}