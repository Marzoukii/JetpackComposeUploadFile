package com.example.jetpackcomposeuploadfile.data.repository

import com.example.jetpackcomposeuploadfile.data.service.FileService
import com.example.jetpackcomposeuploadfile.domain.mapper.FileMapper
import com.example.jetpackcomposeuploadfile.data.NetworkResult
import com.example.myapp.domain.model.FileItemModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FileRepository @Inject constructor( private var api: FileService , private  var mapper: FileMapper) {

    suspend fun getRootFolderId(): Flow<NetworkResult<String>> = flow {
        try {

            emit(NetworkResult.Success(api.getCurrentUser().rootItem?.id.orEmpty()))
        } catch (e: Exception) {
            emit(NetworkResult.Error(e))
        }
    }

    fun getFolderContent(folderId: String?): Flow<NetworkResult<List<FileItemModel>>> = flow {
        try {
            val response = api.getFolderContent(folderId)

            emit(NetworkResult.Success(mapper.toFileItemsModel(response)))
        } catch (e: Exception) {
            emit(NetworkResult.Error(e))
        }
    }

    suspend fun createFolder(parentId: String, folderName: String): Flow<NetworkResult<FileItemModel>> =
        flow {
            try {
                val body = mapOf("name" to folderName)
                val response = api.createFolder(parentId, body)
                emit(NetworkResult.Success(response))
            } catch (e: Exception) {
                emit(NetworkResult.Error(e))
            }
        }

    fun deleteItem(folderId: String?): Flow<NetworkResult<Unit>> = flow {
        try {
            val response = api.deleteItem(folderId)

            if (response.isSuccessful) {
                emit(NetworkResult.Success(Unit))
            } else {
                emit(NetworkResult.Error(Exception("Delete failed with code: ${response.code()}")))
            }
        } catch (e: Exception) {
            emit(NetworkResult.Error(e))
        }
    }
}
