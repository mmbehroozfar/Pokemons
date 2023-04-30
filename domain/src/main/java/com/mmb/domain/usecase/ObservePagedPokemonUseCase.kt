package com.mmb.domain.usecase

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.mmb.domain.base.IoDispatcher
import com.mmb.domain.base.SubjectUseCase
import com.mmb.domain.model.Pokemon
import com.mmb.domain.repository.PokemonRepository
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow

class ObservePagedPokemonUseCase @Inject constructor(
    private val repository: PokemonRepository,
    @IoDispatcher coroutineDispatcher: CoroutineDispatcher,
) : SubjectUseCase<Unit, PagingData<Pokemon>>(
    coroutineDispatcher
) {

    override suspend fun createObservable(params: Unit): Flow<PagingData<Pokemon>> {
        val pagingConfig = PagingConfig(
            pageSize = 15,
            prefetchDistance = 3,
            enablePlaceholders = false,
            initialLoadSize = 15,
        )

        return repository.observePagedRepository(pagingConfig)
    }
}