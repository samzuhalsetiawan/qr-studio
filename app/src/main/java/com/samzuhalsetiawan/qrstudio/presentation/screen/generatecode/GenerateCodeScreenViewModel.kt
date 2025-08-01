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

class GenerateCodeScreenViewModel : ViewModel() {

    private val _state = MutableStateFlow(GenerateCodeScreenState())
    val state = _state.asStateFlow()

    fun onEvent(event: GenerateCodeScreenEvent) {
        when (event) {
            is GenerateCodeScreenEvent.OnDataInputTextFieldChange -> onDataInputTextFieldChange(event.data)
            is GenerateCodeScreenEvent.OnGenerateCodeButtonClick -> onGenerateCodeButtonClick()
        }

    }

    private fun onGenerateCodeButtonClick() {
        val width = 256
        val height = 256
        val hints = mutableMapOf<EncodeHintType, Any>()
        hints[EncodeHintType.ERROR_CORRECTION] = ErrorCorrectionLevel.L
        hints[EncodeHintType.CHARACTER_SET] = "UTF-8"
        hints[EncodeHintType.MARGIN] = 1
        val multiFormatWriter = MultiFormatWriter()
        try {
            val bitMatrix = multiFormatWriter.encode(_state.value.data, BarcodeFormat.QR_CODE, width, height, hints)
            val bitmap = createBitmap(width, height, Bitmap.Config.RGB_565)
            for (x in 0 until width) {
                for (y in 0 until height) {
                    bitmap[x, y] = if (bitMatrix[x, y]) Color.BLACK else Color.WHITE
                }
            }
            _state.update { it.copy(qr = bitmap) }
        } catch (e: WriterException) {
            throw e
        }
    }

    private fun onDataInputTextFieldChange(data: String) {
        _state.update { it.copy(data = data) }
    }

}
