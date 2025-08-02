package com.samzuhalsetiawan.qrstudio.presentation.screen.dashboard

sealed interface DashboardScreenEvent {
    data object OnGenerateCodeButtonClick : DashboardScreenEvent
}