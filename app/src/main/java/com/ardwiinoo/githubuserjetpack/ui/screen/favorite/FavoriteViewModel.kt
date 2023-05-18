package com.ardwiinoo.githubuserjetpack.ui.screen.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ardwiinoo.githubuserjetpack.data.local.entity.Favorite
import com.ardwiinoo.githubuserjetpack.data.repository.LocalRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val localRepository: LocalRepository
) : ViewModel() {

    private val _favorites = MutableStateFlow<List<Favorite>>(emptyList())
    val favorites: StateFlow<List<Favorite>> = _favorites.asStateFlow()

    init {
        viewModelScope.launch {
            loadFavorites()
        }
    }

    suspend fun loadFavorites() {
        val favorites = localRepository.getAllFavorites()
        _favorites.value = favorites
    }
}