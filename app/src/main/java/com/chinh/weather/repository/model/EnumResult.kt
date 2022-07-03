package com.chinh.weather.repository.model

import javax.annotation.concurrent.NotThreadSafe

enum class ApiStatus {
    SUCCESS,
    ERROR,
    LOADING
}  // for your case might be simplify to use only sealed class

sealed class ApiResult<out T>(
    val status: ApiStatus,
    val data: T?,
    val message: String?,
    val exceptionObject: Throwable? = null
) {

    data class Success<out R>(val _data: R?) : ApiResult<R>(
        status = ApiStatus.SUCCESS,
        data = _data,
        message = null
    )

    data class Error(val exception: String?, val _exceptionObject: Throwable? = null) : ApiResult<Nothing>(
        status = ApiStatus.ERROR,
        exceptionObject = _exceptionObject,
        data = null,
        message = exception
    )

    data class Loading<out R>(val _data: R? = null, val isLoading: Boolean) : ApiResult<R>(
        status = ApiStatus.LOADING,
        data = _data,
        message = null
    )
}