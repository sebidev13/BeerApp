package com.example.beer.ui.setting

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SettingsTabScreen(viewModel: SettingsTabViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Button(
            onClick = { viewModel.export() }
        ) {
            Text("Export")
        }
        Button(
            onClick = { viewModel.import() }
        ) {
            Text("Import")
        }
        Button(
            onClick = { viewModel.infos() }
        ) {
            Text("Infos")
        }
    }
}