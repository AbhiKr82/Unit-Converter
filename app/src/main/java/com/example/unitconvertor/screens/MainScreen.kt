package com.example.unitconvertor.screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.unitconvertor.R
import com.example.unitconvertor.Screens

@Composable
fun MainScreen(navController: NavController) {

    Column(modifier = Modifier.fillMaxSize().padding(25.dp),
        horizontalAlignment = Alignment.CenterHorizontally)
    {
        Spacer(Modifier.height(50.dp))
        Text(
            text="Unit Convertor",
            fontSize = 35.sp,
            fontFamily = FontFamily.Monospace
        )

        Spacer(Modifier.height(100.dp))
        Image(painter = painterResource(R.drawable.unit11), contentScale = ContentScale.Fit, contentDescription = "unit")
        Spacer(Modifier.height(50.dp))
        LazyVerticalGrid(GridCells.Fixed(2)) {
            items(list){
                map.get(it)?.let { it1 -> Baxlayout(it, it1, navController) }
            }
        }





    }

}

val list = listOf("Length", "Area", "Volume", "Mass", "Temperature", "Time")
val map = mapOf(
    "Length" to Screens.lengthScreen,
    "Area" to Screens.areaScreen,
    "Volume" to Screens.volumeScreen,
    "Mass" to Screens.massScreen,
    "Temperature" to Screens.tempScreen,
    "Time" to Screens.timeScreen
)


@Composable
fun Baxlayout(str:String,scr:String,navController: NavController){
    Column(
        modifier = Modifier.padding(20.dp).height(50.dp).width(150.dp)
            .background(color = Color.LightGray, shape = RoundedCornerShape(20.dp))
            .clickable(onClick = {navController.navigate(scr)})
        ,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){

        Text(str, fontSize = 20.sp, fontWeight = FontWeight.SemiBold)

    }
}