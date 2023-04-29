package com.mmb.data.di

import com.mmb.data.repository.PokemonRepositoryImpl
import com.mmb.domain.repository.PokemonRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindsPokemonRepository(impl: PokemonRepositoryImpl): PokemonRepository

}