package com.example.unitconvertor.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.unitconvertor.fontFamily

@Composable
fun Volume(navController: NavController) {
    var expend1 by remember { mutableStateOf(false) }
    var input by remember { mutableStateOf("0") }
    var inputUnit by remember { mutableStateOf("Cubic Meters (m^3)") }
    var output by remember { mutableStateOf("00") }
    var outputUnit by remember { mutableStateOf("Cubic Centimeters (cm^3)") }
    var expend2 by remember { mutableStateOf(false) }

    // LaunchedEffect to handle conversions
    LaunchedEffect(input, inputUnit, outputUnit) {
        convertVolume(input, inputUnit, outputUnit) { result ->
            output = result
        }

    }

    Column(
        modifier = Modifier.fillMaxSize().padding(30.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(30.dp))
        Text(text = "Volume", fontFamily = fontFamily, fontSize = 40.sp)

        Spacer(modifier = Modifier.height(50.dp))

        Column(
            modifier = Modifier
                .height(160.dp)
                .background(color = Color.LightGray, shape = RoundedCornerShape(10.dp))
                .fillMaxWidth()
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box {
                Button(
                    modifier = Modifier.padding(10.dp),
                    onClick = { expend1 = true }) {
                    Text(text = inputUnit, fontFamily = fontFamily)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Arrow Down")
                }
                DropdownMenu(
                    expanded = expend1,
                    onDismissRequest = { expend1 = false }
                ) {
                    DropdownMenuItem(
                        text = { Text("Cubic Centimeters (cm^3)", fontFamily = fontFamily) },
                        onClick = {
                            inputUnit = "Cubic Centimeters (cm^3)"
                            expend1 = false
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Cubic Meters (m^3)", fontFamily = fontFamily) },
                        onClick = {
                            inputUnit = "Cubic Meters (m^3)"
                            expend1 = false
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Cubic Inches (in^3)", fontFamily = fontFamily) },
                        onClick = {
                            inputUnit = "Cubic Inches (in^3)"
                            expend1 = false
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Cubic Feet (ft^3)", fontFamily = fontFamily) },
                        onClick = {
                            inputUnit = "Cubic Feet (ft^3)"
                            expend1 = false
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Liters (l)", fontFamily = fontFamily) },
                        onClick = {
                            inputUnit = "Liters (l)"
                            expend1 = false
                        }
                    )
                }
            }
            OutlinedTextField(
                value = input,
                onValueChange = { input = it },
                label = { Text("Input") }
            )
        }

        Spacer(modifier = Modifier.height(40.dp))

        Column(
            modifier = Modifier
                .height(160.dp)
                .background(color = Color.LightGray, shape = RoundedCornerShape(10.dp))
                .fillMaxWidth()
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box {
                Button(
                    modifier = Modifier.padding(10.dp),
                    onClick = { expend2 = true }) {
                    Text(text = outputUnit, fontFamily = fontFamily)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Arrow Down")
                }
                DropdownMenu(
                    expanded = expend2,
                    onDismissRequest = { expend2 = false }
                ) {
                    DropdownMenuItem(
                        text = { Text("Cubic Centimeters (cm^3)", fontFamily = fontFamily) },
                        onClick = {
                            outputUnit = "Cubic Centimeters (cm^3)"
                            expend2 = false
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Cubic Meters (m^3)", fontFamily = fontFamily) },
                        onClick = {
                            outputUnit = "Cubic Meters (m^3)"
                            expend2 = false
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Cubic Inches (in^3)", fontFamily = fontFamily) },
                        onClick = {
                            outputUnit = "Cubic Inches (in^3)"
                            expend2 = false
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Cubic Feet (ft^3)", fontFamily = fontFamily) },
                        onClick = {
                            outputUnit = "Cubic Feet (ft^3)"
                            expend2 = false
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Liters (l)", fontFamily = fontFamily) },
                        onClick = {
                            outputUnit = "Liters (l)"
                            expend2 = false
                        }
                    )
                }
            }

            Text(text = output, fontFamily = fontFamily, fontSize = 30.sp)
        }

        Spacer(modifier = Modifier.height(100.dp))
        Button(
            onClick = { navController.navigateUp() },
            modifier = Modifier.padding(10.dp)
        ) {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "back")
        }
    }
}

fun convertVolume(input: String, inputUnit: String, outputUnit: String, onConversionComplete: (String) -> Unit) {
    // Convert input string to a double for calculations
    val inputValue = input.toDoubleOrNull() ?: 0.0
    var outputValue = 0.0

    // Conversion factors
    val conversionFactors = mapOf(
        "Cubic Centimeters (cm^3)" to 1.0,          // base unit
        "Cubic Meters (m^3)" to 1e6,                // 1 m^3 = 1e6 cm^3
        "Cubic Inches (in^3)" to 16.3871,            // 1 in^3 = 16.3871 cm^3
        "Cubic Feet (ft^3)" to 28316.8466,          // 1 ft^3 = 28316.8466 cm^3
        "Liters (l)" to 1000.0                       // 1 L = 1000 cm^3
    )

    // Convert input value to cubic centimeters
    val valueInCm3 = inputValue * (conversionFactors[inputUnit] ?: 1.0)

    // Convert cubic centimeters to the desired output unit
    outputValue = valueInCm3 / (conversionFactors[outputUnit] ?: 1.0)



    // Call the onConversionComplete callback with the formatted output
    onConversionComplete(outputValue.toString())

}
