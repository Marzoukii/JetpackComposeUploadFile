package com.example.jetpackcomposeuploadfile.domain.usecase

import com.example.jetpackcomposeuploadfile.data.repository.FileRepository
import com.example.jetpackcomposeuploadfile.data.NetworkResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetRootUseCase @Inject constructor(private var repository: FileRepository) {
    fun execute(): Flow<NetworkResult<String>>  = flow {
        repository.getRootFolderId().collect {
            emit(it)
        }
    }
}