package com.app.gyros.Sensors.Utils

import com.app.gyros.Sensors.Accelerometer

class SensorController(val sensor: Accelerometer) {

    fun hasSensor(): Boolean{
        return sensor.hasSensor()
    }
    fun start() = sensor.start()
    fun stop() = sensor.stop()

}