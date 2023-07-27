package com.example.picker.converters

import androidx.room.TypeConverter
import java.time.Instant
import java.time.OffsetDateTime
import java.time.ZoneId

class OdtConverter {
    @TypeConverter
    fun fromDate(value: Long?): OffsetDateTime? {
        return value?.let {
            OffsetDateTime.ofInstant(
                Instant.ofEpochMilli(it),
                ZoneId.systemDefault()
            )
        }
    }

    @TypeConverter
    fun dateToLong(date: OffsetDateTime?): Long? {
        return date?.toLocalDate()?.atStartOfDay()?.toInstant(date.offset)?.toEpochMilli()
    }
}