package com.example.myalarmapp.data

import com.example.myalarmapp.db.dao.AlarmDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AlarmRepository @Inject constructor(private val alarmDao: AlarmDao) {

    suspend fun insertAlarm(alarm: Alarm) {
        withContext(Dispatchers.IO) {
            alarmDao.insert(alarm)
        }
    }

    suspend fun getAllAlarms(): Flow<List<Alarm>> {
        var allAlarms: Flow<List<Alarm>>
        withContext(Dispatchers.IO) {
            allAlarms = alarmDao.getAllAlarms()
        }
        return allAlarms
    }

    suspend fun updateAlarm(alarm: Alarm) {
        withContext(Dispatchers.IO) {
            alarmDao.update(alarm)
        }
    }

    suspend fun deleteAlarm(alarm: Alarm) {
        withContext(Dispatchers.IO) {
            alarmDao.delete(alarm)
        }
    }
}
