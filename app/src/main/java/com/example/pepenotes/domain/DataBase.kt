package com.example.pepenotes.domain

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.pepenotes.converters.OdtConverter

@Database(
    entities = [Note::class],
    version = 3
)
@TypeConverters(OdtConverter::class)
abstract class DataBase: RoomDatabase() {
    abstract val dao: NoteDao
    companion object {
        private val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE note_table ADD COLUMN title TEXT NOT NULL default ''")
            }
        }
        private val MIGRATION_2_3 = object : Migration(2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("UPDATE note_table SET title = SUBSTR(text, 1, INSTR(text, '\n') -1)")
            }
        }

        fun createDataBase(context: Context):DataBase {
            return Room.databaseBuilder(
                context,
                DataBase::class.java,
                "noteDB"
            ).addMigrations(MIGRATION_1_2).addMigrations(MIGRATION_2_3)
            .build()
        }
    }
}