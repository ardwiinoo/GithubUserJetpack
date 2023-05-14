package com.ardwiinoo.githubuserjetpack.utils

sealed class Result<out T> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val message: String, val exception: Exception? = null) : Result<Nothing>()
    object Loading : Result<Nothing>()

    companion object {
        fun <T> success(data: T) = Success(data)
        fun error(message: String, exception: Exception? = null) = Error(message, exception)
        fun loading() = Loading
    }
}