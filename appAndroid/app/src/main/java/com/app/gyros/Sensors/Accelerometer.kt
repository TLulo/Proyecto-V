package com.app.gyros.Sensors

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import com.app.gyros.Sensors.Utils.SensorViewModel


class Accelerometer(private val sensorManager: SensorManager) : AbstractSensor(), SensorEventListener{

    private lateinit var viewModel : SensorViewModel

        public fun setViewModel(viewModel: SensorViewModel) {
            this.viewModel = viewModel
        }

    public override fun detectSensor(): Boolean {
        return (sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null)
    }

    fun start(){
        var sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        if (sensor != null)
            sensorManager.registerListener(this,sensor, SensorManager.SENSOR_DELAY_GAME)
    }

    fun stop(){
        sensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent?){
        if (event != null){
            val x = event.values[0]
            val y = event.values[1]
            val z = event.values[2]

            viewModel.updateValues(x,y,z)
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }

}