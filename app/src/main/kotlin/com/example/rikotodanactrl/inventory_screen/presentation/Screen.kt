package com.example.rikotodanactrl.inventory_screen.presentation

sealed class Screen (val route: String) {
    object InvListScreen: Screen("inventory_screen")
    object ItemViewScreen: Screen("item_screen")
}
