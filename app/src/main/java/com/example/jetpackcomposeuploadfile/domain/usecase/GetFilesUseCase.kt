package com.example.jetpackcomposeuploadfile.domain.usecase

import com.example.jetpackcomposeuploadfile.data.repository.FileRepository
import com.example.jetpackcomposeuploadfile.data.NetworkResult
import com.example.myapp.domain.model.FileItemModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetFilesUseCase @Inject constructor(private var repository: FileRepository) {
    fun execute(folderId: String?): Flow<NetworkResult<List<FileItemModel>>> = flow {
        repository.getFolderContent(folderId).collect {
            emit(it)
        }
    }
}
