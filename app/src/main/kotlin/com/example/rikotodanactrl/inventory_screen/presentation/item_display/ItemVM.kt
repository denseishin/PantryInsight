package com.example.rikotodanactrl.inventory_screen.presentation.item_display
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rikotodanactrl.inventory_screen.domain.datamodel.UpdateItem
import com.example.rikotodanactrl.inventory_screen.domain.use_case.GetItemUC
import com.example.rikotodanactrl.inventory_screen.domain.use_case.TakeOutItemUC
import com.example.rikotodanactrl.inventory_screen.domain.use_case.UpdateItemUC
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class ItemVM @Inject constructor(
    private val getItemUC: GetItemUC,
    private val updateItemUC: UpdateItemUC,
    private val takeOutItemUC: TakeOutItemUC,
    savedStateHandle: SavedStateHandle
): ViewModel(){
    private val _state = mutableStateOf(ItemState())
    val state : State<ItemState> = _state
    private val _netWeight = mutableStateOf(String.format("%.1f",state.value.item?.net_weight))
    val netWeight : State<String> = _netWeight
    private val _expiry = mutableStateOf(state.value.item?.expiry_date ?:"")
    val expiry : State<String> = _expiry
    private val _batch = mutableStateOf(state.value.item?.batch ?:"")
    val batch : State<String> = _batch
    private val _comment = mutableStateOf(state.value.item?.comment ?:"")
    val comment : State<String> = _comment
    //private val _editEvent = MutableSharedFlow<EditEvent>()
    //val editEvent = _editEvent.asSharedFlow()

    init {
        savedStateHandle.get<String>("item_id")?.let { itemId ->
            getItem(itemId.toIntOrNull() ?: 0)
        }
    }

    private fun getItem(id: Int){
        getItemUC.use(id).onEach { result ->
            _state.value = ItemState(id, item = result, error = "")
            _netWeight.value = String.format("%.1f",state.value.item?.net_weight)
            _expiry.value = (state.value.item?.expiry_date ?: "").toString()
            _batch.value = state.value.item?.batch ?: ""
            _comment.value = state.value.item?.comment ?: ""
        }.launchIn(viewModelScope)
    }

    fun onEvent(event: EditEvent)
    {
        when(event) {
            is EditEvent.enteredNetWeight -> {
                _netWeight.value = event.value
            }
            is EditEvent.enteredExpiry -> {
                _expiry.value = LocalDate.of(event.value.first,event.value.second,event.value.third).toString()
            }
            is EditEvent.enteredBatch -> {
                _batch.value  = event.value
            }
            is EditEvent.enteredComment -> {
                _comment.value = event.value
            }
            is EditEvent.saveChanges -> {
                viewModelScope.launch {
                    val netWprep = netWeight.value.replace(",",".").toFloat()
                    val maxWprep = state.value.item?.max_weight ?: 0f
                    val datePrep = if (expiry.value.isNotBlank()) LocalDate.parse(expiry.value) else null
                    updateItemUC.exec(state.value.id,
                        UpdateItem(netWprep,maxWprep, datePrep ,batch.value,comment.value)
                    )
                }
                println("lol")
            }
            is EditEvent.takeOutItem -> {
                viewModelScope.launch {
                    takeOutItemUC.exec(state.value.id)
                }
            }
        }
    }
}