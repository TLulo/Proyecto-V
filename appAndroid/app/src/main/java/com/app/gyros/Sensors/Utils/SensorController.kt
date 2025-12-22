package com.app.gyros.Sensors.Utils

import com.app.gyros.Sensors.AbstractSensor
import com.app.gyros.Sensors.Accelerometer
import com.app.gyros.Sensors.Gyroscope

class SensorController(private val accelerometer: Accelerometer, private val gyroscope: Gyroscope) {
    private var currentSensor : AbstractSensor = accelerometer

    fun changeSensor() {
        currentSensor.stop()

        currentSensor = when (currentSensor){
            accelerometer -> gyroscope
            gyroscope -> accelerometer
            else -> accelerometer
        }
        currentSensor.start()
    }

    fun chooseFirstSensor(choice : SensorType){
        currentSensor.stop()

        if (choice == SensorType.ACCELEROMETER){
            currentSensor = accelerometer
        }else if (choice == SensorType.GYROSCOPE) {
            currentSensor = gyroscope
        }
        currentSensor.start()
    }

    fun bindValues(viewModel: SensorViewModel){
        currentSensor.onValuesChanged = { (x, y, z) -> viewModel.updateValues(x, y, z)}
    }
    fun hasSensor(): Boolean{
        return currentSensor.hasSensor()
    }
    fun start() = currentSensor.start()
    fun stop() = currentSensor.stop()

}