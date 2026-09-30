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
    private val getFilesUseCase: GetFilesUseCase,
    private val getRootUseCase: GetRootUseCase,
    private val createFolderUseCase: CreateFolderUseCase,
    private val deleteItemUseCase: DeleteItemUseCase
) : ViewModel() {

    private var fileJob: Job? = null
    private var userJob: Job? = null
    private var actionJob: Job? = null

    private val _uiStateFile = MutableStateFlow<FileUiState>(FileUiState.Loading)
    val uiStateFile: StateFlow<FileUiState> = _uiStateFile.asStateFlow()

    private var currentFolderId: String? = null

    init {
        loadRoot()
    }

    fun loadRoot() {
        userJob?.cancel()
        userJob = viewModelScope.launch(Dispatchers.IO) {
            _uiStateFile.value = FileUiState.Loading
            getRootUseCase.execute().collect { result ->
                when (result) {
                    is NetworkResult.Success -> {
                        currentFolderId = result.data
                        loadFiles(result.data)
                    }
                    is NetworkResult.Error -> handleError(result.exception)
                }
            }
        }
    }

    fun loadFiles(folderId: String?) {
        fileJob?.cancel()
        currentFolderId = folderId
        fileJob = viewModelScope.launch(Dispatchers.IO) {
            _uiStateFile.value = FileUiState.Loading
            getFilesUseCase.execute(folderId).collect { result ->
                when (result) {
                    is NetworkResult.Success -> {
                        _uiStateFile.value = FileUiState.Success(result.data)
                    }
                    is NetworkResult.Error -> handleError(result.exception)
                }
            }
        }
    }

    fun createFolder(name: String) {
        val parentId = currentFolderId ?: return
        actionJob?.cancel()
        actionJob = viewModelScope.launch(Dispatchers.IO) {
            createFolderUseCase.execute(parentId, name).collect { result ->
                when (result) {
                    is NetworkResult.Success -> loadFiles(parentId)
                    is NetworkResult.Error -> handleError(result.exception)
                }
            }
        }
    }

    fun deleteItem(itemId: String?) {
        if (itemId == null) return
        actionJob?.cancel()
        actionJob = viewModelScope.launch(Dispatchers.IO) {
            deleteItemUseCase.execute(itemId).collect { result ->
                when (result) {
                    is NetworkResult.Success -> loadFiles(currentFolderId)
                    is NetworkResult.Error -> handleError(result.exception)
                }
            }
        }
    }

    private fun handleError(exception: Exception) {
        val message = exception.message ?: "Une erreur inattendue est survenue"
        _uiStateFile.value = FileUiState.Error(message)
    }
}
