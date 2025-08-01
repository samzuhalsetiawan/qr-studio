package com.samzuhalsetiawan.qrstudio.presentation.ui.component.bottomnavigation

import android.widget.Toast
import androidx.annotation.FloatRange
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
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
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.google.android.gms.common.moduleinstall.InstallStatusListener
import com.google.android.gms.common.moduleinstall.ModuleInstall
import com.google.android.gms.common.moduleinstall.ModuleInstallRequest
import com.google.android.gms.common.moduleinstall.ModuleInstallStatusUpdate
import com.google.android.gms.tasks.CancellationTokenSource
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.codescanner.GmsBarcodeScannerOptions
import com.google.mlkit.vision.codescanner.GmsBarcodeScanning
import com.samzuhalsetiawan.qrstudio.domain.utils.isEntryOf
import com.samzuhalsetiawan.qrstudio.domain.utils.isInListOf
import com.samzuhalsetiawan.qrstudio.domain.utils.isNotEntryOf
import com.samzuhalsetiawan.qrstudio.presentation.screen.Screen
import com.samzuhalsetiawan.qrstudio.presentation.ui.component.dialog.DownloadingDialog
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.lang.Exception

@Composable
fun MainBottomNavigation(
    navController: NavHostController,
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val currentNavBackStackEntry by navController.currentBackStackEntryAsState()
    var showDownloadDialog by remember { mutableStateOf(false) }
    @FloatRange(from = 0.0, to = 1.0)
    var downloadProgress by remember { mutableFloatStateOf(0f) }
    val options = remember {
        GmsBarcodeScannerOptions.Builder()
            .setBarcodeFormats(Barcode.FORMAT_ALL_FORMATS)
            .build()
    }
    val barcodeScanner = remember(context) {
        GmsBarcodeScanning.getClient(context, options)
    }
    val moduleInstallClient = remember(context) {
        ModuleInstall.getClient(context)
    }
    val moduleInstallListener = remember {
        object : InstallStatusListener {
            override fun onInstallStatusUpdated(moduleInstallStatusUpdate: ModuleInstallStatusUpdate) {
                moduleInstallStatusUpdate.progressInfo?.let {
                    downloadProgress = (it.bytesDownloaded / it.totalBytesToDownload).toFloat()
                }
                if (isTerminateState(moduleInstallStatusUpdate.installState)) {
                    showDownloadDialog = false
                    moduleInstallClient.unregisterListener(this)
                }
            }
            fun isTerminateState(@ModuleInstallStatusUpdate.InstallState state: Int): Boolean {
                return state == ModuleInstallStatusUpdate.InstallState.STATE_CANCELED ||
                        state == ModuleInstallStatusUpdate.InstallState.STATE_COMPLETED ||
                        state == ModuleInstallStatusUpdate.InstallState.STATE_FAILED
            }
        }
    }
    val moduleInstallRequest = remember(barcodeScanner, moduleInstallListener) {
        ModuleInstallRequest.newBuilder()
            .addApi(barcodeScanner)
            .setListener(moduleInstallListener)
            .build()
    }

    AnimatedVisibility(
        visible = currentNavBackStackEntry.isInListOf(
            Screen.DashboardScreen::class,
            Screen.SettingsScreen::class
        ),
        enter = fadeIn() + slideInVertically(
            initialOffsetY = { it * 2 }
        ),
        exit = fadeOut() + slideOutVertically(
            targetOffsetY = { it * 2 }
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
                    coroutineScope.launch {
                        suspend fun launchScanner() {
                            val barcode = barcodeScanner.startScan().await()
                            val value = barcode.rawValue
                            Toast.makeText(context, value, Toast.LENGTH_LONG).show()
                        }
                        try {
                            val moduleAvailabilityResponse = moduleInstallClient.areModulesAvailable(barcodeScanner).await()
                            if (moduleAvailabilityResponse.areModulesAvailable()) {
                                launchScanner()
                            } else {
                                val moduleInstallResponse = moduleInstallClient.installModules(moduleInstallRequest).await()
                                if (moduleInstallResponse.areModulesAlreadyInstalled()) {
                                    launchScanner()
                                } else {
                                    showDownloadDialog = true
                                }
                            }
                        } catch (e: Exception) {
                            throw e
                        }
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
    if (showDownloadDialog) {
        DownloadingDialog(
            onDismissRequest = {
                showDownloadDialog = false
            },
            moduleName = "Google Scanner",
            progress = downloadProgress
        )
    }
}