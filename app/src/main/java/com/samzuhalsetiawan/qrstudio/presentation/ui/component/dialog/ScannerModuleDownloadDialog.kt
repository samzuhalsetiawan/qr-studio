package com.samzuhalsetiawan.qrstudio.presentation.ui.component.dialog

import android.widget.Toast
import androidx.annotation.FloatRange
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.window.DialogProperties
import com.google.android.gms.common.moduleinstall.InstallStatusListener
import com.google.android.gms.common.moduleinstall.ModuleInstallClient
import com.google.android.gms.common.moduleinstall.ModuleInstallRequest
import com.google.android.gms.common.moduleinstall.ModuleInstallResponse
import com.google.android.gms.common.moduleinstall.ModuleInstallStatusUpdate
import com.google.android.gms.tasks.Task
import com.google.mlkit.vision.codescanner.GmsBarcodeScanner

@Composable
fun ScannerModuleDownloadDialog(
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit,
    moduleInstallClient: ModuleInstallClient,
    barcodeScanner: GmsBarcodeScanner,
    onModuleInstalled: () -> Unit
) {
    @FloatRange(from = 0.0, to = 1.0)
    var progress by remember { mutableFloatStateOf(0f) }
    var isCompleted by remember { mutableStateOf(false) }
    var isDownloading by remember { mutableStateOf(false) }
    val listener = remember {
        object : InstallStatusListener {
            override fun onInstallStatusUpdated(moduleInstallStatusUpdate: ModuleInstallStatusUpdate) {
                isDownloading = true
                moduleInstallStatusUpdate.progressInfo?.let {
                    val progressInt = (it.bytesDownloaded * 100 / it.totalBytesToDownload).toInt()
                    progress = progressInt.toFloat() / 100
                }
                if (moduleInstallStatusUpdate.installState == ModuleInstallStatusUpdate.InstallState.STATE_COMPLETED) {
                    isCompleted = true
                    isDownloading = false
                    onModuleInstalled()
                }
                if (isTerminateState(moduleInstallStatusUpdate.installState)) {
                    isDownloading = false
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
    val moduleInstallRequest = remember(barcodeScanner, listener) {
        ModuleInstallRequest.newBuilder()
            .addApi(barcodeScanner)
            .setListener(listener)
            .build()
    }
    AlertDialog(
        modifier = modifier,
        properties = DialogProperties(dismissOnClickOutside = false, dismissOnBackPress = false),
        onDismissRequest = {
            onDismissRequest()
        },
        title = {
            Text("Download Google Scanner")
        },
        text = {
            when {
                isDownloading || isCompleted -> {
                    LinearProgressIndicator(
                        progress = { progress }
                    )
                }
                else -> {
                    Text("Our app need Google Scanner Module to be able to scan QR Code, Download module?")
                }
            }
        },
        confirmButton = {
            TextButton(
                enabled = !isDownloading,
                onClick = {
                    if (!isDownloading) {
                        isDownloading = true
                        moduleInstallClient
                            .installModules(moduleInstallRequest)
                            .addOnSuccessListener { moduleInstallResponse ->
                                if (moduleInstallResponse.areModulesAlreadyInstalled()) {
                                    onModuleInstalled()
                                }
                            }
                            .addOnFailureListener { exception ->
                                throw exception
                            }
                    }
                }
            ) {
                when {
                    isDownloading -> {
                        Text("Downloading")
                    }
                    else -> {
                        Text("Yes")
                    }
                }
            }
        },
        dismissButton = {
            TextButton(
                enabled = !isDownloading,
                onClick = {
                    onDismissRequest()
                }
            ) {
                Text("No")
            }
        }
    )
}