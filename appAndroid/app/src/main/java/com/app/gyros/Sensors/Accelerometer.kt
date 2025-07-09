package com.app.gyros.Sensors

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorManager


class Accelerometer(private val sensorManager: SensorManager) : AbstractSensor(){

    public override fun detectSensor(): Boolean {
        return (sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null)
    }

}