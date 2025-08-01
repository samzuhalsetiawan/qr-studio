package com.samzuhalsetiawan.qrstudio.domain.utils

import android.graphics.Bitmap
import android.graphics.Color
import androidx.core.graphics.createBitmap
import androidx.core.graphics.set
import com.samzuhalsetiawan.qrstudio.domain.model.QRCode

fun QRCode.toBitmap(): Bitmap {
    val bitmap = createBitmap(dimension, dimension, Bitmap.Config.RGB_565)
    for (x in 0 until dimension) {
        for (y in 0 until dimension) {
            bitmap[x, y] = if (this[x, y]) Color.BLACK else Color.WHITE
        }
    }
    return bitmap
}