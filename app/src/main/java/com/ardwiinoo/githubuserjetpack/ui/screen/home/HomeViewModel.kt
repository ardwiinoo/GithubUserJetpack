package com.ardwiinoo.githubuserjetpack.ui.screen.home

import androidx.lifecycle.ViewModel
import com.ardwiinoo.githubuserjetpack.data.remote.response.GithubResponse
import com.ardwiinoo.githubuserjetpack.data.repository.ApiRepository
import com.ardwiinoo.githubuserjetpack.utils.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: ApiRepository
) : ViewModel() {

    private val _userList = MutableStateFlow<Result<GithubResponse>>(Result.Loading)
    val userList: StateFlow<Result<GithubResponse>> = _userList.asStateFlow()

    suspend fun searchUsers(username: String) {
        _userList.value = Result.Loading
        val result = repository.searchUsers(username)
        _userList.value = result
    }
}