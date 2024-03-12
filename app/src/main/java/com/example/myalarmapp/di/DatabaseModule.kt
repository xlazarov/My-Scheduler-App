package com.example.myalarmapp.di

import android.content.Context
import androidx.room.Room
import com.example.myalarmapp.db.AlarmDatabase
import com.example.myalarmapp.db.dao.AlarmDao
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
        ).build()
    }
}