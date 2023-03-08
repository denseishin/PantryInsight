package com.example.rikotodanactrl.inventory_screen.presentation.inventory_list.components
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.rikotodanactrl.inventory_screen.domain.datamodel.InventoryItemResult

@Preview
@Composable
fun InvListItem(item: InventoryItemResult =
                    InventoryItemResult(99,999,"llll",10f,12f,8f,6f,0.5f,null,
                    "2022-01-01T00:00:00+00:00"
                    ), onItemClick: (InventoryItemResult) -> Unit = {})
{
    Column(modifier = Modifier
        .fillMaxWidth()
        //.wrapContentHeight()
        .clickable { onItemClick(item) }
        .padding(20.dp)) {
        Row (modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            ,horizontalArrangement = Arrangement.SpaceEvenly) {
            Column(modifier = Modifier.fillMaxWidth(0.6f)) {
                Text(text = if (item.product_name.isNotBlank()) item.product_name else "${item.EAN}",
                    overflow = TextOverflow.Ellipsis)
            }
            Text(text = String.format("%.1f",item.net_weight) + "g")
            Text(text = String.format("%.1f",item.current_net_weight)+"g")
        }
        Row(modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()) {
            LinearProgressIndicator(item.fill_level, modifier = Modifier.fillMaxWidth())
        }
        Row(modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
            horizontalArrangement = Arrangement.SpaceEvenly) {
            if (item.expiry_date != null)
            {
                Text(text = "Best by ${item.expiry_date} ")
            }
            Text(text = "Added ${item.add_time}")
        }
    }
}