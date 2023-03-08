package com.example.rikotodanactrl.inventory_screen.presentation.item_display.components
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import com.example.rikotodanactrl.inventory_screen.domain.datamodel.PhotoResult

@Composable
fun singlePhotoUi(photo: PhotoResult)
{
    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Image(bitmap = photo.photo.asImageBitmap(), photo.taken_at, modifier = Modifier.fillMaxWidth())
        Text(text = photo.taken_at)
    }
}