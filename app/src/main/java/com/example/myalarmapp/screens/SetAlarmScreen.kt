package com.example.myalarmapp.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.twotone.ArrowBack
import androidx.compose.material.icons.twotone.Done
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.myalarmapp.data.Alarm
import com.example.myalarmapp.data.AlarmViewModel
import com.example.myalarmapp.presentation.components.DaysToRepeatAlarm
import com.example.myalarmapp.presentation.components.TimePicker

@Composable
fun getIdFromBundle(navController: NavController, key: String = "alarmId"): Long {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val arguments = navBackStackEntry?.arguments
    return arguments?.getString(key)?.toLongOrNull() ?: 0L
}

@Composable
fun SetAlarmScreen(
    viewModel: AlarmViewModel = hiltViewModel(),
    navController: NavController
) {
    val alarmId = getIdFromBundle(navController)
    val alarm = viewModel.alarms.value.find { it.id == alarmId } ?: Alarm()

    var selectedHour by remember { mutableIntStateOf(alarm.hour) }
    var selectedMinute by remember { mutableIntStateOf(alarm.minute) }
    var selectedDays by remember { mutableStateOf(alarm.daysOfWeek) }

    Scaffold(
        topBar = {
            Row {
                IconButton(onClick = { navController.navigateUp() }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.TwoTone.ArrowBack,
                        contentDescription = null
                    )
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier.clip(CircleShape),
                onClick = {
                    if (alarmId > 0) {
                        viewModel.updateAlarm(
                            alarm.copy(
                                hour = selectedHour,
                                minute = selectedMinute,
                                daysOfWeek = selectedDays
                            )
                        )
                    } else {
                        viewModel.insertAlarm(
                            Alarm(
                                hour = selectedHour,
                                minute = selectedMinute,
                                daysOfWeek = selectedDays
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
            verticalArrangement = Arrangement.SpaceEvenly
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
        }
    }
}
