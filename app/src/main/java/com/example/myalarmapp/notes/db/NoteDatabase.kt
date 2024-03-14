package com.example.myalarmapp.notes.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myalarmapp.notes.data.Note

@Database(entities = [Note::class], version = 2, exportSchema = false)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
}