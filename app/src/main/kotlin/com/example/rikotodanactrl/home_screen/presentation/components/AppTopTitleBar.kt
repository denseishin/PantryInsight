package com.example.rikotodanactrl.home_screen.presentation.components

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable

@Composable
fun AppTopTitleBar(onNavIconClick: () -> Unit)
{
    TopAppBar(title = { Text(text = "PantryInsight") },
        backgroundColor = MaterialTheme.colors.primary, contentColor = MaterialTheme.colors.onPrimary,
        navigationIcon = {
                IconButton(onClick = onNavIconClick) {
                    Icon(imageVector = Icons.Default.Menu, contentDescription = "toggle menu drawer")
                }
        })
}