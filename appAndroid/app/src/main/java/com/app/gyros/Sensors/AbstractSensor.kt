package com.app.gyros.Sensors

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import com.app.gyros.Sensors.Utils.SensorCoords
import com.app.gyros.Sensors.Utils.SensorViewModel

abstract class AbstractSensor(protected val sensorManager: SensorManager): SensorEventListener {
//   Atributos Compartidos
    protected var sensor : Sensor? = null
    var onValuesChanged: ((SensorCoords) -> Unit)? = null

    //   Metodos compartidos

    /*
    This function detect if device has Sensor
    return True if has and False else
     */
    public fun hasSensor(): Boolean {
        return (hasTypeSensor() != null)
    }

    /*
    This function return value of Sensor
    if has return Sensor.TYPE...
    else null
     */
    public abstract fun hasTypeSensor(): Sensor?

    /*
    This function start listen
     */
    fun start(){
        sensor = hasTypeSensor()
        if (sensor != null)
            sensorManager.registerListener(this,sensor, SensorManager.SENSOR_DELAY_GAME)
    }

    /*
    This function stop listen
     */
    fun stop(){
        if (sensor != null)
            sensorManager.unregisterListener(this)
    }

    /*
    Business logic to process sensor values
    */
    protected fun processorSensorValues(values : FloatArray): SensorCoords{
        val x = values.getOrNull(0) ?: 0f
        val y = values.getOrNull(1) ?: 0f
        val z = values.getOrNull(2) ?: 0f
        return SensorCoords(x,y,z)
    }

    /*
    This function update values on Sensor changed
    Its the base of Sensors
    */
    override fun onSensorChanged(event: SensorEvent?){
        if (event != null){
            val processedValues = processorSensorValues(event.values)
            onValuesChanged?.invoke(processedValues)
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }
}