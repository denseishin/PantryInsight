package com.example.rikotodanactrl.inventory_screen.domain.model
import com.example.rikotodanactrl.inventory_screen.data.remote.dtos.remoteSingleItem
import java.time.LocalDate
import kotlin.math.max

data class SingleItem (
    val item_id: Int,
    val EAN: Long,
    val product_name: String,
    val net_weight: Float,
    val max_weight: Float,
    val current_weight: Float,
    val expiry_date: LocalDate?,
    val batch: String?,
    val comment: String?,
    val photos: List<Photo>
    ){
    fun getFillLevel(): Float
    {
        val netW = getNetWeight()
        //val minW = if (netW < max_weight) netW else max_weight //min(getNetWeight(), this.max_weight)
        val currW = getCurrentNetWeight()
        val level = (currW / netW)
        return level
    }
    fun getCurrentNetWeight(): Float
    {
        val net_w = getNetWeight()
        val maxW = max(this.max_weight, net_w)
        val wrapperW = maxW - net_w
        val currW = this.current_weight - wrapperW
        return if (currW >= 0f) currW else 0f
    }
    fun getNetWeight():Float
    {
        return if (this.net_weight > 0f) this.net_weight else this.max_weight
    }
    companion object{
        fun convertFromRemoteDtos(remote: remoteSingleItem): SingleItem{
            val ex_date = if (remote.expiry_date != null) LocalDate.parse(remote.expiry_date) else null
            return SingleItem(remote.item_id,remote.EAN,remote.product_name,remote.net_weight,
                remote.max_weight,remote.current_weight, expiry_date = ex_date, remote.batch,
                remote.comment, photos = remote.photos.map {Photo.convertFromRemoteDtos(it)})
        }
    }
}