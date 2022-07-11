package com.saba.searching.core.data

import androidx.annotation.Keep

@Keep
data class Response<out T>(
    val status: Status,
    val data: T? = null,
    val error: T? = null,
    val errorName: String? = null
) {
    enum class Status { LOADING, SUCCESS, ERROR }

    companion object {
        fun <T> loading(): Response<T> = Response(Status.LOADING, null, null)
        fun <T> success(data: T? = null): Response<T>? = Response(Status.SUCCESS, data, null)
        fun <T> error(error: T? = null): Response<T> = Response(Status.ERROR, null, error)
        fun <T> error(error: String? = null): Response<T> =
            Response(Status.ERROR, null, null, error)
    }
}
