package com.example.jetpackcomposeuploadfile.data.repository

import com.example.jetpackcomposeuploadfile.data.service.FileService
import com.example.myapp.data.mapper.FileMapper
import com.example.myapp.data.remote.NetworkResult
import com.example.myapp.domain.model.FileItem
import com.example.myapp.domain.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FileRepository @Inject constructor(

) {
    @Inject
    lateinit var api: FileService
    @Inject
    lateinit var mapper: FileMapper
    suspend fun getCurrentUser(): Flow<NetworkResult<User>> =  flow {
   try {
            NetworkResult.Success(
                mapper.toUsersModel(api.getCurrentUser())
            )
        } catch (e: Exception) {
            NetworkResult.Error(e)
        }
    }

    fun getRootFolderId(): Flow<NetworkResult<String>> =
        flow {
        try {
            val response = api.getCurrentUser()

            emit(
                NetworkResult.Success(
                    response.rootItem?.id.orEmpty()
                )
            )
        } catch (e: Exception) {
            emit(NetworkResult.Error(e))
        }
    }

    suspend fun getFolderContent(folderId: String): Flow<NetworkResult<List<FileItem>>> = flow {
    try {
            NetworkResult.Success(mapper.toFileItemsModel(api.getFolderContent(folderId)))
        } catch (e: Exception) {
            NetworkResult.Error(e)
        }
    }
}
