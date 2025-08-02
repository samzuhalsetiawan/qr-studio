package com.samzuhalsetiawan.qrstudio.presentation.screen.generatecode

import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.samzuhalsetiawan.qrstudio.domain.navigation.Screen
import org.koin.androidx.compose.koinViewModel

fun NavGraphBuilder.generateCodeScreen() {
    composable<Screen.GenerateCodeScreen> {
        val viewModel = koinViewModel<GenerateCodeScreenViewModel>()
        val state by viewModel.state.collectAsStateWithLifecycle()
        GenerateCodeScreen(
            state = state,
            onEvent = viewModel::onEvent
        )
    }
}