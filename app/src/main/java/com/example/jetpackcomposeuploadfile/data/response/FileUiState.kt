package com.example.jetpackcomposeuploadfile.data.response

import com.example.jetpackcomposeuploadfile.domain.model.FileItemModel


sealed class FileUiState {
    object Loading : FileUiState()
    data class Success(val files: List<FileItemModel>) : FileUiState()
    data class Error(val message: String) : FileUiState()
}
