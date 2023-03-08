package com.example.rikotodanactrl.inventory_screen.domain.datamodel

data class InventoryItemResult(
    val item_id: Int,
    val EAN: Long,
    val product_name: String,
    val net_weight: Float,
    val max_weight: Float,
    val current_weight: Float,
    val current_net_weight: Float,
    val fill_level: Float,
    val expiry_date: String?,
    val add_time: String
)
