package com.example.myalarmapp.alarm.ui

import com.example.myalarmapp.alarm.data.Alarm
import com.example.myalarmapp.alarm.data.AlarmRepository
import com.example.myalarmapp.common.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AlarmViewModel @Inject constructor(
    private val repository: AlarmRepository
) : BaseViewModel<Alarm>(repository)
