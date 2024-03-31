package com.jmballangca.unitconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jmballangca.unitconverter.ui.theme.UnitConverterTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConverterTheme {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UnitConverter()
                }
            }
        }
    }
}

@Composable
fun  UnitConverter() {

    var inputValue by remember {
        mutableStateOf("")
    }
    var outPutValue by remember {
        mutableStateOf("")
    }
    var iExpanded by remember {

        mutableStateOf(false)
    }
    var oExpanded by remember {
        mutableStateOf(false)
    }

    var inputUnit by remember {
        mutableStateOf("Meters")
    }
    var outPutUnit by remember {
        mutableStateOf("Meters")
    }
    val inputConverter = remember {
        mutableStateOf(1.00)
    }
    val outpuConverter = remember {
        mutableStateOf(1.00)
    }
    fun calculateResult() {
        val inputVal = inputValue.toDoubleOrNull() ?: 0.00
        val result = (inputVal * inputConverter.value * 100.0 / outpuConverter.value).roundToInt() / 100.0
        outPutValue = result.toString()
    }
    Column(
        modifier =  Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Unit Converter" , style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = inputValue,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            onValueChange = { inputValue = it
                            calculateResult() },
            label = { Text(text = "Enter a value") },
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            Box {
                Button(
                    onClick = {
                        iExpanded = true
                    }) {
                    Text(text = inputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Dropdown Icon")
                }
                DropdownMenu(expanded = iExpanded, onDismissRequest = { iExpanded = false }) {
                    DropdownMenuItem(
                        text = { Text(text = "Centimeters") },
                        onClick = {
                            iExpanded = false
                            inputUnit = "Centimeters"
                            inputConverter.value= 0.01
                            calculateResult()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text(text = "Meters") },
                        onClick = {
                            iExpanded = false
                            inputUnit = "Meters"
                            inputConverter.value= 1.0
                            calculateResult()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text(text = "Feet") },
                        onClick = {
                            iExpanded = false
                            inputUnit = "Feet"
                            inputConverter.value= 0.3048
                            calculateResult()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text(text = "Millimeters") },
                        onClick = {
                            iExpanded = false
                            inputUnit = "Feet"
                            inputConverter.value= 0.001
                            calculateResult()
                        }
                    )
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            Box {
                Button(
                    onClick = {
                        oExpanded = true
                    }) {
                    Text(text = outPutUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Dropdown Icon")
                }

                DropdownMenu(expanded = oExpanded, onDismissRequest = {  oExpanded =false  }) {
                    DropdownMenuItem(
                        text = { Text(text = "Centimeters") },
                        onClick = {
                            oExpanded =false
                            outPutUnit = "Centimeters"
                            outpuConverter.value= 0.01
                            calculateResult()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text(text = "Meters") },
                        onClick = {
                            oExpanded =false
                            outPutUnit = "Meters"
                            outpuConverter.value= 1.0
                            calculateResult()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text(text = "Feet") },
                        onClick = {
                            oExpanded =false
                            outPutUnit = "Feet"
                            outpuConverter.value= 0.3048
                            calculateResult()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text(text = "Millimeters") },
                        onClick = {
                            oExpanded =false
                            outPutUnit = "Millimeters"
                            outpuConverter.value= 0.001
                            calculateResult()
                        }
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Result: $outPutValue $outPutUnit" , style = MaterialTheme.typography.headlineSmall )

    }
}

@Preview(showBackground = true)
@Composable
fun UnitConverterPreview() {
   UnitConverterTheme {
       UnitConverter()
   }
}