package com.mmb.data.local.di

import android.content.Context
import androidx.room.Room
import com.mmb.data.local.dao.PokemonRoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context,
    ): PokemonRoomDatabase = Room
        .databaseBuilder(context, PokemonRoomDatabase::class.java, "pokemon-db")
        .build()

    @Provides
    @Singleton
    fun providePokemonDao(
        db: PokemonRoomDatabase,
    ) = db.pokemonDao()

}