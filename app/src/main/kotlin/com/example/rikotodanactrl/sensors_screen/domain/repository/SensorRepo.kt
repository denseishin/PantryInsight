package com.example.rikotodanactrl.sensors_screen.domain.repository

import kotlinx.coroutines.flow.StateFlow

interface SensorRepo {
    val tempState: StateFlow<String>
    val humidState: StateFlow<String>
    fun sub()
    fun connect()
    fun disconnect()
    suspend fun cbOnTempMsg()
    suspend fun cbOnHumidMsg()
}