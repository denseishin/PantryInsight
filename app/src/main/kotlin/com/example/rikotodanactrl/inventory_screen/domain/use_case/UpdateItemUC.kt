package com.example.rikotodanactrl.inventory_screen.domain.use_case
import com.example.rikotodanactrl.inventory_screen.data.remote.dtos.remoteUpdateItem
import com.example.rikotodanactrl.inventory_screen.domain.datamodel.UpdateItem
import com.example.rikotodanactrl.inventory_screen.domain.repository.ItemRepo.ItemRepo
import javax.inject.Inject

class UpdateItemUC @Inject constructor (val repo: ItemRepo) {
    suspend fun exec(id: Int, item: UpdateItem)
    {
        this.repo.updateItem(id, remoteUpdateItem(item.net_weight,item.max_weight, if (item.expiry_date != null) item.expiry_date.toString() else null,
                            item.batch,item.comment))
    }
}