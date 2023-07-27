package com.example.pepenotes.domain

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import java.time.OffsetDateTime

@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note: Note)

    @Delete
    suspend fun delete(note: Note)

    @Query("SELECT * FROM note_table")
    fun getAll(): Flow<List<Note>>

    @Query("SELECT * FROM note_table WHERE date = :date")
    fun getByDate(date: OffsetDateTime): Flow<List<Note>>
}