package com.samzuhalsetiawan.qrstudio.presentation.screen.settings

import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.samzuhalsetiawan.qrstudio.domain.navigation.Screen
import org.koin.androidx.compose.koinViewModel

fun NavGraphBuilder.settingsScreen() {
    composable<Screen.SettingsScreen> {
        val viewModel = koinViewModel<SettingsScreenViewModel>()
        val state by viewModel.state.collectAsStateWithLifecycle()
        SettingsScreen(
            state = state,
            onEvent = viewModel::onEvent
        )
    }
}