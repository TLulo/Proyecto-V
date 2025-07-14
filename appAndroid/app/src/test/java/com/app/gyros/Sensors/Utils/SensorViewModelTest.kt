package com.app.gyros.Sensors.Utils

import org.junit.Assert.assertEquals
import org.junit.Test

class SensorViewModelTest {

    @Test
    fun `SensorValues updates correctly when updateValues is called`(){
        val firstViewModel = SensorViewModel()
        val initValues = Triple(0f,0f,0f)

        assertEquals(initValues,firstViewModel.SensorValues)

        val updateValues = Triple(13.0f,23.0f,34.0f)
        firstViewModel.updateValues(updateValues.first,updateValues.second,updateValues.third)

        assertEquals(updateValues,firstViewModel.SensorValues)
    }
}