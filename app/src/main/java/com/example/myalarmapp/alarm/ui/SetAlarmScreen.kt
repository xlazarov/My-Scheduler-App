package com.example.myalarmapp.alarm.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.twotone.ArrowBack
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.twotone.Delete
import androidx.compose.material.icons.twotone.Done
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.myalarmapp.alarm.data.Alarm
import com.example.myalarmapp.alarm.data.AlarmViewModel
import com.example.myalarmapp.alarm.ui.components.DaysToRepeatAlarm
import com.example.myalarmapp.alarm.ui.components.TimePicker


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
            Row {
                IconButton(onClick = { navController.navigateUp() }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.TwoTone.ArrowBack,
                        contentDescription = null
                    )
                }
                Text(text = alarm.title, modifier = Modifier.weight(1f))
                IconButton(onClick = {
                    if (!isNew) viewModel.deleteAlarm(alarm)
                    navController.navigateUp()
                }) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(text = "Delete", color = MaterialTheme.colorScheme.error)
                        Icon(
                            imageVector = Icons.TwoTone.Delete,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.error
                        )
                    }
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier.clip(CircleShape),
                onClick = {
                    if (!isNew) {
                        viewModel.updateAlarm(
                            alarm.copy(
                                hour = selectedHour,
                                minute = selectedMinute,
                                daysOfWeek = selectedDays,
                                volume = setVolume,
                                vibrationOn = setVibration
                            )
                        )
                    } else {
                        viewModel.insertAlarm(
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
            verticalArrangement = Arrangement.SpaceBetween
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
            AlarmSound(
                tune = alarm.tune,
                volume = setVolume,
                onVolumeChange = { volume -> setVolume = volume },
                vibrationOn = setVibration,
                onClickToggle = { vibration -> setVibration = vibration }
            )
        }
    }
}

@Composable
fun AlarmSound(
    tune: String,
    volume: Float,
    onVolumeChange: (Float) -> Unit,
    vibrationOn: Boolean,
    onClickToggle: (Boolean) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Volume", fontSize = 18.sp, color = MaterialTheme.colorScheme.secondary)
        Slider(value = volume, onValueChange = onVolumeChange)
        Text(text = "Sound", fontSize = 18.sp, color = MaterialTheme.colorScheme.secondary)
        Row {
            Row(modifier = Modifier
                .weight(1f)
                .padding(end = 16.dp)
                .background(MaterialTheme.colorScheme.secondaryContainer)
                .clickable { }) {
                Icon(imageVector = Icons.Default.PlayArrow, contentDescription = null)
                Text(text = tune)
            }
            IconToggleButton(
                checked = vibrationOn,
                onCheckedChange = { toggled -> onClickToggle(toggled) }) {
            }
        }
    }
}