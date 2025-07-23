package com.app.gyros

import android.content.Context
import android.hardware.SensorManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.app.gyros.Sensors.AbstractSensor
import com.app.gyros.Sensors.Accelerometer
import com.app.gyros.Sensors.Gyroscope
import com.app.gyros.Sensors.Utils.SensorViewModel
import com.app.gyros.ui.theme.GyrosTheme

enum class TypeSensor {
    ACCELEROMETER, GYROSCOPE
}
class MainActivity : ComponentActivity() {
    private lateinit var accelerometer: Accelerometer
    private lateinit var gyroscope: Gyroscope
    private val sensorViewModel : SensorViewModel by viewModels()

    private var sensorUsed = mutableStateOf(TypeSensor.ACCELEROMETER)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        accelerometer = Accelerometer(sensorManager)
        accelerometer.initializeViewModel(sensorViewModel)

        gyroscope = Gyroscope(sensorManager)
        gyroscope.initializeViewModel(sensorViewModel)

        setContent {
            GyrosTheme {
                Surface (
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White,
                ){
                    Column(Modifier.fillMaxSize()) {
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxWidth(),
                            contentAlignment = Alignment.TopEnd
                        ){
                            ConfigMenu()
                        }
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            ShowMainSensor()
                        }
                    }
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        accelerometer.start()
        gyroscope.start()
    }

    override fun onStop() {
        super.onStop()
        accelerometer.stop()
        gyroscope.stop()
    }

    @Composable
    fun ShowMainSensor(){
        when (sensorUsed.value) {
            TypeSensor.ACCELEROMETER -> {
                SensorScreen(sensorViewModel, accelerometer)
            }
            TypeSensor.GYROSCOPE -> {
                SensorScreen(sensorViewModel, gyroscope)
            }
        }
    }

    @Composable
    fun ConfigMenu() {
        var expanded by remember { mutableStateOf(false) }
        Box(
            modifier = Modifier
                .padding(16.dp)
        ) {
            IconButton(onClick = { expanded = !expanded }) {
                Icon(Icons.Default.Settings, contentDescription = "Config")
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                DropdownMenuItem(
                    text = { Text("Change Sensor") },
                    onClick = {
                        ChangeSensor()
                        expanded = false
                    }
                )
            }
        }
    }

    fun ChangeSensor() {
        when(sensorUsed.value){
            TypeSensor.GYROSCOPE -> sensorUsed.value = TypeSensor.ACCELEROMETER
            TypeSensor.ACCELEROMETER -> sensorUsed.value = TypeSensor.GYROSCOPE
        }
    }
}
@Composable
fun SensorScreen(viewModel: SensorViewModel, sensor: AbstractSensor) {
    val (x, y, z) = viewModel.SensorValues

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (sensor.detectSensor()){
            Text("Sensor:")
            Text("X: $x")
            Text("Y: $y")
            Text("Z: $z")
        }else{
            Text("Your device does not have the required Sensor")
        }
    }
}