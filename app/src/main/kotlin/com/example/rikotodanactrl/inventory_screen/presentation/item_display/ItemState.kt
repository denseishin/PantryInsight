package com.example.rikotodanactrl.inventory_screen.presentation.item_display
import com.example.rikotodanactrl.inventory_screen.domain.datamodel.SimpleItemResult

data class ItemState(
    val id: Int = 0,
    val item: SimpleItemResult? = null,
    val error: String = ""
)
