package com.samzuhalsetiawan.qrstudio.presentation.screen.generatecode

import android.graphics.Bitmap

data class GenerateCodeScreenState(
    val data: String = "",
    val qr: Bitmap? = null
)

sealed interface GenerateCodeScreenEvent {
    data class OnDataInputTextFieldChange(val data: String) : GenerateCodeScreenEvent
    data object OnGenerateCodeButtonClick : GenerateCodeScreenEvent
}