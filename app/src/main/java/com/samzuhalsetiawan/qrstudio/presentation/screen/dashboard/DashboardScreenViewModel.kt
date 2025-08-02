package com.samzuhalsetiawan.qrstudio.presentation.screen.dashboard

import androidx.lifecycle.ViewModel
import com.samzuhalsetiawan.qrstudio.domain.navigation.Screen
import com.samzuhalsetiawan.qrstudio.domain.usecases.NavigateTo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class DashboardScreenViewModel(
    private val navigateTo: NavigateTo
) : ViewModel() {

    private val _state = MutableStateFlow(DashboardScreenState())
    val state = _state.asStateFlow()

    fun onEvent(event: DashboardScreenEvent) {
        when (event) {
            is DashboardScreenEvent.OnGenerateCodeButtonClick -> onGenerateCodeButtonClick()
        }
    }

    private fun onGenerateCodeButtonClick() {
        navigateTo(Screen.GenerateCodeScreen)
    }

}