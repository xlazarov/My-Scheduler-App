package com.example.myalarmapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.myalarmapp.db.dao.AlarmDao
import com.example.myalarmapp.data.Alarm

@Database(entities = [Alarm::class], version = 1)
@TypeConverters(Converters::class)
abstract class AlarmDatabase : RoomDatabase() {
    abstract fun alarmDao(): AlarmDao
}