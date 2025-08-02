package com.samzuhalsetiawan.qrstudio.presentation.screen.dashboard

import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.samzuhalsetiawan.qrstudio.domain.navigation.Screen
import org.koin.compose.viewmodel.koinViewModel

fun NavGraphBuilder.dashboardScreen() {
    composable<Screen.DashboardScreen> {
        val viewModel = koinViewModel<DashboardScreenViewModel>()
        val state by viewModel.state.collectAsStateWithLifecycle()
        DashboardScreen(
            state = state,
            onEvent = viewModel::onEvent
        )
    }
}