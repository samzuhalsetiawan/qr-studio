package com.samzuhalsetiawan.qrstudio.domain.utils

import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDestination.Companion.hasRoute
import kotlin.reflect.KClass

infix fun <T : Any> NavBackStackEntry?.isEntryOf(entry: KClass<T>): Boolean {
    return this?.destination?.hasRoute(entry) == true
}

infix fun <T : Any> NavBackStackEntry?.isNotEntryOf(entry: KClass<T>): Boolean {
    return !(this isEntryOf entry)
}

fun NavBackStackEntry?.isInListOf(vararg entries: KClass<*>): Boolean {
    return entries.any { this isEntryOf it }
}