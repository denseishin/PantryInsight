package com.example.rikotodanactrl.warnings_screen.domain.use_case

import com.example.rikotodanactrl.warnings_screen.domain.datamodel.ExpiryRequest
import com.example.rikotodanactrl.warnings_screen.domain.datamodel.ExpiryWarning
import com.example.rikotodanactrl.warnings_screen.domain.datamodel.RecallRequest
import com.example.rikotodanactrl.warnings_screen.domain.datamodel.RecallWarning
import com.example.rikotodanactrl.warnings_screen.domain.repository.WarningRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.time.LocalDate
import javax.inject.Inject

class GetFoodWarningsUC @Inject constructor(
    private val repo: WarningRepo
) {
    fun getExpiries(date: LocalDate): Flow<List<ExpiryWarning>> = flow {
        emit(repo.getExpiring(ExpiryRequest(date).toRemote()).map { ExpiryWarning.convertFromRemote(it) })
    }

    fun getRecalls(date: LocalDate): Flow<List<RecallWarning>> = flow {
        emit(repo.getRecalls(RecallRequest(date).toRemote()).map { RecallWarning.convertFromRemote(it) })
    }
}