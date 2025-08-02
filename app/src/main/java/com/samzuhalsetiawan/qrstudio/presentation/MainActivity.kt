package com.samzuhalsetiawan.qrstudio.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.samzuhalsetiawan.qrstudio.di.LoadDynamicModule
import com.samzuhalsetiawan.qrstudio.domain.manager.NavigationManager
import com.samzuhalsetiawan.qrstudio.presentation.navigation.rememberMainNavGraph
import com.samzuhalsetiawan.qrstudio.presentation.theme.QRStudioTheme
import com.samzuhalsetiawan.qrstudio.presentation.ui.component.bottomnavigation.MainBottomNavigation

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            QRStudioTheme {
                val navController = rememberNavController()
                LoadDynamicModule(navController)
                val navGraph = rememberMainNavGraph(navController)
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