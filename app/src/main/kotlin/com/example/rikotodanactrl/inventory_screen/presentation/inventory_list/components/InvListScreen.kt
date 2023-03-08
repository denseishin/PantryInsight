package com.example.rikotodanactrl.inventory_screen.presentation.inventory_list.components
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.rikotodanactrl.inventory_screen.presentation.Screen
import com.example.rikotodanactrl.inventory_screen.presentation.inventory_list.InvListVM

@Composable
fun InvListScreen(
    navController: NavController,
    viewModel: InvListVM = hiltViewModel()
)
{
    val state = viewModel.state.value
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(state.inventoryItems){ item ->
                InvListItem(item, onItemClick = {
                    navController.navigate(Screen.ItemViewScreen.route+"/${item.item_id}")
                }
                )
            }
        }
        if (state.error.isNotBlank())
        {
            //Snackbar() { Text(state.error) }
            println("fuck u")
        }
    }
}