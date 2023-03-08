package com.example.rikotodanactrl.warnings_screen.domain.datamodel
import com.example.rikotodanactrl.warnings_screen.data.remote.dtos.remoteRecallRequest
import java.time.LocalDate

data class RecallRequest(val after: LocalDate)
{
    fun toRemote(): remoteRecallRequest
    {
        return remoteRecallRequest(after = after.toString())
    }
}