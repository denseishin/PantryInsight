package com.example.rikotodanactrl.inventory_screen.domain.repository.ItemRepo
import com.example.rikotodanactrl.inventory_screen.data.remote.dtos.remoteInventoryItems
import com.example.rikotodanactrl.inventory_screen.data.remote.dtos.remoteSingleItem
import com.example.rikotodanactrl.inventory_screen.data.remote.dtos.remoteUpdateItem

interface ItemRepo {

    suspend fun getInventory(): List<remoteInventoryItems>

    suspend fun getItem(id: Int): remoteSingleItem

    suspend fun updateItem(id: Int, item: remoteUpdateItem)

    suspend fun takeOutItem(id: Int)

    /*companion object {
        fun create(): ItemRepo {
                return ItemRepoImpl(
                    client = HttpClient(Android) {
                        install(JsonFeature){
                            serializer = KotlinxSerializer()
                        }
                    }
                )
        }
    }*/

}