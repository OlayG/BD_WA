package com.example.lowesweatherapp.util

sealed class DataState<out R> {

    data class Success<T>(val data: T) : DataState<T>()
    data class Error(val errMsg: String) : DataState<Nothing>()
    object Loading : DataState<Nothing>()
    object NoAction : DataState<Nothing>()
}
