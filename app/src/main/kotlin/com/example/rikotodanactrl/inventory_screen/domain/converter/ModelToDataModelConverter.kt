package com.example.rikotodanactrl.inventory_screen.domain.converter
import com.example.rikotodanactrl.inventory_screen.domain.datamodel.InventoryItemResult
import com.example.rikotodanactrl.inventory_screen.domain.datamodel.PhotoResult
import com.example.rikotodanactrl.inventory_screen.domain.datamodel.SimpleItemResult
import com.example.rikotodanactrl.inventory_screen.domain.model.InventoryItems
import com.example.rikotodanactrl.inventory_screen.domain.model.Photo
import com.example.rikotodanactrl.inventory_screen.domain.model.SingleItem

class ModelToDataModelConverter: IModelToDataModelConverter {
    override fun photo(photo: Photo): PhotoResult {
        return PhotoResult(photo.toBitmap(), photo.taken_at.toString())
    }

    override fun singleItem(item: SingleItem): SimpleItemResult {
        return SimpleItemResult(item.item_id, item.EAN, item.product_name, item.getNetWeight(),
            item.max_weight,item.current_weight, item.getCurrentNetWeight(),item.getFillLevel(),
            item.expiry_date?.toString() ?: null, item.batch, item.comment, photos = item.photos.map { this.photo(it) })
    }

    override fun invItem(item: InventoryItems): InventoryItemResult {
        return InventoryItemResult(item.item_id, item.EAN, item.product_name, item.getNetWeight(),
            item.max_weight,item.current_weight, item.getCurrentNetWeight(),item.getFillLevel(),
            item.expiry_date?.toString() ?: null, item.localTs())
    }
}