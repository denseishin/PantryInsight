package com.example.rikotodanactrl.inventory_screen.presentation.item_display.components
import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.rikotodanactrl.inventory_screen.presentation.Screen
import com.example.rikotodanactrl.inventory_screen.presentation.item_display.EditEvent
import com.example.rikotodanactrl.inventory_screen.presentation.item_display.ItemVM
import java.util.Calendar

@Composable
fun SingleItemScreen(navController: NavController, viewModel: ItemVM = hiltViewModel())
{
    val state = viewModel.state.value
    //val maxWeightState = viewModel.maxWeight.value
    val netWeightState = viewModel.netWeight.value
    val expDateState = viewModel.expiry.value
    val batchState = viewModel.batch.value
    val commentState = viewModel.comment.value
    val cal = Calendar.getInstance()
    val curYear = cal.get(Calendar.YEAR)
    val curMon = cal.get(Calendar.MONTH)
    val curDay = cal.get(Calendar.DAY_OF_MONTH)
    val currContext = LocalContext.current
    val expDatePicker = DatePickerDialog(currContext, {
            _: DatePicker, pickYear: Int, pickMon: Int, pickDayoM: Int ->
            viewModel.onEvent(EditEvent.enteredExpiry(Triple(pickYear,pickMon+1,pickDayoM)))
        }, curYear, curMon, curDay
    )

    Box(modifier = Modifier.fillMaxSize()) {
        //state.item?.let { item ->
        val item = state.item
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            item {
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "item")
                    TextField(value = "${item?.EAN ?: ""}", onValueChange = {}, readOnly = true,
                        label = {Text("GTIN")}, modifier = Modifier.fillMaxWidth())
                    //Text(text = "${item.EAN}")
                    TextField(value = item?.product_name ?: "", onValueChange = {}, readOnly = true,
                        label = {Text("product")}, modifier = Modifier.fillMaxWidth())
                    Row(modifier = Modifier.fillMaxWidth(1f)) {
                        TextField(value = netWeightState, onValueChange = {viewModel.onEvent(EditEvent.enteredNetWeight(it))}, readOnly = false,
                            label = {Text("net weight")}, modifier = Modifier.fillMaxWidth(0.33f))
                        TextField(value = String.format("%.1f",item?.max_weight ?: 0f), onValueChange = {}, readOnly = true,
                            label = {Text("max weight")}, modifier = Modifier.fillMaxWidth(0.5f))
                        TextField(value = String.format("%.1f",item?.current_weight ?: 0f), onValueChange = {}, readOnly = true,
                            label = {Text("current weight")}, modifier = Modifier.fillMaxWidth(1f))
                        }
                    LinearProgressIndicator(item?.fill_level ?: 0f, modifier = Modifier.fillMaxWidth())
                    Row(modifier = Modifier.fillMaxWidth()){
                        TextField(value = expDateState, onValueChange = {}, readOnly = true,
                            label = {Text("expiry date")}, modifier = Modifier.fillMaxWidth(0.5f)
                                .clickable {expDatePicker.show()}, enabled = false)
                        TextField(value = batchState, onValueChange = {viewModel.onEvent(EditEvent.enteredBatch(it))}, readOnly = false,
                            label = {Text("batch")}, modifier = Modifier.fillMaxWidth(1f))
                    }
                    TextField(value = commentState, onValueChange = {viewModel.onEvent(EditEvent.enteredComment(it))}, label = { Text(text = "comment")},
                    modifier = Modifier.fillMaxWidth())
                    Row() {
                        Button(onClick = {viewModel.onEvent(EditEvent.saveChanges)
                        navController.navigate(Screen.InvListScreen.route)}) {
                            Text(text = "Submit")
                        }
                        Button(onClick = {viewModel.onEvent(EditEvent.takeOutItem)
                            navController.navigate(Screen.InvListScreen.route)}) {
                            Text(text = "Take out")
                        }
                        Button(onClick = {navController.navigate(Screen.InvListScreen.route)},) {
                            Text(text = "Cancel")
                        }
                    }
                    if (item != null) {
                        if (item.photos.isNotEmpty()) {
                            Text(text = "photos")
                            //Text(text = item.product_name)
                            //Text(text = "${item.net_weight}g ${item.max_weight}g ${item.current_weight}")
                            for (photo in item.photos) {
                                singlePhotoUi(photo = photo)
                            }
                        }
                    }
                    }
                }
            }
        //}
        if (state.error.isNotBlank())
        {
            Snackbar() { Text(state.error) }
            println("fuck u")
        }
    }
}