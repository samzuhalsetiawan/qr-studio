package com.samzuhalsetiawan.qrstudio.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavGraph
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.createGraph
import com.samzuhalsetiawan.qrstudio.domain.navigation.Screen
import com.samzuhalsetiawan.qrstudio.presentation.screen.dashboard.DashboardScreen
import com.samzuhalsetiawan.qrstudio.presentation.screen.dashboard.dashboardScreen
import com.samzuhalsetiawan.qrstudio.presentation.screen.generatecode.generateCodeScreen
import com.samzuhalsetiawan.qrstudio.presentation.screen.settings.SettingsScreen
import com.samzuhalsetiawan.qrstudio.presentation.screen.settings.settingsScreen

@Composable
fun rememberMainNavGraph(
    navController: NavHostController
): NavGraph {
    return remember(navController) {
        navController.createGraph(startDestination = Screen.DashboardScreen) {
            dashboardScreen()
            settingsScreen()
            generateCodeScreen()
        }
    }
}