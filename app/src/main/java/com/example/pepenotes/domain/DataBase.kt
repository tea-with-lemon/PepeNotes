package com.example.pepenotes.domain

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.pepenotes.converters.OdtConverter

@Database(
    entities = [Note::class],
    version = 1
)
@TypeConverters(OdtConverter::class)
abstract class DataBase: RoomDatabase() {
    abstract val dao: NoteDao
    companion object {
        fun createDataBase(context: Context):DataBase {
            return Room.databaseBuilder(
                context,
                DataBase::class.java,
                "noteDB"
            )
            .build()
        }
    }
}