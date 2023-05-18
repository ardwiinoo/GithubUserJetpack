package com.ardwiinoo.githubuserjetpack.data.repository

import com.ardwiinoo.githubuserjetpack.data.local.database.AppDatabase
import com.ardwiinoo.githubuserjetpack.data.local.entity.Favorite
import javax.inject.Inject

class LocalRepository @Inject constructor(
    private val database: AppDatabase
) {
    suspend fun insertFavorite(favorite: Favorite) {
        database.favoriteDao().insertUser(favorite)
    }

    suspend fun deleteFavorite(username: String) {
        database.favoriteDao().deleteFavorite(username)
    }

    suspend fun isFavoriteUser(username: String): Boolean {
        return database.favoriteDao().isFavoriteUser(username)
    }

    suspend fun getAllFavorites(): List<Favorite> {
        return database.favoriteDao().getAllFavorites()
    }
}