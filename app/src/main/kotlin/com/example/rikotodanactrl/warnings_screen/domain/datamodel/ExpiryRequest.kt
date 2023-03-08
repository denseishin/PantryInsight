package com.example.rikotodanactrl.warnings_screen.domain.datamodel
import com.example.rikotodanactrl.warnings_screen.data.remote.dtos.remoteExpiryRequest
import java.time.LocalDate

data class ExpiryRequest(val before: LocalDate)
{
    fun toRemote(): remoteExpiryRequest
    {
        return remoteExpiryRequest(before = before.toString())
    }
}