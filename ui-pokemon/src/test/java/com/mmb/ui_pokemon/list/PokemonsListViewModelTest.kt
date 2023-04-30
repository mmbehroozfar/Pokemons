package com.mmb.ui_pokemon.list

import androidx.paging.PagingData
import app.cash.turbine.test
import com.google.common.truth.Truth
import com.mmb.domain.model.Pokemon
import com.mmb.domain.usecase.ObservePagedPokemonUseCase
import com.mmb.ui_pokemon.util.CoroutinesTestExtension
import io.mockk.MockKAnnotations
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
internal class PokemonsListViewModelTest {

    @RelaxedMockK
    lateinit var observePagedPokemonUseCase: ObservePagedPokemonUseCase

    @BeforeEach
    fun setup() {
        MockKAnnotations.init(this)
    }

    @Test
    fun initialStateShouldCallUseCase() = runTest {
        createViewModel()

        verify(exactly = 1) { observePagedPokemonUseCase(Unit) }
    }

    @Test
    fun initialStateShouldUpdateState() = runTest {
        val pagingFlow = flowOf(
            PagingData.from(
                listOf(
                    Pokemon(
                        id = 0,
                        name = "",
                        url = ""
                    )
                )
            )
        )
        every { observePagedPokemonUseCase.flow } returns pagingFlow

        val viewModel = createViewModel()
        val state = viewModel.state.first()

        Truth.assertThat(state.pokemons).isEqualTo(pagingFlow)
    }

    @Test
    fun onPokemonItemClickedIntentShouldEmitEffect() = runTest {
        val name = "pokemon"
        val viewModel = createViewModel()
        viewModel.dispatchIntent(PokemonsListIntent.OnPokemonItemClicked(name))

        viewModel.effect.test {
            val item = awaitItem()

            Truth.assertThat(item)
                .isInstanceOf(PokemonsListEffect.OnNavigateToDetailScreen::class.java)
            Truth.assertThat((item as PokemonsListEffect.OnNavigateToDetailScreen).name)
                .isEqualTo(name)
        }
    }

    private fun createViewModel() = PokemonsListViewModel(observePagedPokemonUseCase)
}