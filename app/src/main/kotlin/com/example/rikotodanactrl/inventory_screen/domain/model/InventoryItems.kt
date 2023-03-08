package com.example.rikotodanactrl.inventory_screen.domain.model
import com.example.rikotodanactrl.inventory_screen.data.remote.dtos.remoteInventoryItems
import java.time.LocalDate
import java.time.OffsetDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import kotlin.math.max

data class InventoryItems(
    val item_id: Int,
    val EAN: Long,
    val product_name: String,
    val net_weight: Float,
    val max_weight: Float,
    val current_weight: Float,
    val expiry_date: LocalDate?,
    val add_time: OffsetDateTime
    )
{
    fun getFillLevel(): Float
    {
        val netW = getNetWeight()
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
        val net_w = if (this.net_weight > 0f) this.net_weight else this.max_weight
        return net_w
    }
    fun localTs(): String
    {
        val zoneId: ZoneId = ZoneId.systemDefault()
        val fmt: DateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")
        val zoned = add_time.atZoneSameInstant(zoneId)
        return fmt.format(zoned)
    }
    companion object {
        fun convertFromRemoteDtos(remote: remoteInventoryItems): InventoryItems {
            val ex_date = if (remote.expiry_date != null) LocalDate.parse(remote.expiry_date) else null
            return InventoryItems(remote.item_id,remote.EAN,remote.product_name,remote.net_weight,
                remote.max_weight,remote.current_weight, expiry_date = ex_date,
                add_time = OffsetDateTime.parse(remote.add_time))
        }
    }
}