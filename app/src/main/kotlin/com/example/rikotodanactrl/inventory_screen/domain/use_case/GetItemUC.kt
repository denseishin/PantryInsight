package com.example.rikotodanactrl.inventory_screen.domain.use_case
import com.example.rikotodanactrl.inventory_screen.domain.converter.IModelToDataModelConverter
import com.example.rikotodanactrl.inventory_screen.domain.datamodel.SimpleItemResult
import com.example.rikotodanactrl.inventory_screen.domain.model.SingleItem
import com.example.rikotodanactrl.inventory_screen.domain.repository.ItemRepo.ItemRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetItemUC @Inject constructor (private val repo: ItemRepo, private val converter: IModelToDataModelConverter) {
    fun use(id: Int): Flow<SimpleItemResult> = flow {
        emit(converter.singleItem(SingleItem.convertFromRemoteDtos(repo.getItem(id))))
    }
}