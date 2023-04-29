package com.mmb.data.local.datasource

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PreferenceLocalDataSourceImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>,
) : PreferenceLocalDataSource {

    private val offsetKey = intPreferencesKey("offset")

    override fun getPagingOffset(): Flow<Int> = dataStore.data.map {
        it[offsetKey] ?: 0
    }

    override suspend fun setPagingOffset(offset: Int) {
        dataStore.edit {
            it[offsetKey] = offset
        }
    }

}