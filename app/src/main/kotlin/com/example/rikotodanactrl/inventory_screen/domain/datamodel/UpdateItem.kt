package com.example.rikotodanactrl.inventory_screen.domain.datamodel
import java.time.LocalDate

class UpdateItem (val net_weight: Float,
val max_weight: Float,
val expiry_date: LocalDate?,
val batch: String,
val comment: String)
{
}