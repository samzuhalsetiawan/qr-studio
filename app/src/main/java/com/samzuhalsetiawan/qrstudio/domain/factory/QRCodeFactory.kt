package com.samzuhalsetiawan.qrstudio.domain.factory

import com.samzuhalsetiawan.qrstudio.domain.model.QRCode

interface QRCodeFactory {
    fun generateQRCode(data: String): QRCode
}