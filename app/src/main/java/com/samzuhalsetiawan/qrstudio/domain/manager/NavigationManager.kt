package com.samzuhalsetiawan.qrstudio.domain.manager

import com.samzuhalsetiawan.qrstudio.domain.navigation.Screen

interface NavigationManager {
    fun navigateTo(screen: Screen)
}