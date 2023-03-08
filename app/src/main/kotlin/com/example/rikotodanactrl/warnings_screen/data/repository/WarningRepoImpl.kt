package com.example.rikotodanactrl.warnings_screen.data.repository
import com.example.rikotodanactrl.core.HttpInfo
import com.example.rikotodanactrl.warnings_screen.data.remote.dtos.remoteExpiryRequest
import com.example.rikotodanactrl.warnings_screen.data.remote.dtos.remoteExpiryWarning
import com.example.rikotodanactrl.warnings_screen.data.remote.dtos.remoteRecallRequest
import com.example.rikotodanactrl.warnings_screen.data.remote.dtos.remoteRecallWarning
import com.example.rikotodanactrl.warnings_screen.domain.repository.WarningRepo
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*

class WarningRepoImpl(private val client: HttpClient):WarningRepo {
    override suspend fun getExpiring(before: remoteExpiryRequest): List<remoteExpiryWarning> {
        val res = client.post<List<remoteExpiryWarning>> {url(HttpInfo.ExpiryEndpoint)
            body = before
            contentType(ContentType.Application.Json)
        }
        return res
    }

    override suspend fun getRecalls(after: remoteRecallRequest): List<remoteRecallWarning> {
        val res = client.post<List<remoteRecallWarning>> {url(HttpInfo.RecallsEndpoints)
            body = after
            contentType(ContentType.Application.Json)
        }
        return res
    }
}