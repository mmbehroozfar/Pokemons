package com.mmb.data.local.typeconverter

import androidx.room.TypeConverter
import com.mmb.data.local.model.HeldItemEntity
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class HeldItemConvertor {

    @TypeConverter
    fun fromString(heldItems: String): List<HeldItemEntity> {
        return Json.decodeFromString(heldItems)
    }

    @TypeConverter
    fun toString(items: List<HeldItemEntity>): String {
        return Json.encodeToString(items)
    }
}