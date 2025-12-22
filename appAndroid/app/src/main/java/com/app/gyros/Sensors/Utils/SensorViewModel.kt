package com.app.gyros.Sensors.Utils

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class SensorViewModel(private val senController: SensorController) : ViewModel() {

    private val _sensorValues = MutableStateFlow(SensorCoords())
    val sensorValues : StateFlow<SensorCoords> = _sensorValues.asStateFlow()

    private val _hasSensor = MutableStateFlow(false)
    val hasSensor: StateFlow<Boolean> = _hasSensor.asStateFlow()

    fun updateValues(x: Float, y: Float, z: Float) {
        _sensorValues.value = SensorCoords(x, y, z)
    }

    fun changeSensor(){
        senController.changeSensor()
        hasSensor()
    }

    fun chooseFirstSensor(choice : SensorType){
        senController.chooseFirstSensor(choice)
        hasSensor()
    }

    init {
        senController.bindValues(this)
        hasSensor()
    }

    fun hasSensor(){
        _hasSensor.value = senController.hasSensor()
    }

    fun onResume(){
        senController.start()
    }

    fun onStop(){
        senController.stop()
    }
}