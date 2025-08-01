package com.samzuhalsetiawan.qrstudio.data.factory

import android.graphics.Color
import androidx.core.graphics.set
import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.google.zxing.MultiFormatWriter
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel
import com.samzuhalsetiawan.qrstudio.domain.factory.QRCodeFactory
import com.samzuhalsetiawan.qrstudio.domain.model.QRCode

class QRCodeFactoryImpl : QRCodeFactory {

    private val format: BarcodeFormat = BarcodeFormat.QR_CODE
    private val width: Int = 256
    private val height: Int = 256
    private val hints: Map<EncodeHintType, Any> = mapOf(
        EncodeHintType.ERROR_CORRECTION to ErrorCorrectionLevel.L,
        EncodeHintType.CHARACTER_SET to "UTF-8",
        EncodeHintType.MARGIN to 1
    )
    private val multiFormatWriter = MultiFormatWriter()

    override fun generateQRCode(data: String): QRCode {
        val bitMatrix = multiFormatWriter.encode(data, format, width, height, hints)
        return QRCode { x, y ->
            bitMatrix[x, y]
        }
    }
}