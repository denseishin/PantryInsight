package com.example.rikotodanactrl

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.rikotodanactrl.inventory_screen.presentation.Screen
import com.example.rikotodanactrl.inventory_screen.presentation.inventory_list.components.InvListScreen
import com.example.rikotodanactrl.inventory_screen.presentation.item_display.components.SingleItemScreen
import com.example.rikotodanactrl.sensors_screen.domain.repository.SensorRepo
import com.example.rikotodanactrl.warnings_screen.presentation.warnings.components.FoodWarningScreen
import com.example.rikotodanactrl.home_screen.presentation.components.DrawerContent
import com.example.rikotodanactrl.home_screen.presentation.components.HomeScreen
import com.example.rikotodanactrl.home_screen.presentation.components.AppTopTitleBar
import com.example.rikotodanactrl.ui.theme.SmartPantryControlTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var MqttClient: SensorRepo
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tmp = MqttClient.connect()
        setContent {
            SmartPantryControlTheme {
                    val navCtrl = rememberNavController()
                    val scaffoldState = rememberScaffoldState()
                    val scope = rememberCoroutineScope()
                    Scaffold(modifier = Modifier.padding(0.dp), scaffoldState = scaffoldState,
                        backgroundColor = MaterialTheme.colors.background,
                        topBar = {
                        AppTopTitleBar {
                            scope.launch { scaffoldState.drawerState.open() }
                        }
                        }, drawerContent = { DrawerContent(navController = navCtrl) {
                            scope.launch { scaffoldState.drawerState.close() }
                        }
                        })
                    {  it ->
                        NavHost(navController = navCtrl, startDestination = "home")
                        {
                            composable(route = Screen.InvListScreen.route) {
                                InvListScreen(navController = navCtrl)
                            }
                            composable(route = Screen.ItemViewScreen.route + "/{item_id}") {
                                SingleItemScreen(navController = navCtrl)
                            }
                            composable(route = "home"){
                                HomeScreen(navController = navCtrl)
                            }
                            composable(route = "foodwarning"){
                                FoodWarningScreen(navController = navCtrl)
                            }
                        }
                    }
            }
        }
    }

    override fun onStop() {
        super.onStop()
        MqttClient.disconnect()
    }
}