package com.bayu.moviepaging.core.data.source.remote.api

sealed class ApiResponse<T>(val data: T? = null, val message: String? = null) {

    class Success<T>(data: T) : ApiResponse<T>(data = data)

    class Error<T>(message: String) : ApiResponse<T>(message = message)

}