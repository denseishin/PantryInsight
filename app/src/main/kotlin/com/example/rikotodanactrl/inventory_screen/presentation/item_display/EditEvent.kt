package com.example.rikotodanactrl.inventory_screen.presentation.item_display

sealed class EditEvent {
    object saveChanges: EditEvent()
    object takeOutItem: EditEvent()
    data class enteredNetWeight(val value: String): EditEvent()
    //data class enteredExpiry(val value: String): EditEvent()
    data class enteredBatch(val value: String): EditEvent()
    data class enteredComment(val value: String): EditEvent()
    data class enteredExpiry(val value: Triple<Int,Int,Int>): EditEvent()
}
