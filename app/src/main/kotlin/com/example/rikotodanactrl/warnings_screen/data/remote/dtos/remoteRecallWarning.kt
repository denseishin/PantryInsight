package com.example.rikotodanactrl.warnings_screen.data.remote.dtos

@kotlinx.serialization.Serializable
data class remoteRecallWarning(
    val UPC: Long,
    val name: String,
    val reason: String,
    val batches: List<String>?,
    val expiry_dates: List<String>?,
    val issued_at: String,
    val info_url: String
)
