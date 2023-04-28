package com.mmb.data.remote.di

import com.mmb.data.remote.datasource.PokemonRemoteDataSource
import com.mmb.data.remote.datasource.PokemonRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    @Singleton
    abstract fun bindsPokemonRemoteDataSource(impl: PokemonRemoteDataSourceImpl): PokemonRemoteDataSource

}