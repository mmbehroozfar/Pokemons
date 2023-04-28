package com.mmb.data.local.di

import com.mmb.data.local.datasource.PokemonLocalDataSource
import com.mmb.data.local.datasource.PokemonLocalDataSourceImpl
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
    abstract fun bindsAssetLocalDataSource(impl: PokemonLocalDataSourceImpl): PokemonLocalDataSource

}