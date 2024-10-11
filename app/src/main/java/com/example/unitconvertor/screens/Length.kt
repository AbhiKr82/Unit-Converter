package com.example.unitconvertor.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
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
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.unitconvertor.Screens
import com.example.unitconvertor.fontFamily

@Composable
fun Length(navController: NavController) {

    var expend1 by remember { mutableStateOf(false) }
    var input by remember { mutableStateOf("0") }
    var inputUnit by remember { mutableStateOf("Meters") }
    var output by remember { mutableStateOf("00") }
    var outputUnit by remember { mutableStateOf("Centimeters") }
    var expend2 by remember { mutableStateOf(false) }

    // Automatically trigger conversion when input, inputUnit, or outputUnit changes
    LaunchedEffect(input, inputUnit, outputUnit) {
        if (input.isNotEmpty() && input != "-") {
            output = convertLength(input.toDoubleOrNull() ?: 0.0, inputUnit, outputUnit)
        }
    }

    Column(modifier = Modifier.fillMaxSize().padding(30.dp),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Spacer(modifier=Modifier.height(30.dp))
        Text(text="Length", fontFamily = fontFamily, fontSize = 40.sp)


        Spacer(modifier=Modifier.height(50.dp))


        Column(modifier=Modifier
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
                        text = { Text("Millimeters(mm)",fontFamily = fontFamily) },
                        onClick = {
                            inputUnit = "Millimeters"
                            expend1 = false
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Centimeters(cm)",fontFamily = fontFamily) },
                        onClick = {
                            inputUnit = "Centimeters"
                            expend1 = false
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Meters(m)",fontFamily = fontFamily) },
                        onClick = {
                            inputUnit = "Meters"
                            expend1 = false
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Kilometers(km)",fontFamily = fontFamily) },
                        onClick = {
                            inputUnit = "Kilometers"
                            expend1 = false
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Inches(in)",fontFamily = fontFamily) },
                        onClick = {
                            inputUnit = "Inches(in)"
                            expend1 = false
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Feet(ft)",fontFamily = fontFamily) },
                        onClick = {
                            inputUnit = "Feet"
                            expend1 = false
                        }
                    )

                }


            }
            OutlinedTextField(
                value = input,
                onValueChange = {input=it},
                label = { Text("Input") }
            )


        }
        Spacer(modifier=Modifier.height(40.dp))
        Column(modifier=Modifier
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
                        text = { Text("Millimeters(mm)",fontFamily = fontFamily) },
                        onClick = {
                            outputUnit = "Millimeters"
                            expend2 = false
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Centimeters(cm)",fontFamily = fontFamily) },
                        onClick = {
                            outputUnit = "Centimeters"
                            expend2 = false
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Meters(m)",fontFamily = fontFamily) },
                        onClick = {
                            outputUnit = "Meters"
                            expend2 = false
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Kilometers(km)",fontFamily = fontFamily) },
                        onClick = {
                            outputUnit = "Kilometers"
                            expend2 = false
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Inches(in)",fontFamily = fontFamily) },
                        onClick = {
                            outputUnit = "Inches(in)"
                            expend2 = false
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Feet(ft)",fontFamily = fontFamily) },
                        onClick = {
                            outputUnit = "Feet"
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

// Function to convert length between units
fun convertLength(input: Double, inputUnit: String, outputUnit: String): String {
    // Convert to meters first
    val meters = when (inputUnit) {
        "Millimeters" -> input / 1000
        "Centimeters" -> input / 100
        "Meters" -> input
        "Kilometers" -> input * 1000
        "Inches(in)" -> input * 0.0254
        "Feet" -> input * 0.3048
        else -> input
    }

    // Convert meters to the desired output unit
    val result = when (outputUnit) {
        "Millimeters" -> meters * 1000
        "Centimeters" -> meters * 100
        "Meters" -> meters
        "Kilometers" -> meters / 1000
        "Inches(in)" -> meters / 0.0254
        "Feet" -> meters / 0.3048
        else -> meters
    }


    return String.format("%.4f", result)
}