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
fun Area(navController: NavController) {
    var expend1 by remember { mutableStateOf(false) }
    var input by remember { mutableStateOf("0") }
    var inputUnit by remember { mutableStateOf("Meters sq") }
    var output by remember { mutableStateOf("0.00") }
    var outputUnit by remember { mutableStateOf("Centimeters sq") }
    var expend2 by remember { mutableStateOf(false) }

    // Automatically trigger conversion when input, inputUnit, or outputUnit changes
    LaunchedEffect(input, inputUnit, outputUnit) {
        // Ensure input is numeric
        val numericInput = input.toDoubleOrNull()
        if (numericInput != null && numericInput >= 0) {
            output = "%.4f".format(convertArea(numericInput, inputUnit, outputUnit))
        } else {
            output = "0.00" // Reset output if input is invalid
        }
    }

    Column(modifier = Modifier.fillMaxSize().padding(30.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(30.dp))
        Text(text = "Area", fontFamily = fontFamily, fontSize = 40.sp)

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
                        text = { Text("Centimeters Sq (cm^2)", fontFamily = fontFamily) },
                        onClick = {
                            inputUnit = "Centimeters sq"
                            expend1 = false
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Meters Sq (m^2)", fontFamily = fontFamily) },
                        onClick = {
                            inputUnit = "Meters sq"
                            expend1 = false
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Inches Sq (in^2)", fontFamily = fontFamily) },
                        onClick = {
                            inputUnit = "Inches sq"
                            expend1 = false
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Feet Sq (ft ^2)", fontFamily = fontFamily) },
                        onClick = {
                            inputUnit = "Feet sq"
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
                        text = { Text("Centimeters Sq (cm^2)", fontFamily = fontFamily) },
                        onClick = {
                            outputUnit = "Centimeters sq"
                            expend2 = false
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Meters Sq (m^2)", fontFamily = fontFamily) },
                        onClick = {
                            outputUnit = "Meters sq"
                            expend2 = false
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Inches Sq (in^2)", fontFamily = fontFamily) },
                        onClick = {
                            outputUnit = "Inches sq"
                            expend2 = false
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Feet Sq (ft ^2)", fontFamily = fontFamily) },
                        onClick = {
                            outputUnit = "Feet sq"
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

// Conversion logic for area units
fun convertArea(value: Double, fromUnit: String, toUnit: String): Double {
    val conversionFactors = mapOf(
        "Centimeters sq" to 1.0,
        "Meters sq" to 10_000.0,  // 1 m² = 10,000 cm²
        "Inches sq" to 6.4516,    // 1 in² = 6.4516 cm²
        "Feet sq" to 929.0304      // 1 ft² = 929.0304 cm²
    )

    val inputInCm2 = value * (conversionFactors[fromUnit] ?: 1.0)
    return inputInCm2 / (conversionFactors[toUnit] ?: 1.0)

}
