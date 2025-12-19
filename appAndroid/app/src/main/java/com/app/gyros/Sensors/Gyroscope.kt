package com.app.gyros.Sensors

import android.hardware.Sensor
import android.hardware.SensorManager

class Gyroscope(protected val sensorMan: SensorManager): AbstractSensor(sensorMan) {

    override fun hasSensor(): Boolean {
        return ( hasTypeSensor() != null)
    }

    override fun hasTypeSensor(): Sensor? {
        return (sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE))
    }
}