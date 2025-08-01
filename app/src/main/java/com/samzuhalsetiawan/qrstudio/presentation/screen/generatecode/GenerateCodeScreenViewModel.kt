package com.samzuhalsetiawan.qrstudio.presentation.screen.generatecode

import android.graphics.Bitmap
import android.graphics.Color
import androidx.lifecycle.ViewModel
import com.google.zxing.BarcodeFormat
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import com.google.zxing.qrcode.QRCodeWriter
import com.google.zxing.common.BitArray
import com.google.zxing.BinaryBitmap
import com.google.zxing.EncodeHintType
import com.google.zxing.MultiFormatWriter
import com.google.zxing.WriterException
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel
import androidx.core.graphics.createBitmap
import androidx.core.graphics.set
import com.google.zxing.common.BitMatrix
import com.samzuhalsetiawan.qrstudio.domain.usecases.GenerateQRCode
import com.samzuhalsetiawan.qrstudio.domain.utils.toBitmap

class GenerateCodeScreenViewModel(
    private val generateQRCode: GenerateQRCode
) : ViewModel() {

    private val _state = MutableStateFlow(GenerateCodeScreenState())
    val state = _state.asStateFlow()

    fun onEvent(event: GenerateCodeScreenEvent) {
        when (event) {
            is GenerateCodeScreenEvent.OnDataInputTextFieldChange -> onDataInputTextFieldChange(event.data)
            is GenerateCodeScreenEvent.OnGenerateCodeButtonClick -> onGenerateCodeButtonClick()
        }

    }

    private fun onGenerateCodeButtonClick() {
        val qrCodeBitmap = generateQRCode(state.value.data).toBitmap()
        _state.update { it.copy(qr = qrCodeBitmap) }
    }

    private fun onDataInputTextFieldChange(data: String) {
        _state.update { it.copy(data = data) }
    }

}
