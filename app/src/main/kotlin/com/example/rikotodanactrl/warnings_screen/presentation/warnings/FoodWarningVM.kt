package com.example.rikotodanactrl.warnings_screen.presentation.warnings
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rikotodanactrl.warnings_screen.domain.use_case.GetFoodWarningsUC
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class FoodWarningVM @Inject constructor(private val getFoodWarningsUC: GetFoodWarningsUC): ViewModel()
{
    private val _recallState = mutableStateOf(RecallWarningStates())
    val recallState : State<RecallWarningStates> = _recallState
    private val _expiryState = mutableStateOf(ExpiryWarningStates())
    val expiryState : State<ExpiryWarningStates> = _expiryState

    init {
        getItems()
    }
    private fun getItems(){
        val tmp = getFoodWarningsUC.getExpiries(LocalDate.now())
        tmp.onEach { result ->
            _expiryState.value = ExpiryWarningStates(expiryWarnings = result, error = "")
        }.launchIn(viewModelScope)
        val tmp2 = getFoodWarningsUC.getRecalls(LocalDate.now().minusDays(30))
        tmp2.onEach { result ->
            _recallState.value = RecallWarningStates(recallWarnings = result, error = "")
        }.launchIn(viewModelScope)
    }
}