package com.mmb.data.local.datasource

import kotlinx.coroutines.flow.Flow

interface PreferenceLocalDataSource {

    fun getPagingOffset(): Flow<Int>

    suspend fun setPagingOffset(offset: Int)

}