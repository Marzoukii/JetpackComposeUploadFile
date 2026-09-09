package com.example.jetpackcomposeuploadfile.ui.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpackcomposeuploadfile.domain.enums.FileUiState
import com.example.myapp.data.remote.NetworkResult
import com.example.myapp.domain.usecase.GetFilesUseCase
import com.example.myapp.domain.usecase.GetRootUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FileViewModel @Inject constructor(
    private val getFilesUseCase: GetFilesUseCase,
    private val getRootUseCase: GetRootUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow<FileUiState>(FileUiState.Loading)
    val uiState: StateFlow<FileUiState> = _uiState.asStateFlow()

    private var currentFolderId: String? = null

    init {
        loadRoot()
    }

    fun loadRoot() {
        viewModelScope.launch {
            _uiState.value = FileUiState.Loading

            when (val result = getRootUseCase()) {
                is NetworkResult.Success -> {
                    currentFolderId = result.data
                    loadFiles(result.data)
                }

                is NetworkResult.Error -> {
                    _uiState.value = FileUiState.Error(result.exception.message ?: "Unknown error")
                }
            }
        }
    }

    fun loadFiles(folderId: String) {
        viewModelScope.launch {
            _uiState.value = FileUiState.Loading
            currentFolderId = folderId
            when (val result = getFilesUseCase(folderId)) {
                is NetworkResult.Success -> {
                    _uiState.value = FileUiState.Success(result.data)
                }

                is NetworkResult.Error -> {
                    _uiState.value = FileUiState.Error(result.exception.message ?: "Unknown error")
                }
            }
        }
    }
}