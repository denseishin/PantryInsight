package com.example.rikotodanactrl.inventory_screen.presentation.inventory_list
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rikotodanactrl.inventory_screen.domain.use_case.GetInventoryUC
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class InvListVM @Inject constructor(
    private val getInventoryUC: GetInventoryUC
): ViewModel(){
    private val _state = mutableStateOf(InvListState())
    val state : State<InvListState> = _state

    init {
        //println("Created InvListVM")
        getItems()
    }

    private fun getItems(){
        getInventoryUC.use().onEach { result ->
            _state.value = InvListState(inventoryItems = result, error = "")
        }.launchIn(viewModelScope)
    }
}