package com.example.myalarmapp.alarm.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Done
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.myalarmapp.alarm.data.Alarm
import com.example.myalarmapp.alarm.ui.components.AlarmSoundSetting
import com.example.myalarmapp.alarm.ui.components.DaysToRepeatAlarm
import com.example.myalarmapp.alarm.ui.components.TimePicker
import com.example.myalarmapp.common.components.TopBar


@Composable
fun SetAlarmScreen(
    alarm: Alarm = Alarm(),
    isNew: Boolean = true,
    viewModel: AlarmViewModel = hiltViewModel(),
    navController: NavController
) {
    var selectedHour by remember { mutableIntStateOf(alarm.hour) }
    var selectedMinute by remember { mutableIntStateOf(alarm.minute) }
    var selectedDays by remember { mutableStateOf(alarm.daysOfWeek) }
    var setVolume by remember { mutableFloatStateOf(alarm.volume) }
    var setVibration by remember { mutableStateOf(alarm.vibrationOn) }

    Scaffold(
        topBar = {
            TopBar(
                topBarTitle = alarm.title,
                navController = navController
            ) {
                if (!isNew) viewModel.deleteItem(alarm)
                navController.navigateUp()
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier.clip(CircleShape),
                onClick = {
                    if (!isNew) {
                        viewModel.updateItem(
                            alarm.copy(
                                hour = selectedHour,
                                minute = selectedMinute,
                                daysOfWeek = selectedDays,
                                volume = setVolume,
                                vibrationOn = setVibration
                            )
                        )
                    } else {
                        viewModel.insertItem(
                            Alarm(
                                hour = selectedHour,
                                minute = selectedMinute,
                                daysOfWeek = selectedDays,
                                volume = setVolume,
                                vibrationOn = setVibration
                            )
                        )
                    }
                    navController.navigateUp()
                }
            ) {
                Icon(
                    imageVector = Icons.TwoTone.Done,
                    contentDescription = null
                )
            }
        },
        floatingActionButtonPosition = FabPosition.End
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            TimePicker(
                hour = selectedHour,
                minute = selectedMinute,
                onHourChange = { hour -> selectedHour = hour },
                onMinuteChange = { minute -> selectedMinute = minute }
            )
            DaysToRepeatAlarm(
                selectedDays = selectedDays
            ) { day ->
                selectedDays = if (selectedDays.contains(day)) {
                    selectedDays - day
                } else {
                    selectedDays + day
                }
            }
            AlarmSoundSetting(
                alarm = alarm,
                onVolumeChange = { volume -> setVolume = volume },
                onVibrationToggle = { vibration -> setVibration = vibration }
            )
        }
    }
}
