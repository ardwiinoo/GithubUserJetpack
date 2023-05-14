package com.ardwiinoo.githubuserjetpack.ui.screen.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.ardwiinoo.githubuserjetpack.data.remote.response.GithubResponse
import com.ardwiinoo.githubuserjetpack.data.repository.ApiRepository
import com.ardwiinoo.githubuserjetpack.utils.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: ApiRepository
): ViewModel() {

    private val _userList = mutableStateOf<Result<GithubResponse>>(Result.Loading)
    val userList: State<Result<GithubResponse>> = _userList

    suspend fun searchUsers(username: String) {
        _userList.value = Result.Loading
        val result = repository.searchUsers(username)
        _userList.value = result
    }
}