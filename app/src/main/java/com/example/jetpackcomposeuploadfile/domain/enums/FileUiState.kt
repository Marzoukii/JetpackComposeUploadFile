package com.example.jetpackcomposeuploadfile.domain.enums

import com.example.myapp.domain.model.FileItem

sealed class FileUiState {
    object Loading : FileUiState()
    data class Success(val files: List<FileItem>) : FileUiState()
    data class Error(val message: String) : FileUiState()
}
