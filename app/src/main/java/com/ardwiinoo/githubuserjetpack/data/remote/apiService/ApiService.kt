package com.ardwiinoo.githubuserjetpack.data.remote.apiService

import com.ardwiinoo.githubuserjetpack.data.remote.response.GithubDetailResponse
import com.ardwiinoo.githubuserjetpack.data.remote.response.GithubFollowerResponse
import com.ardwiinoo.githubuserjetpack.data.remote.response.GithubFollowingResponse
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
    ): GithubDetailResponse

    @GET("users/{username}/followers")
    suspend fun getUserFollowers(
        @Path("username") username: String,
    ): GithubFollowerResponse

    @GET("users/{username}/following")
    suspend fun getUserFollowings(
        @Path("username") username: String,
    ): GithubFollowingResponse

}