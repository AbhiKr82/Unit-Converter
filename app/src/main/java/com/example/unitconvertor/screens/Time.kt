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
fun Time (navController:NavController){
    var expend1 by remember { mutableStateOf(false) }
    var input by remember { mutableStateOf("0") }
    var inputUnit by remember { mutableStateOf("Seconds") }
    var output by remember { mutableStateOf("00") }
    var outputUnit by remember { mutableStateOf("Minutes") }
    var expend2 by remember { mutableStateOf(false) }

    // LaunchedEffect to handle conversions
    LaunchedEffect(input, inputUnit, outputUnit) {
        // Convert input whenever it or the selected units change
        convertTime(input, inputUnit, outputUnit) { result ->
            output = result
        }
    }

    Column(modifier = Modifier.fillMaxSize().padding(30.dp),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Spacer(modifier= Modifier.height(30.dp))
        Text(text="Time", fontFamily = fontFamily, fontSize = 40.sp)


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
                        text = { Text("Milliseconds(ms)",fontFamily = fontFamily) },
                        onClick = {
                            inputUnit = "Milliseconds"
                            expend1 = false
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Seconds(s)",fontFamily = fontFamily) },
                        onClick = {
                            inputUnit = "Seconds"
                            expend1 = false
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Minutes(min)",fontFamily = fontFamily) },
                        onClick = {
                            inputUnit = "Minutes"
                            expend1 = false
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Hours(h)",fontFamily = fontFamily) },
                        onClick = {
                            inputUnit = "Hours"
                            expend1 = false
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Days(d)",fontFamily = fontFamily) },
                        onClick = {
                            inputUnit = "Days"
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
                        text = { Text("Milliseconds(ms)",fontFamily = fontFamily) },
                        onClick = {
                            outputUnit = "Milliseconds"
                            expend2 = false
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Seconds(s)",fontFamily = fontFamily) },
                        onClick = {
                            outputUnit = "Seconds"
                            expend2 = false
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Minutes(min)",fontFamily = fontFamily) },
                        onClick = {
                            outputUnit = "Minutes"
                            expend2 = false
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Hours(h)",fontFamily = fontFamily) },
                        onClick = {
                            outputUnit = "Hours"
                            expend2 = false
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Days(d)",fontFamily = fontFamily) },
                        onClick = {
                            outputUnit = "Days"
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

fun convertTime(input: String, inputUnit: String, outputUnit: String, onConversionComplete: (String) -> Unit) {
    // Convert input string to a double for calculations
    val inputValue = input.toDoubleOrNull() ?: 0.0
    var outputValue = 0.0

    // Conversion factors (in milliseconds)
    val conversionFactors = mapOf(
        "Milliseconds" to 1.0,      // base unit
        "Seconds" to 1000.0,         // 1 s = 1000 ms
        "Minutes" to 60_000.0,       // 1 min = 60_000 ms
        "Hours" to 3_600_000.0,      // 1 h = 3_600_000 ms
        "Days" to 86_400_000.0        // 1 d = 86_400_000 ms
    )

    // Convert input value to milliseconds
    val valueInMilliseconds = inputValue * (conversionFactors[inputUnit] ?: 1.0)

    // Convert milliseconds to the desired output unit
    outputValue = valueInMilliseconds / (conversionFactors[outputUnit] ?: 1.0)


    // Convert outputValue to string with two decimal points
    onConversionComplete(String.format("%.4f", outputValue))
}