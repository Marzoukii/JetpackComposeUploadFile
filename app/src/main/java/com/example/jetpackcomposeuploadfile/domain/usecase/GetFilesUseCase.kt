package com.example.jetpackcomposeuploadfile.domain.usecase

import com.example.jetpackcomposeuploadfile.data.repository.FileRepository
import com.example.jetpackcomposeuploadfile.data.NetworkResult
import com.example.myapp.domain.model.FileItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetFilesUseCase @Inject constructor(private var repository: FileRepository) {
    fun execute(folderId: String?): Flow<NetworkResult<List<FileItem>>> = flow {
        repository.getFolderContent(folderId).collect {
            emit(it)
        }
    }
}
