package com.app.gyros.Sensors

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.lifecycle.ViewModel
import com.app.gyros.Sensors.Utils.SensorViewModel
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify
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

    @Test
    fun `testGetAndSetViewModel return setViewModel`() {
        val mockViewModel = mockk<SensorViewModel>()
        val mockSensorManager = mockk<SensorManager>()

        val accelerometer = Accelerometer(mockSensorManager)

        accelerometer.initializeViewModel(mockViewModel)

        assertEquals(accelerometer.getViewModel(),mockViewModel)
    }

    @Test
    fun `start register listener  when sensor exists`() {
        val mockSensorManager = mockk<SensorManager>()
        val mockSensor = mockk<Sensor>()

        val accelerometer = Accelerometer(mockSensorManager)

        every { mockSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) } returns mockSensor
        every { mockSensorManager.registerListener(any(), mockSensor, SensorManager.SENSOR_DELAY_GAME) } returns true

        accelerometer.start()

        verify{
            mockSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
            mockSensorManager.registerListener(any(),mockSensor,SensorManager.SENSOR_DELAY_GAME)
        }
    }

    @Test
    fun `start does not register listener when sensor does not exists`() {
        val mockSensorManager = mockk<SensorManager>()

        every { mockSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) } returns null

        val accelerometer = Accelerometer(mockSensorManager)
        accelerometer.start()

        verify(exactly = 1) {
            mockSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        }

        verify(exactly = 0) {
            mockSensorManager.registerListener(any<SensorEventListener>(),any<Sensor>(),any<Int>())
        }
    }

    @Test
    fun `stop register listener`() {
        val mockSensorManager = mockk<SensorManager>()

        val accelerometer = Accelerometer(mockSensorManager)

        justRun { mockSensorManager.unregisterListener(accelerometer) }
        accelerometer.stop()

        verify(exactly = 1) {
            mockSensorManager.unregisterListener(accelerometer)
        }
    }

    @Test
    fun `onSensorChanged processes sensor values correctly`() {
        val mockSensorManager = mockk<SensorManager>()
        val mockViewModel = mockk<SensorViewModel>(relaxed = true)

        val accelerometer = Accelerometer(mockSensorManager)
        accelerometer.initializeViewModel(mockViewModel)

        val mockEvent = mockk<SensorEvent>(relaxed = true)

        mockEvent.values =  floatArrayOf(23.0f,54.0f,128.0f)

        accelerometer.onSensorChanged(mockEvent)

        verify {
            mockViewModel.updateValues(23.0f, 54.0f, 128.0f)
        }
    }

}