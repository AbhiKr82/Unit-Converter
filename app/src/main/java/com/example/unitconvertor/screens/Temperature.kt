package com.example.unitconvertor.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.unitconvertor.fontFamily

@Composable
fun Temperature (navController: NavController){
    var expend1 by remember { mutableStateOf(false) }
    var input by remember { mutableStateOf("0") }
    var inputUnit by remember { mutableStateOf("Celsius") }
    var output by remember { mutableStateOf("00") }
    var outputUnit by remember { mutableStateOf("Kelvin") }
    var expend2 by remember { mutableStateOf(false) }

    // Automatically trigger conversion when any relevant value changes
    LaunchedEffect(input, inputUnit, outputUnit) {
        if (input.isNotEmpty() && input != "-") {
            output = convertTemperature(input.toDoubleOrNull() ?: 0.0, inputUnit, outputUnit)
        }
    }
    Column(modifier = Modifier.fillMaxSize().padding(30.dp),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Spacer(modifier= Modifier.height(30.dp))
        Text(text="Temperature", fontFamily = fontFamily, fontSize = 40.sp)


        Spacer(modifier= Modifier.height(50.dp))


        Column(modifier= Modifier
            .height(160.dp)
            .background(color = Color.LightGray, shape = RoundedCornerShape(10.dp)).fillMaxWidth().padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            Box {
                Button(
                    modifier = Modifier.padding(10.dp),
                    onClick = { expend1 = true }) {
                    Text(text = inputUnit, fontFamily = fontFamily)
                    Icon(
                        Icons.Default.ArrowDropDown,
                        contentDescription = "Arrow Down"
                    )
                }
                DropdownMenu(
                    expanded = expend1,
                    onDismissRequest = { expend1 = false }

                ) {
                    DropdownMenuItem(
                        text = { Text("Celsius(°C)",fontFamily = fontFamily) },
                        onClick = {
                            inputUnit = "Celsius"
                            expend1 = false
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Fahrenheit(°F)",fontFamily = fontFamily) },
                        onClick = {
                            inputUnit = "Fahrenheit"
                            expend1 = false
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Kelvin(K)",fontFamily = fontFamily) },
                        onClick = {
                            inputUnit = "Kelvin"
                            expend1 = false
                        }
                    )


                }


            }
            OutlinedTextField(
                value = input,
                onValueChange = {
                    input=it
                                },
                label = { Text("Input") }
            )


        }
        Spacer(modifier= Modifier.height(40.dp))
        Column(modifier= Modifier
            .height(160.dp)
            .background(color = Color.LightGray, shape = RoundedCornerShape(10.dp)).fillMaxWidth().padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            Box {
                Button(
                    modifier = Modifier.padding(10.dp),
                    onClick = { expend2 = true }) {
                    Text(text = outputUnit, fontFamily = fontFamily)
                    Icon(
                        Icons.Default.ArrowDropDown,
                        contentDescription = "Arrow Down"
                    )
                }
                DropdownMenu(
                    expanded = expend2,
                    onDismissRequest = { expend2 = false }

                ) {
                    DropdownMenuItem(
                        text = { Text("Celsius(°C)",fontFamily = fontFamily) },
                        onClick = {
                            outputUnit = "Celsius"
                            expend2 = false
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Fahrenheit(°F)",fontFamily = fontFamily) },
                        onClick = {
                            outputUnit = "Fahrenheit"
                            expend2 = false
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Kelvin(K)",fontFamily = fontFamily) },
                        onClick = {
                            outputUnit = "Kelvin"
                            expend2 = false
                        }
                    )
                }

            }

            Text(text=output, fontFamily = fontFamily, fontSize = 30.sp)

        }

        Spacer(modifier = Modifier.height(100.dp))
        Button(
            onClick = { navController.navigateUp() },
            modifier = Modifier.padding(10.dp)
        )
        {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "back")
        }

    }
}

// Function to convert temperature between units
fun convertTemperature(input: Double, inputUnit: String, outputUnit: String): String {
    val celsius = when (inputUnit) {
        "Celsius" -> input
        "Fahrenheit" -> (input - 32) * 5 / 9
        "Kelvin" -> input - 273.15
        else -> input
    }

    val result = when (outputUnit) {
        "Celsius" -> celsius
        "Fahrenheit" -> (celsius * 9 / 5) + 32
        "Kelvin" -> celsius + 273.15
        else -> celsius
    }

    return "%.2f".format(result)
}