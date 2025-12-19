package com.app.gyros.Sensors.Utils

import com.app.gyros.Sensors.Accelerometer

class SensorController(private val sensor: Accelerometer) {

    fun bindValues(viewModel: SensorViewModel){
        sensor.onValuesChanged = { (x,y,z) -> viewModel.updateValues(x,y,z)}
    }
    fun hasSensor(): Boolean{
        return sensor.hasSensor()
    }
    fun start() = sensor.start()
    fun stop() = sensor.stop()

}