package com.example.rikotodanactrl.warnings_screen.presentation.warnings.components
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.rikotodanactrl.warnings_screen.presentation.warnings.FoodWarningVM

@Composable
fun FoodWarningScreen(
    navController: NavController,
    viewModel: FoodWarningVM = hiltViewModel()
)
{
    LazyColumn(
        Modifier
            .fillMaxSize()
            .padding(16.dp)) {
        item {
            Text(text = "Expiring food")
        }
        item {
            Spacer(modifier = Modifier.height(16.dp))
        }
        items(viewModel.expiryState.value.expiryWarnings) { item ->
            expiryDisplay(item)
            Spacer(modifier = Modifier.height(16.dp))
        }
        item {
            Text(text = "Recalled food")
        }
        items(viewModel.recallState.value.recallWarnings) { item ->
            recallDisplay(item)
        }
    }
}