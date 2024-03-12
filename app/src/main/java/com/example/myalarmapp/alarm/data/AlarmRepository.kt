package com.example.myalarmapp.alarm.data

import com.example.myalarmapp.alarm.db.AlarmDao
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
        return withContext(Dispatchers.IO) {
            alarmDao.getAllAlarms()
        }
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

    suspend fun getAlarmById(alarmId: Long): Alarm? {
        return withContext(Dispatchers.IO) {
            alarmDao.getAlarmById(alarmId)
        }
    }
}
