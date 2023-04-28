package com.mmb.data.local.typeconverter

import androidx.room.TypeConverter
import com.mmb.data.local.model.AbilityEntity
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class AbilityConvertor {

    @TypeConverter
    fun fromString(abilities: String): List<AbilityEntity> {
        return Json.decodeFromString(abilities)
    }

    @TypeConverter
    fun toString(items: List<AbilityEntity>): String {
        return Json.encodeToString(items)
    }
}