package com.aradevs.domain

sealed class Status<out T> {
    class Initial<T> : Status<T>()
    class Loading<T> : Status<T>()
    class Success<T>(val data: T, vararg val extras: Any?) : Status<T>()
    class Error<T>(val exception: Exception) : Status<T>()
}