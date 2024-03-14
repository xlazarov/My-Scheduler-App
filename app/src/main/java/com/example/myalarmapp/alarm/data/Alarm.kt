package com.example.myalarmapp.alarm.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Alarms")
data class Alarm(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val title: String = "Alarm",
    val hour: Int = 8,
    val minute: Int = 0,
    val isOn: Boolean = true,
    val daysOfWeek: List<String> = emptyList(),
    val volume: Float = 0.5f,
    val vibrationOn: Boolean = true,
    val tune: String = "TODO ringtones"
)