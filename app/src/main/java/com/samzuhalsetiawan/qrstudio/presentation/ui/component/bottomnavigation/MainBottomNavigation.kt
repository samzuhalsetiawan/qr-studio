package com.samzuhalsetiawan.qrstudio.presentation.ui.component.bottomnavigation

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.google.android.gms.common.moduleinstall.ModuleInstall
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.codescanner.GmsBarcodeScannerOptions
import com.google.mlkit.vision.codescanner.GmsBarcodeScanning
import com.samzuhalsetiawan.qrstudio.domain.utils.isEntryOf
import com.samzuhalsetiawan.qrstudio.domain.utils.isInListOf
import com.samzuhalsetiawan.qrstudio.domain.utils.isNotEntryOf
import com.samzuhalsetiawan.qrstudio.presentation.screen.Screen
import com.samzuhalsetiawan.qrstudio.presentation.ui.component.dialog.ScannerModuleDownloadDialog

@Composable
fun MainBottomNavigation(
    navController: NavHostController,
) {
    val context = LocalContext.current
    val currentNavBackStackEntry by navController.currentBackStackEntryAsState()
    val options = remember {
        GmsBarcodeScannerOptions.Builder()
            .setBarcodeFormats(Barcode.FORMAT_QR_CODE)
            .build()
    }
    val barcodeScanner = remember(context) {
        GmsBarcodeScanning.getClient(context, options)
    }
    val moduleInstallClient = ModuleInstall.getClient(context)
    var showScannerModuleDownloadDialog by remember { mutableStateOf(false) }
    AnimatedVisibility(
        visible = currentNavBackStackEntry.isInListOf(
            Screen.DashboardScreen::class,
            Screen.SettingsScreen::class
        )
    ) {
        NavigationBar {
            NavigationBarItem(
                selected = currentNavBackStackEntry isEntryOf Screen.DashboardScreen::class,
                onClick = {
                    if (currentNavBackStackEntry isNotEntryOf Screen.DashboardScreen::class) {
                        navController.navigate(Screen.DashboardScreen) {
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            restoreState = true
                            launchSingleTop = true
                        }
                    }
                },
                icon = {
                    Icon(
                        imageVector = Icons.Default.Home,
                        contentDescription = null
                    )
                },
                label = { Text("Dashboard") }
            )
            NavigationBarItem(
                selected = false,
                onClick = {
                    moduleInstallClient
                        .areModulesAvailable(barcodeScanner)
                        .addOnSuccessListener {  moduleAvailabilityResponse ->
                            if (moduleAvailabilityResponse.areModulesAvailable()) {
                                barcodeScanner.startScan()
                                    .addOnSuccessListener { barcode ->
                                        val value = barcode.rawValue
                                        Toast.makeText(context, value, Toast.LENGTH_LONG).show()
                                    }
                                    .addOnCanceledListener {
                                        Toast.makeText(context, "Canceled", Toast.LENGTH_LONG).show()
                                    }
                                    .addOnFailureListener { exception ->
                                        throw exception
                                    }
                            } else {
                                showScannerModuleDownloadDialog = true
                            }
                        }
                        .addOnFailureListener { exception ->
                            throw exception
                        }
                },
                icon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null
                    )
                },
                label = { Text("Scan") }
            )
            NavigationBarItem(
                selected = currentNavBackStackEntry isEntryOf Screen.SettingsScreen::class,
                onClick = {
                    if (currentNavBackStackEntry isNotEntryOf Screen.SettingsScreen::class) {
                        navController.navigate(Screen.SettingsScreen) {
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            restoreState = true
                            launchSingleTop = true
                        }
                    }
                },
                icon = {
                    Icon(
                        imageVector = Icons.Default.Settings,
                        contentDescription = null
                    )
                },
                label = { Text("Settings") }
            )
        }
    }
    if (showScannerModuleDownloadDialog) {
        ScannerModuleDownloadDialog(
            onDismissRequest = { showScannerModuleDownloadDialog = false },
            moduleInstallClient = moduleInstallClient,
            barcodeScanner = barcodeScanner,
            onModuleInstalled = {
                showScannerModuleDownloadDialog = false
                barcodeScanner.startScan()
                    .addOnSuccessListener { barcode ->
                        val value = barcode.rawValue
                        Toast.makeText(context, value, Toast.LENGTH_LONG).show()
                    }
                    .addOnCanceledListener {
                        Toast.makeText(context, "Canceled", Toast.LENGTH_LONG).show()
                    }
                    .addOnFailureListener { exception ->
                        throw exception
                    }
            },
        )
    }
}