package com.example.rikotodanactrl.ui.theme

import android.annotation.SuppressLint
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.material.darkColors
import androidx.compose.ui.graphics.Color

//@SuppressLint("ConflictingOnColor")
private val OrangeColorPalette = darkColors(
    primary = colorPrimary,
    background = colorBg,
    onBackground = Color.Black,
    onPrimary = Color.Black,
    onSecondary = Color.Black,
    onSurface = Color.Black,
    surface = lightora
)

@Composable
fun SmartPantryControlTheme (content: @Composable() () -> Unit) {
    val colors = OrangeColorPalette
    MaterialTheme(colors = colors, content = content)
}