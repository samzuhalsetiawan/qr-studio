package com.samzuhalsetiawan.qrstudio.domain.navigation

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