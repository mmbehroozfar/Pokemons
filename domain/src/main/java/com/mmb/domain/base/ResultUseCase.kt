package com.mmb.domain.base

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

abstract class ResultUseCase<P, R>(private val coroutineDispatcher: CoroutineDispatcher) {

    suspend operator fun invoke(parameter: P): Result<R> {
        return withContext(coroutineDispatcher) {
            try {
                execute(parameter)
            } catch (e: Exception) {
                e.printStackTrace()
                Result.Error(e)
            }
        }
    }

    @Throws(RuntimeException::class)
    protected abstract suspend fun execute(parameter: P): Result<R>

}