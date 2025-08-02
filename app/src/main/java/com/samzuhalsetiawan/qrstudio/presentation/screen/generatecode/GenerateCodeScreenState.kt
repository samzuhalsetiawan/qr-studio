package com.samzuhalsetiawan.qrstudio.presentation.screen.generatecode

import android.graphics.Bitmap

data class GenerateCodeScreenState(
    val data: String = "",
    val qr: Bitmap? = null
)
