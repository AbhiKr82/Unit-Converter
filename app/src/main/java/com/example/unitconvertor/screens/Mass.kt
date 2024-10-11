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
fun Mass(navController: NavController) {
    var expend1 by remember { mutableStateOf(false) }
    var input by remember { mutableStateOf("0") }
    var inputUnit by remember { mutableStateOf("Grams") }
    var output by remember { mutableStateOf("00") }
    var outputUnit by remember { mutableStateOf("Kilograms") }
    var expend2 by remember { mutableStateOf(false) }

    // Automatically trigger conversion when input, inputUnit, or outputUnit changes
    LaunchedEffect(input, inputUnit, outputUnit) {
        if (input.isNotEmpty() && input != "-") {
            output = convertMass(input.toDoubleOrNull() ?: 0.0, inputUnit, outputUnit).toString()
        }
    }
    Column(modifier = Modifier.fillMaxSize().padding(30.dp),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Spacer(modifier= Modifier.height(30.dp))
        Text(text="Mass", fontFamily = fontFamily, fontSize = 40.sp)


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
                        text = { Text("Grams(g)",fontFamily = fontFamily) },
                        onClick = {
                            inputUnit = "Grams"
                            expend1 = false
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Kilograms(kg)",fontFamily = fontFamily) },
                        onClick = {
                            inputUnit = "Kilograms"
                            expend1 = false
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Pound(lb)",fontFamily = fontFamily) },
                        onClick = {
                            inputUnit = "Pound"
                            expend1 = false
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Tons(t)",fontFamily = fontFamily) },
                        onClick = {
                            inputUnit = "Tons"
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
                        text = { Text("Grams(g)",fontFamily = fontFamily) },
                        onClick = {
                            outputUnit = "Grams"
                            expend2 = false
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Kilograms(kg)",fontFamily = fontFamily) },
                        onClick = {
                            outputUnit = "Kilograms"
                            expend2 = false
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Pound(lb)",fontFamily = fontFamily) },
                        onClick = {
                            outputUnit = "Pound"
                            expend2 = false
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Tons(t)",fontFamily = fontFamily) },
                        onClick = {
                            outputUnit = "Tons"
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

// Conversion logic for mass units
fun convertMass(value: Double, fromUnit: String, toUnit: String): Double {
    val conversionFactors = mapOf(
        "Grams" to 1.0,
        "Kilograms" to 1000.0,
        "Pound" to 453.592,
        "Tons" to 1_000_000.0
    )

    val inputInGrams = value * (conversionFactors[fromUnit] ?: 1.0)
    return inputInGrams / (conversionFactors[toUnit] ?: 1.0)
}