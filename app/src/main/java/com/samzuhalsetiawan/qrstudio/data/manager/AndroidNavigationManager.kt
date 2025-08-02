package com.samzuhalsetiawan.qrstudio.data.manager

import androidx.navigation.NavHostController
import com.samzuhalsetiawan.qrstudio.domain.manager.NavigationManager
import com.samzuhalsetiawan.qrstudio.domain.navigation.Screen

class AndroidNavigationManager(
    private val navController: NavHostController
) : NavigationManager {
    override fun navigateTo(screen: Screen) {
        when (screen) {
            is Screen.ScannerScreen -> {

            }
            else -> navController.navigate(screen)
        }
    }
}