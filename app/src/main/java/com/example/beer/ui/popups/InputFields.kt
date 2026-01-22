package com.example.beer.ui.popups

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp


@Composable
fun AttributeDoubleInputField(
    label: String,
    value: Double,
    onChange: (Number) -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(label, modifier = Modifier.weight(1f))

        Row(verticalAlignment = Alignment.CenterVertically) {
            NumericUnderlinedInputField(
                value = value,
                isInteger = false,
                onValueChange = onChange
            )
        }
    }
}

@Composable
fun AttributeIntegerInputField(
    label: String,
    value: Int,
    onChange: (Number) -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(label, modifier = Modifier.weight(1f))

        Row(verticalAlignment = Alignment.CenterVertically) {
            NumericUnderlinedInputField(
                value = value,
                isInteger = true,
                onValueChange = onChange
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T : Enum<T>> EnumTasteDropDown(
    label: String,
    selected: T?,
    options: Array<T>,
    onSelected: (T?) -> Unit,
    displayMapper: (T?) -> String
) {
    var expanded by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = label, modifier = Modifier.weight(1f))

        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded },
            modifier = Modifier.width(180.dp)
        ) {
            OutlinedTextField(
                value = displayMapper(selected),
                onValueChange = {},
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors(),
                modifier = Modifier.menuAnchor(),
                textStyle = MaterialTheme.typography.bodyMedium
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                DropdownMenuItem(
                    text = { Text("None") },
                    onClick = {
                        onSelected(null)
                        expanded = false
                    }
                )
                options.forEach { option ->
                    DropdownMenuItem(
                        text = { Text(text = displayMapper(option)) },
                        onClick = {
                            onSelected(option)
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun NumericUnderlinedInputField(
    value: Number,
    isInteger: Boolean,
    onValueChange: (Number) -> Unit
) {
    var textFieldState by remember(value) {
        val initialText = if (isInteger) {
            value.toInt().toString()
        } else {
            value.toDouble().toString().replace(".", ",")
        }
        mutableStateOf(
            TextFieldValue(
                text = initialText,
                selection = TextRange(initialText.length)
            )
        )
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        BasicTextField(
            value = textFieldState,
            onValueChange = { newState ->
                val input = newState.text

                if (isInteger) {
                    // Integer Logic
                    var filtered = input.filter { it.isDigit() }
                    var parsed = filtered.toIntOrNull()
                    if(parsed != null && parsed > 5){
                        parsed = parsed % 10
                        filtered = parsed.toString()
                    }
                    if (filtered.isEmpty() || (parsed != null && parsed in 0..5)) {
                        textFieldState = newState.copy(text = filtered)
                        parsed?.let { onValueChange(it) }
                    }
                } else {
                    // Double Logic
                    var formatted = input.replace(".", ",")
                    var parsed = formatted.replace(",", ".").toDoubleOrNull()
                    if(parsed != null && parsed > 5){
                        parsed = parsed % 10
                        formatted = parsed.toString().replace(".", ",")
                    }
                    if (formatted.isEmpty() || (parsed != null && parsed in 0.0..5.0)) {
                        textFieldState = newState.copy(text = formatted)
                        parsed?.let { onValueChange(it) }
                    }
                }
            },
            modifier = Modifier.width(45.dp),
            textStyle = MaterialTheme.typography.bodyLarge.copy(textAlign = TextAlign.Center),
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = if (isInteger) KeyboardType.Number else KeyboardType.Decimal
            )
        )
        HorizontalDivider(
            modifier = Modifier.width(45.dp),
            thickness = 1.dp,
            color = Color.Gray
        )
    }
}

