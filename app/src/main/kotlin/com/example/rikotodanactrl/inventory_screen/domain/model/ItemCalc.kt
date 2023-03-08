package com.example.rikotodanactrl.inventory_screen.domain.model
import kotlin.math.max

class ItemCalc(
) {
    fun getFillLevel(net_weight: Float, current_net: Float): Float
    {
        val level = (current_net / net_weight)
        return level
    }
    fun getCurrentNetWeight(net_weight: Float, max_weight: Float, current_weight: Float): Float
    {
        val maxW = max(max_weight, net_weight)
        val wrapperW = maxW - net_weight
        val currW = current_weight - wrapperW
        return if (currW >= 0f) currW else 0f
    }
    fun getNetWeight(net_weight: Float, max_weight: Float): Float
    {
        val net_w = if (net_weight > 0f) net_weight else max_weight
        return net_w
    }
}