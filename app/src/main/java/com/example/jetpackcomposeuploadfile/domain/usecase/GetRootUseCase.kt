package com.example.jetpackcomposeuploadfile.domain.usecase

import com.example.jetpackcomposeuploadfile.data.repository.FileRepository
import com.example.myapp.data.remote.NetworkResult
import com.example.myapp.domain.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetRootUseCase @Inject constructor(
) {
    @Inject
    lateinit var repository: FileRepository

    fun execute(): Flow<NetworkResult<String>>  = flow {
        repository.getRootFolderId().collect {
            emit(it)
        }
    }
}