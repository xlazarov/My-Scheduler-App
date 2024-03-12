package com.example.myalarmapp.alarm.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlarmViewModel @Inject constructor(
    private val repository: AlarmRepository
) : ViewModel() {

    private val _alarms: MutableStateFlow<List<Alarm>> = MutableStateFlow(emptyList())
    val alarms: StateFlow<List<Alarm>> = _alarms

    init {
        getAllAlarms()
    }

    private fun getAllAlarms() {
        viewModelScope.launch {
            repository.getAllAlarms().collect { alarms ->
                _alarms.value = alarms
            }
        }
    }

    fun getAlarmById(alarmId: Long): Alarm? {
        return _alarms.value.find { it.id == alarmId }
    }

    fun insertAlarm(alarm: Alarm) {
        viewModelScope.launch {
            repository.insertAlarm(alarm)
        }
    }

    fun updateAlarm(alarm: Alarm) {
        viewModelScope.launch {
            repository.updateAlarm(alarm)
        }
    }

    fun deleteAlarm(alarm: Alarm) {
        viewModelScope.launch {
            repository.deleteAlarm(alarm)
        }
    }
}
