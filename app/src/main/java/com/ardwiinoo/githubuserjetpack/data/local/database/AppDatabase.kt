package com.ardwiinoo.githubuserjetpack.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ardwiinoo.githubuserjetpack.data.local.dao.FavoriteDao
import com.ardwiinoo.githubuserjetpack.data.local.entity.Favorite

@Database(
    entities = [Favorite::class],
    version = 2,
    exportSchema = false
)

abstract class AppDatabase : RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao
}