package com.samzuhalsetiawan.qrstudio.domain.usecases

import com.samzuhalsetiawan.qrstudio.domain.manager.NavigationManager
import com.samzuhalsetiawan.qrstudio.domain.navigation.Screen

class NavigateTo(
    private val navigationManager: NavigationManager
) {
    operator fun invoke(screen: Screen) {
        navigationManager.navigateTo(screen)
    }
}