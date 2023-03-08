package com.example.rikotodanactrl.inventory_screen.domain.converter
import com.example.rikotodanactrl.inventory_screen.domain.datamodel.InventoryItemResult
import com.example.rikotodanactrl.inventory_screen.domain.datamodel.PhotoResult
import com.example.rikotodanactrl.inventory_screen.domain.datamodel.SimpleItemResult
import com.example.rikotodanactrl.inventory_screen.domain.model.InventoryItems
import com.example.rikotodanactrl.inventory_screen.domain.model.Photo
import com.example.rikotodanactrl.inventory_screen.domain.model.SingleItem

interface IModelToDataModelConverter {
    fun photo(photo: Photo): PhotoResult
    fun singleItem(item: SingleItem): SimpleItemResult
    fun invItem(item: InventoryItems): InventoryItemResult
}