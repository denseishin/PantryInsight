package com.example.rikotodanactrl.warnings_screen.domain.datamodel
import com.example.rikotodanactrl.warnings_screen.data.remote.dtos.remoteExpiryWarning
import java.time.LocalDate
import kotlin.math.max
import kotlin.math.min

data class ExpiryWarning(
    val item_id: Int,
    val EAN: Long,
    val name: String,
    val expiry_date: LocalDate?,
    val net_weight: Float?,
    val max_weight: Float?,
    val current_weight: Float
    )
{
    fun getFillLevel(): Float
    {
        val minW = min(getNetWeight(), this.max_weight ?: Float.POSITIVE_INFINITY)
        return  if (minW != Float.POSITIVE_INFINITY && minW > 0f) (getCurrentNetWeight() / minW) else 1f
    }
    fun getCurrentNetWeight(): Float
    {
        val net_w = getNetWeight()
        val maxW = max(this.max_weight ?: 0f, net_w)
        val wrapperW = maxW - net_w
        return (this.current_weight - wrapperW)
    }
    fun getNetWeight():Float
    {
        return if ((this.net_weight ?: 0f) > 0f) this.net_weight ?: 0f else this.max_weight ?: 0f
    }
    companion object{
        fun convertFromRemote(remote: remoteExpiryWarning): ExpiryWarning
        {
            val ex_date = if (remote.expiry_date != null) LocalDate.parse(remote.expiry_date) else null
            return ExpiryWarning(remote.item_id, remote.EAN, remote.name, ex_date, remote.net_weight,
            remote.max_weight, remote.current_weight)
        }
    }
}