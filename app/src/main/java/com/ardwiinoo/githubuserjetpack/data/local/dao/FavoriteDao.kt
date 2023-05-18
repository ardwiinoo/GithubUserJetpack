package com.ardwiinoo.githubuserjetpack.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ardwiinoo.githubuserjetpack.data.local.entity.Favorite

@Dao
interface FavoriteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(favorite: Favorite)

    @Query("DELETE FROM favorite WHERE username = :username")
    suspend fun deleteFavorite(username: String)

    @Query("SELECT EXISTS(SELECT * FROM favorite WHERE username = :username)")
    suspend fun isFavoriteUser(username: String): Boolean

    @Query("SELECT * FROM favorite")
    suspend fun getAllFavorites(): List<Favorite>
}