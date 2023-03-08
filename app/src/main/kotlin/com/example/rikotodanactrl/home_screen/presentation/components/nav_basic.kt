package com.example.rikotodanactrl.home_screen.presentation.components
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.rikotodanactrl.inventory_screen.presentation.Screen
import com.example.rikotodanactrl.sensors_screen.presentation.navbox.NavSensorBox
import com.example.rikotodanactrl.home_screen.presentation.MenuItem

@Composable
fun DrawerItems(items: List<MenuItem>, navController: NavController, modifier: Modifier = Modifier,
               itemTextStyle: TextStyle = TextStyle(fontSize = 18.sp), onItemClick: () -> Unit)
{
    LazyColumn()
    {
        items(items) { item ->
            Row(modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .clickable {
                    navController.navigate(item.navRoute)
                    onItemClick()
                }) {
                if (item.icon != null)
                {
                    Icon(imageVector = item.icon, contentDescription = item.name)
                }
                Text(text = item.name, modifier = Modifier.weight(1f), style = itemTextStyle)
            }
        }
        item {
            NavSensorBox()
        }
    }
}

@Composable
fun DrawerContent(navController: NavController, onItemClick: () -> Unit)
{
    DrawerItems(items = listOf(
        MenuItem(id = "home",
                name = "Start",
                icon = Icons.Default.Home,
                navRoute = "home"),
        MenuItem(id = "inv",
                name = "Inventory",
                icon = Icons.Default.Info,
                navRoute = Screen.InvListScreen.route),
        MenuItem(id = "warn",
            name = "Food warnings",
            icon = Icons.Default.Warning,
            navRoute = "foodwarning")
    ), navController, onItemClick = onItemClick)
}