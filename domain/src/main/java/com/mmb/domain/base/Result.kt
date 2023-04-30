package com.mmb.domain.base

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

sealed interface Result<out T> {
    object Loading : Result<Nothing>
    data class Success<out T>(val data: T) : Result<T>
    data class Error(val error: Exception) : Result<Nothing>
}

inline fun <T, R> Result<T>.map(
    onSuccess: (value: T) -> R,
): Result<R> {
    return when (this) {
        is Result.Error -> this
        is Result.Success -> Result.Success(onSuccess(data))
        is Result.Loading -> this
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

fun <T> Result<T>.isLoading(): Boolean {
    return this is Result.Loading
}

fun <T> Result<T>.isSuccess(): Boolean {
    return this is Result.Success
}

@OptIn(ExperimentalContracts::class)
fun <T> Result<T>.isError(): Boolean {
    contract {
        returns(true) implies (this@isError is Result.Error)
    }
    return this is Result.Error
}