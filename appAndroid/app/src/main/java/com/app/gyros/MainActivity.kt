package com.app.gyros

import android.content.Context
import android.hardware.SensorManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.app.gyros.Sensors.Accelerometer
import com.app.gyros.Sensors.Gyroscope
import com.app.gyros.ui.theme.GyrosTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            GyrosTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                }
                Detected(this)
            }
        }
    }
}

@Composable
fun Detected(context: Context, modifier: Modifier = Modifier){
    val sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    val accelerometer = Accelerometer(sensorManager)
    val gyroscope = Gyroscope(sensorManager)

    Surface() {
        Box(Modifier.fillMaxSize(),contentAlignment = Alignment.Center){
            Text(
                text = (if (accelerometer.detectSensor()) "The user Has a Accelerometer"
                            else "The user Hasn't a Accelerometer")
                        + "\n" +
                        (if (gyroscope.detectSensor()) "The user Has a Gyroscope"
                            else "The user Hasn't a Gyroscope"),
                modifier = modifier.padding(50.dp)
            )
        }
    }
}