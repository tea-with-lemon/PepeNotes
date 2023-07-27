package com.example.pepenotes.domain

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.pepenotes.converters.OdtConverter
import java.time.OffsetDateTime

@Entity(tableName = "note_table")
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    @TypeConverters(OdtConverter::class)
    var date: OffsetDateTime = OffsetDateTime.now(),
    var checkBox: Boolean = false,
    var text: String = ""
)
