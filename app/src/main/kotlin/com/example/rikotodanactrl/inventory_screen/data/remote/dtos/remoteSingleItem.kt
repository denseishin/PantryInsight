package com.example.rikotodanactrl.inventory_screen.data.remote.dtos

@kotlinx.serialization.Serializable
class remoteSingleItem (
    val item_id: Int,
    val EAN: Long,
    val product_name: String,
    val net_weight: Float,
    val max_weight: Float,
    val current_weight: Float,
    val expiry_date: String?,
    val batch: String?,
    val comment: String?,
    val photos: List<remotePhoto>,
    val shelf: Int
    ){
    /*fun toSingleItem(): SingleItem
    {
        val ex_date = if (expiry_date != null) LocalDate.parse(expiry_date) else null
        return SingleItem(item_id,EAN,product_name,net_weight,max_weight,current_weight, expiry_date =
        ex_date, batch, comment, photos = photos.map { it.toPhoto() })
    }*/
}