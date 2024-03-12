package com.example.myalarmapp.alarm.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.myalarmapp.alarm.data.Alarm

@Database(entities = [Alarm::class], version = 3)
@TypeConverters(Converters::class)
abstract class AlarmDatabase : RoomDatabase() {
    abstract fun alarmDao(): AlarmDao
}