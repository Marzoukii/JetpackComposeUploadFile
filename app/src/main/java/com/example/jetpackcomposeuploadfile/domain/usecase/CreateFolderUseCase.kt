package com.example.jetpackcomposeuploadfile.domain.usecase

import com.example.jetpackcomposeuploadfile.data.NetworkResult
import com.example.jetpackcomposeuploadfile.data.repository.FileRepository
import com.example.jetpackcomposeuploadfile.domain.model.FileItemModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CreateFolderUseCase @Inject constructor(private var repository: FileRepository) {

    fun execute(parentId: String, name: String): Flow<NetworkResult<FileItemModel>> = flow {
        repository.createFolder(parentId,name).collect {
            emit(it)
        }
    }
}