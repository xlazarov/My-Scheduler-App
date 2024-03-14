package com.example.myalarmapp.alarm.data

import com.example.myalarmapp.alarm.db.AlarmDao
import com.example.myalarmapp.common.BaseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AlarmRepository @Inject constructor(
    private val alarmDao: AlarmDao
) : BaseRepository<Alarm> {

    override suspend fun insert(item: Alarm) {
        withContext(Dispatchers.IO) {
            alarmDao.insert(item)
        }
    }

    override suspend fun getAll(): Flow<List<Alarm>> {
        return withContext(Dispatchers.IO) {
            alarmDao.getAll()
        }
    }

    override suspend fun getById(id: Long): Alarm? {
        return withContext(Dispatchers.IO) {
            alarmDao.getById(id)
        }
    }

    override suspend fun delete(item: Alarm) {
        withContext(Dispatchers.IO) {
            alarmDao.delete(item)
        }
    }

    override suspend fun update(item: Alarm) {
        withContext(Dispatchers.IO) {
            alarmDao.update(item)
        }
    }
}
