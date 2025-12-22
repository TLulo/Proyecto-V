package com.app.gyros.Composes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.unit.dp
import com.app.gyros.Sensors.Utils.SensorType
import com.app.gyros.Sensors.Utils.SensorViewModel

class ComposeActivity {

    @Composable
    fun ShowMainSensorScreen(sensorViewModel : SensorViewModel) {
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

    @Composable
    fun ConfigMenu(sensorViewModel : SensorViewModel) {
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

    @Composable
    fun ChoseFirstSensor(sensorViewModel : SensorViewModel){
        val showDialog = remember { mutableStateOf(true) }

        if (showDialog.value) {
            AlertDialog(
                onDismissRequest = { },
                title = { Text("Choose a Sensor") },
                text = { Text("You can change it later.") },
                confirmButton = {
                    TextButton(onClick = {
                        showDialog.value = false
                        sensorViewModel.chooseFirstSensor(SensorType.ACCELEROMETER)
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
}