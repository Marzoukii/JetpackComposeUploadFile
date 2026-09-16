package com.example.jetpackcomposeuploadfile.domain.usecase

import com.example.jetpackcomposeuploadfile.data.NetworkResult
import com.example.jetpackcomposeuploadfile.data.repository.FileRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DeleteItemUseCase @Inject constructor(private var repository: FileRepository) {

    fun execute(folderId: String?): Flow<NetworkResult<Unit>> = flow {
        repository.deleteItem(folderId).collect {
            emit(it)
        }
    }
}