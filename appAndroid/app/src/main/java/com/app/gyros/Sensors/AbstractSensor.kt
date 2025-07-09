package com.app.gyros.Sensors

import android.content.Context
import android.hardware.SensorManager

abstract class AbstractSensor {
//   Atributos Compartidos

//    Funciones compartidas
    public abstract fun detectSensor(): Boolean
}