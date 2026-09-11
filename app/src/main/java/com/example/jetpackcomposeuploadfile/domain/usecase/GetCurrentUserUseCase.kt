package com.example.myapp.domain.usecase

import com.example.jetpackcomposeuploadfile.data.repository.FileRepository
import com.example.jetpackcomposeuploadfile.data.NetworkResult
import com.example.myapp.domain.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCurrentUserUseCase @Inject constructor(private var repository: FileRepository) {

    fun execute(): Flow<NetworkResult<User>>  = flow {
        repository.getCurrentUser().collect {
            emit(it)
        }
    }
}