package com.mmb.domain.base

sealed interface Result<out T> {
    data class Success<out T>(val data: T) : Result<T>
    data class Error(val error: Exception) : Result<Nothing>
}

inline fun <T, R> Result<T>.map(
    onSuccess: (value: T) -> R,
): Result<R> {
    return when (this) {
        is Result.Error -> this
        is Result.Success -> Result.Success(onSuccess(data))
    }
}

fun <T, R> Result<List<T>>.mapResultList(
    transform: (value: T) -> R,
): Result<List<R>> = map {
    it.map(transform)
}

inline fun <R, P> Result<R>.onSuccess(action: (R) -> P): Result<R> {
    if (this is Result.Success) {
        action(this.data)
    }
    return this
}

inline fun <T> Result<T>.onError(onFailure: (Exception) -> Unit): Result<T> {
    if (this is Result.Error) onFailure(error)
    return this
}

fun <T> Result<T>?.requireData(): T {
    return (this as Result.Success).data
}