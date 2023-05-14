package com.ardwiinoo.githubuserjetpack.data.repository

import com.ardwiinoo.githubuserjetpack.data.remote.apiService.ApiService
import com.ardwiinoo.githubuserjetpack.data.remote.response.GithubResponse
import javax.inject.Inject
import com.ardwiinoo.githubuserjetpack.utils.Result

class ApiRepository @Inject constructor(
    private val apiService: ApiService
) {

    suspend fun searchUsers(username: String): Result<GithubResponse> {

        val response = apiService.getUsers(username)
        val result = response.body()

        return try {
            if(response.isSuccessful && result != null) {
                Result.Success(result)
            } else {
                val errorBody = response.errorBody()
                val errorMessage = errorBody?.toString() ?: "Unknown error"
                Result.Error(errorMessage)
            }
        } catch (e: Exception) {
            Result.Error(e.message ?: "Unknown error")
        }
    }

}