package com.samzuhalsetiawan.qrstudio.di

import com.samzuhalsetiawan.qrstudio.data.factory.QRCodeFactoryImpl
import com.samzuhalsetiawan.qrstudio.domain.factory.QRCodeFactory
import com.samzuhalsetiawan.qrstudio.domain.usecases.GenerateQRCode
import com.samzuhalsetiawan.qrstudio.presentation.screen.generatecode.GenerateCodeScreenViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val appModule = module {
    singleOf(::QRCodeFactoryImpl) bind QRCodeFactory::class
    singleOf(::GenerateQRCode)
    viewModelOf(::GenerateCodeScreenViewModel)
}