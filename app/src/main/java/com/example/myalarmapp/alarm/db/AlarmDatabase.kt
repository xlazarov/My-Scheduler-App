package com.example.myalarmapp.alarm.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.myalarmapp.alarm.data.Alarm

@Database(entities = [Alarm::class], version = 4, exportSchema = false)
@TypeConverters(ListConverters::class)
abstract class AlarmDatabase : RoomDatabase() {
    abstract fun alarmDao(): AlarmDao
}