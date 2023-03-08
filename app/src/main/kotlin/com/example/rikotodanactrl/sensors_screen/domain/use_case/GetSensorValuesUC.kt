package com.example.rikotodanactrl.sensors_screen.domain.use_case
import com.example.rikotodanactrl.sensors_screen.domain.repository.SensorRepo
import javax.inject.Inject

class GetSensorValuesUC @Inject constructor(
    private val repo: SensorRepo
) {
    suspend fun collectTemp()
    {
        repo
    }
}