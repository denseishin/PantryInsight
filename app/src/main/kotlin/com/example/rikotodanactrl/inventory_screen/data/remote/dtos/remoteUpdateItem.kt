package com.example.rikotodanactrl.inventory_screen.data.remote.dtos

@kotlinx.serialization.Serializable
class remoteUpdateItem (val net_weight: Float?,
                        val max_weight: Float?,
                        val expiry_date: String?,
                        val batch: String?,
                        val comment: String?)
{
}