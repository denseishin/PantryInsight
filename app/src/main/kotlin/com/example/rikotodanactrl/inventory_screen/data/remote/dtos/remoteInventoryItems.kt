package com.example.rikotodanactrl.inventory_screen.data.remote.dtos

@kotlinx.serialization.Serializable
data class remoteInventoryItems (
    val EAN: Long,
    val current_weight: Float,
    val item_id: Int,
    val net_weight: Float,
    val max_weight: Float,
    val expiry_date: String?,
    val product_name: String,
    val add_time: String
    )
{
    /*fun toInventoryItems(): InventoryItems
    {
        val ex_date = if (expiry_date != null) LocalDate.parse(expiry_date) else null
        return InventoryItems(item_id,EAN,product_name,net_weight,max_weight,current_weight, expiry_date = ex_date,
        add_time = OffsetDateTime.parse(add_time))
    }*/
}