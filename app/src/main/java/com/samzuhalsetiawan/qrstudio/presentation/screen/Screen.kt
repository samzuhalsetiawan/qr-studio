package com.samzuhalsetiawan.qrstudio.presentation.screen

import kotlinx.serialization.Serializable

sealed interface Screen {
    @Serializable
    object DashboardScreen : Screen

    @Serializable
    object ScannerScreen : Screen

    @Serializable
    object SettingsScreen : Screen

    @Serializable
    object GenerateCodeScreen : Screen
}