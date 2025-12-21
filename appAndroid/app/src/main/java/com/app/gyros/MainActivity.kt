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
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import com.app.gyros.Sensors.Utils.SensorController
import com.app.gyros.Sensors.Utils.SensorType
import com.app.gyros.Sensors.Utils.SensorViewModel
import com.app.gyros.Sensors.Utils.SensorViewModelFactory
import com.app.gyros.ui.theme.GyrosTheme

enum class TypeSensor {
    ACCELEROMETER, GYROSCOPE, NULL
}
class MainActivity : ComponentActivity() {
    private val sensorViewModel : SensorViewModel by viewModels(){
        SensorViewModelFactory(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
                            ChoseFirstSensor()
                            ShowMainSensor()
                        }
                    }
                }
            }
        }
    }

    @Composable
    fun ChoseFirstSensor(){
        val showDialog = remember { mutableStateOf(true) }

        if (showDialog.value) {
            AlertDialog(
                onDismissRequest = { },
                title = { Text("Choose a Sensor") },
                text = { Text("You can change it later.") },
                confirmButton = {
                    TextButton(onClick = {
                        showDialog.value = false
                        sensorViewModel.chooseFirstSensor(SensorType.ACCELEROMETTER)
                    }) {
                        Text("ACCELEROMETER")
                    }
                },
                dismissButton = {
                    TextButton(onClick = {
                        showDialog.value = false
                        sensorViewModel.chooseFirstSensor(SensorType.GYROSCOPE)
                    }) {
                        Text("GYROSCOPE")
                    }
                }
            )
        }
    }
    @Composable
    fun ShowMainSensor(){
        SensorScreen(sensorViewModel)
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
                        sensorViewModel.changeSensor()
                        expanded = false
                    }
                )
            }
        }
    }

    override fun onResume() {
        super.onResume()
        sensorViewModel.onResume()
    }

    override fun onStop() {
        super.onStop()
        sensorViewModel.onStop()
    }

}


@Composable
fun SensorScreen(sensorViewModel : SensorViewModel) {
    val values by sensorViewModel.sensorValues.collectAsState()
    val hasSensor by sensorViewModel.hasSensor.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (hasSensor){
            Text("Sensor:")
            Text("X: ${values.x}")
            Text("Y: ${values.y}")
            Text("Z: ${values.z}")
        }else{
            Text("Your device does not have the required Sensor")
        }
    }
}