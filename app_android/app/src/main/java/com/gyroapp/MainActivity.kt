package com.gyroapp

import android.app.Activity
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.widget.Toast
import com.gyroapp.R

class MainActivity : Activity(), SensorEventListener {
    private lateinit var sensorManager: SensorManager
    private var gyrosensor: Sensor? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        gyrosensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)

        if (gyrosensor == null) {
            Toast.makeText(this, "Gyroscope not available", Toast.LENGTH_SHORT).show()
        } else {
            sensorManager.registerListener(this, gyrosensor, SensorManager.SENSOR_DELAY_GAME)
        }
    }

    override fun onSensorChanged(event: SensorEvent) {
        if (event.sensor.type == Sensor.TYPE_GYROSCOPE) {
            val x = event.values[0]
            val y = event.values[1]
            val z = event.values[2]

            // Use the gyroscope data (x, y, z) as needed
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        // Handle accuracy changes if needed
    }
}