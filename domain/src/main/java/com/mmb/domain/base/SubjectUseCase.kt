package com.mmb.domain.base

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOn

abstract class SubjectUseCase<in PARAMS, out TYPE>(private val coroutineDispatcher: CoroutineDispatcher) {
    // Ideally this would be buffer = 0, since we use flatMapLatest below, BUT invoke is not
    // suspending. This means that we can't suspend while flatMapLatest cancels any
    // existing flows. The buffer of 1 means that we can use tryEmit() and buffer the value
    // instead, resulting in mostly the same result.
    private val paramState = MutableSharedFlow<PARAMS>(
        replay = 1,
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )

    val flow: Flow<TYPE> = paramState
        .distinctUntilChanged()
        .flatMapLatest { createObservable(it).flowOn(coroutineDispatcher) }
        .distinctUntilChanged()

    operator fun invoke(params: PARAMS) {
        paramState.tryEmit(params)
    }

    protected abstract suspend fun createObservable(params: PARAMS): Flow<TYPE>
}