package com.example.rikotodanactrl.sensors_screen.presentation
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rikotodanactrl.sensors_screen.domain.repository.SensorRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SensorVM @Inject constructor(
    private val sensorValues: SensorRepo
): ViewModel()
{
    val tempVals = sensorValues.tempState
    val humidVals = sensorValues.humidState
    init {
        //sensorValues.sub()
        getValues()
    }
    private fun getValues()
    {
        viewModelScope.launch { sensorValues.cbOnHumidMsg() }
        viewModelScope.launch { sensorValues.cbOnTempMsg() }
    }
}