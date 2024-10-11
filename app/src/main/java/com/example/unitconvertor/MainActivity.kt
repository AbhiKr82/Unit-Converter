package com.example.unitconvertor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.unitconvertor.screens.Area
import com.example.unitconvertor.screens.Length
import com.example.unitconvertor.screens.MainScreen
import com.example.unitconvertor.screens.Mass
import com.example.unitconvertor.screens.Temperature
import com.example.unitconvertor.screens.Time
import com.example.unitconvertor.screens.Volume
import com.example.unitconvertor.ui.theme.UnitConvertorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UnitConvertorTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                   UnitConvertor(Modifier.padding(innerPadding))
                }

            }
        }
    }
}

@Composable
fun UnitConvertor(modifier: Modifier=Modifier){
    val navController= rememberNavController()

    NavHost(navController=navController, startDestination =Screens.homeScreen) {
        composable(Screens.homeScreen){
            MainScreen(navController)
        }
        composable(Screens.lengthScreen){
           Length(navController)
        }
        composable(Screens.areaScreen){
            Area(navController)
        }
        composable(Screens.volumeScreen){
            Volume(navController)
        }
        composable(Screens.massScreen){
            Mass(navController)
        }
        composable(Screens.tempScreen){
            Temperature(navController)
        }
        composable(Screens.timeScreen){
            Time(navController)
        }
    }
}



