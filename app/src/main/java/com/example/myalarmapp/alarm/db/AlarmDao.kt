package com.example.myalarmapp.alarm.db

import androidx.room.*
import com.example.myalarmapp.common.BaseDao
import com.example.myalarmapp.alarm.data.Alarm
import kotlinx.coroutines.flow.Flow

@Dao
interface AlarmDao : BaseDao<Alarm> {

    /**
     * Get all alarms from the Alarms table.
     */
    @Query("SELECT * FROM Alarms")
    fun getAllAlarms(): Flow<List<Alarm>>

    /**
     * Retrieve an alarm by its alarmId.
     */
    @Query("SELECT * FROM Alarms WHERE id = :alarmId")
    fun getAlarmById(alarmId: Long): Alarm?
}