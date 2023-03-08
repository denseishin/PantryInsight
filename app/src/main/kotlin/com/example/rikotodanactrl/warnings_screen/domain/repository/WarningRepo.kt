package com.example.rikotodanactrl.warnings_screen.domain.repository
import com.example.rikotodanactrl.warnings_screen.data.remote.dtos.remoteExpiryRequest
import com.example.rikotodanactrl.warnings_screen.data.remote.dtos.remoteExpiryWarning
import com.example.rikotodanactrl.warnings_screen.data.remote.dtos.remoteRecallRequest
import com.example.rikotodanactrl.warnings_screen.data.remote.dtos.remoteRecallWarning

interface WarningRepo {
    suspend fun getExpiring(before: remoteExpiryRequest): List<remoteExpiryWarning>
    suspend fun getRecalls(after: remoteRecallRequest): List<remoteRecallWarning>
}