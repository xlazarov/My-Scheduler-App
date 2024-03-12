package com.example.myalarmapp.db.dao

import androidx.room.*
import com.example.myalarmapp.data.Alarm
import kotlinx.coroutines.flow.Flow

@Dao
interface AlarmDao : BaseDao<Alarm> {

    /**
     * Get all alarms from the Alarms table.
     */
    @Query("SELECT * FROM Alarms")
    fun getAllAlarms(): Flow<List<Alarm>>
}