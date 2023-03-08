package com.example.rikotodanactrl.warnings_screen.presentation.warnings.components
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.rikotodanactrl.warnings_screen.domain.datamodel.ExpiryWarning
import java.time.LocalDate

@Preview
@Composable
fun expiryDisplay(item: ExpiryWarning = ExpiryWarning(1,22,"Test", LocalDate.now(),200f,400f,250f))
{
    Card(backgroundColor = Color.Yellow) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(16.dp)
        ) {
            Row(horizontalArrangement = Arrangement.Start) {
                Text(text = item.expiry_date.toString())
            }
            Divider(thickness = 2.dp, color = Color.Black)
            Text(text = item.name)
            Text(text = "GTIN ${item.EAN}")
            Text(text = String.format("%.2f", item.getFillLevel() * 100) + "% remaining")
        }
    }
}