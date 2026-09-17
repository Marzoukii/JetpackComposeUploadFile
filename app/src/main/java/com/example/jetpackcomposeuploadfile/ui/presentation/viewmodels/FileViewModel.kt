package com.example.jetpackcomposeuploadfile.ui.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpackcomposeuploadfile.data.response.FileUiState
import com.example.jetpackcomposeuploadfile.data.NetworkResult
import com.example.jetpackcomposeuploadfile.domain.usecase.CreateFolderUseCase
import com.example.jetpackcomposeuploadfile.domain.usecase.DeleteItemUseCase
import com.example.jetpackcomposeuploadfile.domain.usecase.GetFilesUseCase
import com.example.jetpackcomposeuploadfile.domain.usecase.GetRootUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FileViewModel @Inject constructor(
    private var getFilesUseCase: GetFilesUseCase,
    private var getRootUseCase: GetRootUseCase,
    private var createFolderUseCase: CreateFolderUseCase,
    private var deleteItemUseCase: DeleteItemUseCase
) : ViewModel() {
    private var fileJOB: Job? = null
    private var userJOB: Job? = null
    private var deleteJOB: Job? = null
    private var createJOB: Job? = null
    private val _uiStateFile = MutableStateFlow<FileUiState>(FileUiState.Loading)
    val uiStateFile: StateFlow<FileUiState> = _uiStateFile.asStateFlow()

    private var currentFolderId: String? = null

    init {
        loadRoot()
    }

    fun loadRoot() {
        userJOB?.cancel()
        userJOB = viewModelScope.launch(Dispatchers.IO) {
            try {
                _uiStateFile.value = FileUiState.Loading
                getRootUseCase.execute().collect { result ->
                    when (result) {
                        is NetworkResult.Success -> {
                            currentFolderId = result.data
                            loadFiles(result.data)
                        }
                        is NetworkResult.Error -> {

                            val message = result.exception.message ?: "An error occurred"
                            _uiStateFile.value = FileUiState.Error(message)
                        }
                    }
                }
            } catch (exception: Exception) {
                _uiStateFile.value = FileUiState.Error(exception.message ?: "Unknown error")
            }
        }
    }

    fun loadFiles(folderId: String?) {
        fileJOB?.cancel()
        currentFolderId = folderId
        
        fileJOB = viewModelScope.launch(Dispatchers.IO) {
            try {
                _uiStateFile.value = FileUiState.Loading
                getFilesUseCase.execute(folderId).collect { result ->
                    when (result) {
                        is NetworkResult.Success -> {
                            _uiStateFile.value = FileUiState.Success(result.data)
                        }
                        is NetworkResult.Error -> {
                            val message = result.exception.message ?: "An error occurred"
                            _uiStateFile.value = FileUiState.Error(message)
                        }
                        }
                }
            }
            catch (exception: Exception) {
                _uiStateFile.value = FileUiState.Error(exception.message ?: "Unknown error")
            }
        }
    }

    fun createFolder(name: String, folderId: String? = currentFolderId) {
        if (folderId == null) return
        
        createJOB?.cancel()
        createJOB = viewModelScope.launch(Dispatchers.IO) {
            try {
                createFolderUseCase.execute(folderId, name).collect { result ->
                    if (result is NetworkResult.Success) {
                        loadFiles(folderId)
                    }
                }
            } catch (exception: Exception) {
                _uiStateFile.value = FileUiState.Error(exception.message ?: "Unknown error")
            }
        }
    }

    fun deleteItem(itemId: String?) {
        deleteJOB?.cancel()
        deleteJOB = viewModelScope.launch(Dispatchers.IO) {
            try {
                deleteItemUseCase.execute(itemId).collect { result ->
                    if (result is NetworkResult.Success) {

                        loadFiles(currentFolderId)
                    }
                }
            } catch (exception: Exception) {
                _uiStateFile.value = FileUiState.Error(exception.message ?: "Unknown error")
            }
        }
    }
}