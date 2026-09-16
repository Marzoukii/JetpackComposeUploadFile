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

    init {
        loadRoot()
    }

    fun loadRoot() {
        userJOB?.start()
        userJOB = viewModelScope.launch(Dispatchers.IO) {
            try {
                _uiStateFile.value = FileUiState.Loading
                getRootUseCase.execute().collect { result ->
                    if (result is NetworkResult.Success) {
                        loadFiles(result.data)
                    }
                }
            } catch (exception: Exception) {
                _uiStateFile.value = FileUiState.Error(
                    exception.message ?: "Unknown error"
                )
            }
        }
      //  userJOB?.cancel()
    }

    fun loadFiles(folderId: String?) {
        fileJOB?.start()
        fileJOB = viewModelScope.launch(Dispatchers.IO) {
            try {
                _uiStateFile.value = FileUiState.Loading

                getFilesUseCase.execute(folderId).collect { result ->
                    if (result is NetworkResult.Success) {
                        _uiStateFile.value = FileUiState.Success(result.data)
                    }
                }

            } catch (exception: Exception) {
                _uiStateFile.value = FileUiState.Error(
                    exception.message ?: "Unknown error"
                )
            }
        }
       // fileJOB?.cancel()
    }

    fun createFolder(name: String,folderId: String) {
        createJOB?.start()
        createJOB = viewModelScope.launch(Dispatchers.IO) {
            try {
            createFolderUseCase.execute(folderId, name).collect { result ->
                if (result is NetworkResult.Success) {
                    // Refresh current folder
                    loadFiles(folderId)
                }
            }
        }
        catch (exception: Exception) {
            _uiStateFile.value = FileUiState.Error(
                exception.message ?: "Unknown error"
            )
        }

        }
        createJOB?.cancel()
    }

    fun deleteItem(itemId: String) {
        deleteJOB?.start()
        deleteJOB = viewModelScope.launch(Dispatchers.IO) {
            try {
                deleteItemUseCase.execute(itemId).collect { result ->
                    if (result is NetworkResult.Success) {
                        loadFiles(itemId)
                    }
                }
            }
               catch (exception: Exception) {
                        _uiStateFile.value = FileUiState.Error(
                            exception.message ?: "Unknown error"
                        )
                    }
        }
        deleteJOB?.cancel()
    }
}