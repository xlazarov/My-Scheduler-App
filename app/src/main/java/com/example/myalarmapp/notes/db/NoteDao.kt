package com.example.myalarmapp.notes.db

import androidx.room.Dao
import androidx.room.Query
import com.example.myalarmapp.common.BaseDao
import com.example.myalarmapp.notes.data.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao : BaseDao<Note> {

    /**
     * Get all alarms from the Alarms table.
     */
    @Query("SELECT * FROM Notes")
    fun getAll(): Flow<List<Note>>

    /**
     * Retrieve an alarm by its alarmId.
     */
    @Query("SELECT * FROM Notes WHERE id = :noteId")
    fun getById(noteId: Long): Note?
}