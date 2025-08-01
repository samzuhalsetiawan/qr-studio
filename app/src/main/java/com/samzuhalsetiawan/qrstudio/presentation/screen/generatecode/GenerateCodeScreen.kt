package com.samzuhalsetiawan.qrstudio.presentation.screen.generatecode

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.samzuhalsetiawan.qrstudio.presentation.theme.QRStudioTheme

@Composable
fun GenerateCodeScreen(
    viewModel: GenerateCodeScreenViewModel,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    GenerateCodeScreen(
        state = state,
        onEvent = viewModel::onEvent
    )
}

@Composable
private fun GenerateCodeScreen(
    state: GenerateCodeScreenState,
    onEvent: (GenerateCodeScreenEvent) -> Unit,
) {
    Column {
        OutlinedTextField(
            value = state.data,
            onValueChange = { onEvent(GenerateCodeScreenEvent.OnDataInputTextFieldChange(it)) },
            label = { Text("Data") }
        )
        if (state.qr != null) {
            Image(
                bitmap = state.qr.asImageBitmap(),
                contentDescription = null
            )
        }
        Button(
            onClick = { onEvent(GenerateCodeScreenEvent.OnGenerateCodeButtonClick) }
        ) {
            Text("Generate QR Code")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GenerateCodeScreenPreview() {
    QRStudioTheme {
        GenerateCodeScreen(
            state = GenerateCodeScreenState(),
            onEvent = {}
        )
    }
}