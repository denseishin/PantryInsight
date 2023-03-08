package com.example.rikotodanactrl.inventory_screen.data.repository
import com.example.rikotodanactrl.core.HttpInfo
import com.example.rikotodanactrl.inventory_screen.data.remote.dtos.remoteInventoryItems
import com.example.rikotodanactrl.inventory_screen.data.remote.dtos.remoteSingleItem
import com.example.rikotodanactrl.inventory_screen.data.remote.dtos.remoteUpdateItem
import com.example.rikotodanactrl.inventory_screen.domain.repository.ItemRepo.ItemRepo
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*

class ItemRepoImpl (private val client: HttpClient): ItemRepo {
    override suspend fun getInventory(): List<remoteInventoryItems> {
        return client.get{url(HttpInfo.InvEndpoint)}
    }

    override suspend fun getItem(id: Int): remoteSingleItem {
        return client.get{url(HttpInfo.ItemEndpoint+"$id")}
    }

    override suspend fun updateItem(id: Int, item: remoteUpdateItem) {
        val res = client.put<String> {url(HttpInfo.ItemEndpoint+"$id")
            body = item
            contentType(ContentType.Application.Json)
        }
    }

    override suspend fun takeOutItem(id: Int) {
        val res = client.patch<String>{url(HttpInfo.ItemEndpoint+"$id")}
    }
}