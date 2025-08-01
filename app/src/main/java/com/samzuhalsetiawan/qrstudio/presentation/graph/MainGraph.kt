package com.samzuhalsetiawan.qrstudio.presentation.graph

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.createGraph
import com.samzuhalsetiawan.qrstudio.presentation.screen.Screen
import com.samzuhalsetiawan.qrstudio.presentation.screen.dashboard.DashboardScreen
import com.samzuhalsetiawan.qrstudio.presentation.screen.generatecode.GenerateCodeScreen
import com.samzuhalsetiawan.qrstudio.presentation.screen.generatecode.GenerateCodeScreenViewModel
import com.samzuhalsetiawan.qrstudio.presentation.screen.scanner.ScannerScreen
import com.samzuhalsetiawan.qrstudio.presentation.screen.settings.SettingsScreen

@Composable
fun rememberMainNavGraph(
    navController: NavHostController = rememberNavController()
): NavGraph {
    return remember(navController) {
        navController.createGraph(startDestination = Screen.DashboardScreen) {
            composable<Screen.DashboardScreen> {
                DashboardScreen(
                    navigateToGenerateCodeScreen = {
                        navController.navigate(Screen.GenerateCodeScreen)
                    }
                )
            }
            composable<Screen.ScannerScreen> {
                ScannerScreen()
            }
            composable<Screen.SettingsScreen> {
                SettingsScreen()
            }
            composable<Screen.GenerateCodeScreen> {
                val viewModel = viewModel<GenerateCodeScreenViewModel>()
                GenerateCodeScreen(viewModel)
            }
        }
    }
}