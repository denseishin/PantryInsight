package com.example.rikotodanactrl.inventory_screen.presentation.inventory_list
import com.example.rikotodanactrl.inventory_screen.domain.datamodel.InventoryItemResult

data class InvListState(
    val inventoryItems: List<InventoryItemResult> = emptyList(),
    val error: String = ""
)
