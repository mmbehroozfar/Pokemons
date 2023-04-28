package com.mmb.data.local.typeconverter

import androidx.room.TypeConverter
import com.mmb.data.local.model.StatEntity
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class StatConvertor {

    @TypeConverter
    fun fromString(stats: String): List<StatEntity> {
        return Json.decodeFromString(stats)
    }

    @TypeConverter
    fun toString(items: List<StatEntity>): String {
        return Json.encodeToString(items)
    }
}