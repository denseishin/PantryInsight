package com.example.rikotodanactrl.warnings_screen.presentation.warnings.components
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.rikotodanactrl.warnings_screen.domain.datamodel.RecallWarning

@Composable
fun recallDisplay(item: RecallWarning) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()) {
        Card(backgroundColor = Color.Red) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(16.dp)
            ) {
                Row(horizontalArrangement = Arrangement.Start) {
                    Text(text = item.name)
                }
                Divider(thickness = 2.dp, color = Color.Black)
                Text(text = item.name)
                Text(text = "GTIN ${item.UPC}")
                Text(text = item.reason)
                if (item.batches != null) {
                    if (item.batches.isNotEmpty()) {
                        Row(modifier = Modifier.fillMaxWidth()) {
                            Text(text = "batches: ")
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.Start
                            ) {
                                var batchnos = ""
                                for (batchno in item.batches) {
                                    batchnos = batchnos + batchno + " "
                                }
                                Text(text = batchnos)
                            }
                        }
                    }
                }
                if (item.expiry_dates != null) {
                    if (item.expiry_dates.isNotEmpty()) {
                        Row(modifier = Modifier.fillMaxWidth()) {
                            Text(text = "expiry dates: ")
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.Start
                            ) {
                                var datelist: String = ""
                                for (date in item.expiry_dates) {
                                    datelist = date.toString() + " "
                                }
                                Text(text = datelist.toString())
                            }
                        }
                    }
                }
            }
        }
    }
}