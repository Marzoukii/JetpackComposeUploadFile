package com.example.jetpackcomposeuploadfile.data.repository

import com.example.jetpackcomposeuploadfile.data.service.FileService
import com.example.myapp.data.mapper.toDomain
import com.example.myapp.data.remote.NetworkResult
import com.example.myapp.domain.model.FileItem
import com.example.myapp.domain.model.User
import javax.inject.Inject

class FileRepository @Inject constructor(
private val api: FileService
) {

    suspend fun getCurrentUser(): NetworkResult<User> {
        return try {
            NetworkResult.Success(api.getCurrentUser().toDomain())
        } catch (e: Exception) {
            NetworkResult.Error(e)
        }
    }

    suspend fun getRootFolderId(): NetworkResult<String> {
        return try {
            NetworkResult.Success(api.getCurrentUser().rootItem.id)
        } catch (e: Exception) {
            NetworkResult.Error(e)
        }
    }

    suspend fun getFolderContent(folderId: String): NetworkResult<List<FileItem>> {
        return try {
            NetworkResult.Success(api.getFolderContent(folderId).toDomain())
        } catch (e: Exception) {
            NetworkResult.Error(e)
        }
    }
}
