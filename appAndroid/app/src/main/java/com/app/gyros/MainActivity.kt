package com.app.gyros

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Surface
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.app.gyros.Composes.ComposeActivity
import com.app.gyros.Sensors.Utils.SensorViewModel
import com.app.gyros.Sensors.Utils.SensorViewModelFactory
import com.app.gyros.ui.theme.GyrosTheme

class MainActivity : ComponentActivity() {
    private val composeActivity = ComposeActivity()
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
                            composeActivity.ConfigMenu(sensorViewModel)
                        }
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            composeActivity.ChoseFirstSensor(sensorViewModel)
                            composeActivity.ShowMainSensorScreen(sensorViewModel)
                        }
                    }
                }
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