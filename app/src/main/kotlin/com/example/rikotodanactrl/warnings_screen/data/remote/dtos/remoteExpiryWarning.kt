package com.example.rikotodanactrl.warnings_screen.data.remote.dtos

@kotlinx.serialization.Serializable
data class remoteExpiryWarning(
    val item_id: Int,
    val EAN: Long,
    val name: String,
    val expiry_date: String?,
    val net_weight: Float?,
    val max_weight: Float?,
    val current_weight: Float
    )
