package com.ardwiinoo.githubuserjetpack.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite")
data class Favorite (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    val id: Long = 0L,
    val avatarUrl: String,
    val username: String
) {
    constructor() : this(0L, "", "")
}