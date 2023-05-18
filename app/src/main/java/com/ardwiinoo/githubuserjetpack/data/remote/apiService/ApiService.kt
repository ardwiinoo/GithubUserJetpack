package com.ardwiinoo.githubuserjetpack.data.remote.apiService

import com.ardwiinoo.githubuserjetpack.data.remote.response.GithubDetailResponse
import com.ardwiinoo.githubuserjetpack.data.remote.response.GithubResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("search/users")
    suspend fun getUsers(
        @Query("q") query: String,
    ): Response<GithubResponse>

    @GET("users/{username}")
    suspend fun getDetailUser(
        @Path("username") username: String,
    ): Response<GithubDetailResponse>

}