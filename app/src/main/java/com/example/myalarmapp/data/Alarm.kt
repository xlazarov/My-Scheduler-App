package com.example.myalarmapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Alarms")
data class Alarm(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val isOn: Boolean = true,
    val hour: Int = 8,
    val minute: Int = 0,
    val daysOfWeek: List<String> = emptyList()
)