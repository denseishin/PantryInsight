package com.example.rikotodanactrl.inventory_screen.domain.use_case
import com.example.rikotodanactrl.inventory_screen.domain.repository.ItemRepo.ItemRepo
import javax.inject.Inject

class TakeOutItemUC @Inject constructor (val repo: ItemRepo) {
    suspend fun exec(id: Int) {
        this.repo.takeOutItem(id)
    }
}