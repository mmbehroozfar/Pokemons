package com.mmb.domain.usecase

import com.mmb.domain.base.IoDispatcher
import com.mmb.domain.base.Result
import com.mmb.domain.base.ResultUseCase
import com.mmb.domain.repository.PokemonRepository
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher

class FetchPokemonDetailUseCase @Inject constructor(
    private val repository: PokemonRepository,
    @IoDispatcher coroutineDispatcher: CoroutineDispatcher,
) : ResultUseCase<String, Unit>(coroutineDispatcher) {

    override suspend fun execute(parameter: String): Result<Unit> {
        repository.fetchPokemonDetail(parameter)

        return Result.Success(Unit)
    }

}