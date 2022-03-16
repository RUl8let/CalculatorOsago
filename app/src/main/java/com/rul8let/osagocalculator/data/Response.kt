package com.rul8let.osagocalculator.data

sealed class Response<out R> {
    data class Success<out T>(val data: T) : Response<T>()
    data class Error(val error: Exception) : Response<Nothing>()
}