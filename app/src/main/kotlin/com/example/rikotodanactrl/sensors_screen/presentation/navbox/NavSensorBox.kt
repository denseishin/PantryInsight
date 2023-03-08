package com.example.rikotodanactrl.sensors_screen.presentation.navbox
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.rikotodanactrl.sensors_screen.presentation.SensorVM

@Composable
fun NavSensorBox(viewModel: SensorVM = hiltViewModel())
{
    val tempState = viewModel.tempVals.collectAsState()
    val humidState = viewModel.humidVals.collectAsState()
    Row(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.fillMaxWidth(0.5f)) {
            Text(text = "Temperature: ${tempState.value}")
        }
        Column(modifier = Modifier.fillMaxWidth(1f)) {
            Text(text = "Humidity: ${humidState.value}")
        }
    }
}