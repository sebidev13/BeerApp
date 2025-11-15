package com.example.beer.ui.beer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun BeerTabScreen(viewModel: BeerTabViewModel) {
    var number1 by remember { mutableStateOf("") }
    var number2 by remember { mutableStateOf("") }

    // State aus dem ViewModel (automatisch recomposed bei Änderungen)
    val result by viewModel.result.collectAsState(initial = null)
    val history by viewModel.history.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        OutlinedTextField(
            value = number1,
            onValueChange = {
                number1 = it
                viewModel.calculate(
                    number1.toIntOrNull(),
                    number2.toIntOrNull()
                )
            },
            label = { Text("Zahl 1") },
            singleLine = true
        )

        OutlinedTextField(
            value = number2,
            onValueChange = {
                number2 = it
                viewModel.calculate(
                    number1.toIntOrNull(),
                    number2.toIntOrNull()
                )
            },
            label = { Text("Zahl 2") },
            singleLine = true
        )

        Text(
            text = result?.let { "Summe: ${it.sum}" } ?: "Bitte Zahlen eingeben",
            modifier = Modifier.padding(top = 16.dp)
        )

        if (history.isEmpty()) {
            Text("Keine Berechnungen vorhanden", modifier = Modifier.padding(16.dp))
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize().padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(history) { data ->
                    Text("${data.num1} + ${data.num2} = ${data.sum}")
                }
            }
        }
    }
}