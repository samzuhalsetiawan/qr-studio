package com.samzuhalsetiawan.qrstudio.domain.usecases

import com.samzuhalsetiawan.qrstudio.domain.factory.QRCodeFactory
import com.samzuhalsetiawan.qrstudio.domain.model.QRCode

class GenerateQRCode(
    private val qrCodeFactory: QRCodeFactory
) {
    operator fun invoke(
        data: String
    ): QRCode {
        return qrCodeFactory.generateQRCode(data)
    }

}