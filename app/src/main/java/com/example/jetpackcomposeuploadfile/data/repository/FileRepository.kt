package com.example.jetpackcomposeuploadfile.data.repository

import com.example.jetpackcomposeuploadfile.data.service.FileService
import com.example.myapp.data.mapper.FileMapper
import com.example.myapp.data.remote.NetworkResult
import com.example.myapp.domain.model.FileItem
import com.example.myapp.domain.model.User
import javax.inject.Inject

class FileRepository @Inject constructor(
    private val api: FileService,
    private val mapper: FileMapper
) {

    suspend fun getCurrentUser(): NetworkResult<User> {
        return try {
            NetworkResult.Success(
                mapper.toUsersModel(api.getCurrentUser())
            )
        } catch (e: Exception) {
            NetworkResult.Error(e)
        }
    }

    suspend fun getRootFolderId(): NetworkResult<String> {
        return try {
            api.getCurrentUser().rootItem?.id?.let { rootFolderId ->
                NetworkResult.Success(rootFolderId)
            } ?: NetworkResult.Error(
                Exception("Root folder ID is null")
            )
        } catch (e: Exception) {
            NetworkResult.Error(e)
        }
    }

    suspend fun getFolderContent(
        folderId: String
    ): NetworkResult<List<FileItem>> {
        return try {
            NetworkResult.Success(
                mapper.toFileItemsModel(
                    api.getFolderContent(folderId)
                )
            )
        } catch (e: Exception) {
            NetworkResult.Error(e)
        }
    }
}
