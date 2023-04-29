package com.mmb.domain.usecase

import com.mmb.domain.base.FlowUseCase
import com.mmb.domain.model.PokemonDetail
import com.mmb.domain.repository.PokemonRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class GetPokemonDetailUseCase @Inject constructor(
    private val repository: PokemonRepository,
) : FlowUseCase<String, PokemonDetail>() {

    override fun execute(parameter: String): Flow<PokemonDetail> {
        return repository.getPokemonDetail(parameter)
    }

}