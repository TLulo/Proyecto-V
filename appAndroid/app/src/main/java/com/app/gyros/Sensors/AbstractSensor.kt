package com.app.gyros.Sensors

import android.hardware.SensorManager

abstract class AbstractSensor {
//   Atributos Compartidos
    protected lateinit var sensorManager: SensorManager

//    Funciones compartidas
    public abstract fun detectSensor(): Boolean
}