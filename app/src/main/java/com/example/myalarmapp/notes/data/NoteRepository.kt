package com.example.myalarmapp.notes.data

import com.example.myalarmapp.common.BaseRepository
import com.example.myalarmapp.notes.db.NoteDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NoteRepository @Inject constructor(
    private val noteDao: NoteDao
) : BaseRepository<Note> {

    override suspend fun insert(item: Note) {
        withContext(Dispatchers.IO) {
            noteDao.insert(item)
        }
    }

    override suspend fun getAll(): Flow<List<Note>> {
        return withContext(Dispatchers.IO) {
            noteDao.getAll()
        }
    }

    override suspend fun getById(id: Long): Note? {
        return withContext(Dispatchers.IO) {
            noteDao.getById(id)
        }
    }

    override suspend fun delete(item: Note) {
        withContext(Dispatchers.IO) {
            noteDao.delete(item)
        }
    }

    override suspend fun update(item: Note) {
        withContext(Dispatchers.IO) {
            noteDao.update(item)
        }
    }
}
