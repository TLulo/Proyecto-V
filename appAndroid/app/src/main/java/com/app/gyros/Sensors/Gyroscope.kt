package com.app.gyros.Sensors

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorManager

class Gyroscope(private val context: Context): AbstractSensor() {

    override fun detectSensor(): Boolean {
        sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        return (sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE) != null)
    }

}