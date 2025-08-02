package com.samzuhalsetiawan.qrstudio.presentation.screen.settings

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun SettingsScreen(
    state: SettingsScreenState,
    onEvent: (SettingsScreenEvent) -> Unit
) {
    Text("Settings Screen")
}