package com.samzuhalsetiawan.qrstudio.presentation.screen.generatecode


sealed interface GenerateCodeScreenEvent {
    data class OnDataInputTextFieldChange(val data: String) : GenerateCodeScreenEvent
    data object OnGenerateCodeButtonClick : GenerateCodeScreenEvent
}