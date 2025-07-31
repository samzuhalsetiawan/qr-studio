package com.samzuhalsetiawan.qrstudio.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.createGraph
import com.samzuhalsetiawan.qrstudio.domain.utils.isEntryOf
import com.samzuhalsetiawan.qrstudio.domain.utils.isInListOf
import com.samzuhalsetiawan.qrstudio.domain.utils.isNotEntryOf
import com.samzuhalsetiawan.qrstudio.presentation.graph.rememberMainNavGraph
import com.samzuhalsetiawan.qrstudio.presentation.screen.Screen
import com.samzuhalsetiawan.qrstudio.presentation.screen.dashboard.DashboardScreen
import com.samzuhalsetiawan.qrstudio.presentation.screen.scanner.ScannerScreen
import com.samzuhalsetiawan.qrstudio.presentation.screen.settings.SettingsScreen
import com.samzuhalsetiawan.qrstudio.presentation.theme.QRStudioTheme
import com.samzuhalsetiawan.qrstudio.presentation.ui.component.bottomnavigation.MainBottomNavigation

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            val navGraph = rememberMainNavGraph(navController)
            QRStudioTheme {
                Scaffold(
                    bottomBar = { MainBottomNavigation(navController) }
                ) { paddingValues ->
                    NavHost(
                        modifier = Modifier.padding(paddingValues),
                        navController = navController,
                        graph = navGraph
                    )
                }
            }
        }
    }
}