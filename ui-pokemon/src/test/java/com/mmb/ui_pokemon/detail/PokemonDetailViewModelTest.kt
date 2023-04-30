package com.mmb.ui_pokemon.detail

import androidx.lifecycle.SavedStateHandle
import com.google.common.truth.Truth
import com.mmb.domain.base.Result
import com.mmb.domain.model.PokemonDetail
import com.mmb.domain.usecase.FetchPokemonDetailUseCase
import com.mmb.domain.usecase.GetPokemonDetailUseCase
import com.mmb.ui_pokemon.PokemonGraph
import com.mmb.ui_pokemon.util.CoroutinesTestExtension
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.verify
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(CoroutinesTestExtension::class)
internal class PokemonDetailViewModelTest {

    @RelaxedMockK
    lateinit var fetchPokemonDetailUseCase: FetchPokemonDetailUseCase

    @RelaxedMockK
    lateinit var getPokemonDetailUseCase: GetPokemonDetailUseCase

    @BeforeEach
    fun setup() {
        MockKAnnotations.init(this)
    }

    @Test
    fun initialStateShouldCallUseCase() = runTest {
        val name = "pokemon"
        createViewModel(name)

        coVerify(exactly = 1) { fetchPokemonDetailUseCase(name) }
        verify(exactly = 1) { getPokemonDetailUseCase(name) }
    }

    @Test
    fun fetchPokemonDetailIntentShouldCallUseCase() = runTest {
        val name = "pokemon"
        val viewModel = createViewModel(name)
        viewModel.dispatchIntent(PokemonDetailIntent.FetchPokemonDetail)

        coVerify(exactly = 2) { fetchPokemonDetailUseCase(name) }
    }

    @Test
    fun emitDataShouldUpdateState() = runTest {
        every { getPokemonDetailUseCase(any()) } returns flowOf(PokemonDetail(id = 1))
        val viewModel = createViewModel()

        val state = viewModel.state.first()
        Truth.assertThat(state.pokemonDetail.id).isEqualTo(1)
    }

    @Test
    fun successFetchShouldUpdateState() = runTest {
        coEvery { fetchPokemonDetailUseCase(any()) } returns Result.Success(Unit)
        val viewModel = createViewModel()

        val state = viewModel.state.first()
        Truth.assertThat(state.status).isInstanceOf(Result.Success::class.java)
    }

    @Test
    fun failedFetchShouldUpdateState() = runTest {
        coEvery { fetchPokemonDetailUseCase(any()) } returns Result.Error(Exception())
        val viewModel = createViewModel()

        val state = viewModel.state.first()
        Truth.assertThat(state.status).isInstanceOf(Result.Error::class.java)
    }

    private fun createViewModel(name: String = "pokemon") = PokemonDetailViewModel(
        savedStateHandle = SavedStateHandle(mapOf(PokemonGraph.PokemonDetail.NAME_ARGUMENT to name)),
        fetchPokemonDetailUseCase = fetchPokemonDetailUseCase,
        getPokemonDetailUseCase = getPokemonDetailUseCase,
    )
}