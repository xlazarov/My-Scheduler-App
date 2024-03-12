package com.example.myalarmapp.alarm.di

import android.content.Context
import androidx.room.Room
import com.example.myalarmapp.alarm.db.AlarmDatabase
import com.example.myalarmapp.alarm.db.AlarmDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Provides
    fun provideLogDao(database: AlarmDatabase): AlarmDao {
        return database.alarmDao()
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): AlarmDatabase {
        return Room.databaseBuilder(
            appContext,
            AlarmDatabase::class.java,
            "alarms.db"
        ).fallbackToDestructiveMigration().build()
    }
}