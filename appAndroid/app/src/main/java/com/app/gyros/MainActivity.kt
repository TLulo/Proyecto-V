package com.app.gyros

import android.content.Context
import android.hardware.SensorManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.app.gyros.Sensors.Accelerometer
import com.app.gyros.Sensors.Gyroscope
import com.app.gyros.Sensors.Utils.SensorViewModel
import com.app.gyros.ui.theme.GyrosTheme

class MainActivity : ComponentActivity() {
    private lateinit var accelerometer: Accelerometer
    private lateinit var gyroscope: Gyroscope
    private val sensorViewModel : SensorViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        accelerometer = Accelerometer(sensorManager)
        accelerometer.setViewModel(sensorViewModel)

        setContent {
            GyrosTheme {
                Surface (
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White,
                ){
                }
                SensorScreen(sensorViewModel)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        accelerometer.start()
    }

    override fun onStop() {
        super.onStop()
        accelerometer.stop()
    }
}

@Composable
fun SensorScreen(viewModel: SensorViewModel) {
    val (x, y, z) = viewModel.SensorValues

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text("Sensor:")
        Text("X: $x")
        Text("Y: $y")
        Text("Z: $z")
    }
}