package com.samzuhalsetiawan.qrstudio.presentation.screen.settings

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class SettingsScreenViewModel : ViewModel() {

    private val _state = MutableStateFlow(SettingsScreenState())
    val state = _state.asStateFlow()

    fun onEvent(event: SettingsScreenEvent) {

    }
}