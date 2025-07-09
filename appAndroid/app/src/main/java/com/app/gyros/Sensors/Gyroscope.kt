package com.app.gyros.Sensors

import android.hardware.Sensor
import android.hardware.SensorManager

class Gyroscope(private val sensorManager: SensorManager): AbstractSensor() {

    override fun detectSensor(): Boolean {
        return (sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE) != null)
    }

}