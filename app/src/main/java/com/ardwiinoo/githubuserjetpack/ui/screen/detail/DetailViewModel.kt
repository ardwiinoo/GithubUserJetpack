package com.ardwiinoo.githubuserjetpack.ui.screen.detail

import androidx.lifecycle.ViewModel
import com.ardwiinoo.githubuserjetpack.data.local.entity.Favorite
import com.ardwiinoo.githubuserjetpack.data.remote.response.GithubDetailResponse
import com.ardwiinoo.githubuserjetpack.data.repository.ApiRepository
import com.ardwiinoo.githubuserjetpack.data.repository.LocalRepository
import com.ardwiinoo.githubuserjetpack.utils.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: ApiRepository,
    private val localRepository: LocalRepository
) : ViewModel() {

    private val _userDetail = MutableStateFlow<Result<GithubDetailResponse>>(Result.Loading)
    val userDetail: StateFlow<Result<GithubDetailResponse>> = _userDetail.asStateFlow()

    suspend fun getDetailUser(username: String) {
        _userDetail.value = Result.Loading
        val result = repository.getDetailUser(username)
        _userDetail.value = result
    }

    suspend fun addToFavorites(favorite: Favorite) {
        localRepository.insertFavorite(favorite)
    }

    suspend fun removeFromFavorites(username: String) {
        localRepository.deleteFavorite(username)
    }

    suspend fun isFavoriteUser(username: String): Boolean {
        return localRepository.isFavoriteUser(username)
    }
}