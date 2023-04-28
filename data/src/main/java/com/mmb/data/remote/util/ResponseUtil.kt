package com.mmb.data.remote.util

import com.mmb.domain.error.NetworkError
import java.net.SocketTimeoutException
import retrofit2.Response

suspend fun <T> bodyOrThrow(call: suspend () -> Response<T>): T = try {
    call().wrapResponse()
} catch (timeoutException: SocketTimeoutException) {
    throw NetworkError.Timeout
} catch (ioException: java.io.IOException) {
    throw NetworkError.NoInternet
} catch (exception: Exception) {
    throw exception
}

private fun <R> Response<R>.wrapResponse(): R = if (isSuccessful && body() != null) body()!!
else throw NetworkError.Server