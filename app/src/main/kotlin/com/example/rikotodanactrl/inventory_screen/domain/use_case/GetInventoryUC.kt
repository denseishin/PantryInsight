package com.example.rikotodanactrl.inventory_screen.domain.use_case
import com.example.rikotodanactrl.inventory_screen.domain.converter.IModelToDataModelConverter
import com.example.rikotodanactrl.inventory_screen.domain.datamodel.InventoryItemResult
import com.example.rikotodanactrl.inventory_screen.domain.model.InventoryItems
import com.example.rikotodanactrl.inventory_screen.domain.repository.ItemRepo.ItemRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetInventoryUC @Inject constructor(
    private val repo: ItemRepo,
    private val converter: IModelToDataModelConverter
) {
    fun use(): Flow<List<InventoryItemResult>> = flow {
        emit(repo.getInventory().map { converter.invItem(InventoryItems.convertFromRemoteDtos(it)) })
    }
}