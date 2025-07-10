package com.app.gyros.Sensors

import android.hardware.Sensor
import android.hardware.SensorManager


class Accelerometer(sensorMan: SensorManager) : AbstractSensor(sensorMan){

    public override fun hasTypeSensor(): Sensor? {
        return (sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER))
    }

    public override fun detectSensor(): Boolean {
        return (hasTypeSensor() != null)
    }
}