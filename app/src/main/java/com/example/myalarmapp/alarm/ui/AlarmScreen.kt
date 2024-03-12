package com.example.myalarmapp.alarm.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.myalarmapp.Screen
import com.example.myalarmapp.R
import com.example.myalarmapp.alarm.data.AlarmViewModel
import com.example.myalarmapp.alarm.ui.components.AlarmItem
import com.example.myalarmapp.alarm.ui.components.DismissBackground
import com.example.myalarmapp.alarm.ui.components.NavigationBottomBar


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlarmScreen(
    viewModel: AlarmViewModel = hiltViewModel(),
    navController: NavController,
) {
    val alarms by viewModel.alarms.collectAsState()

    Scaffold(
        bottomBar = { NavigationBottomBar() },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate(Screen.SetAlarm.route) },
                modifier = Modifier.clip(CircleShape)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.alarm_add_24px),
                    contentDescription = null
                )
            }
        },
        floatingActionButtonPosition = FabPosition.End
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(it)
        ) {
            items(
                items = alarms,
                key = { item -> item.id }
            ) { alarm ->
                val state = rememberSwipeToDismissBoxState(
                    confirmValueChange = { value ->
                        if (value == SwipeToDismissBoxValue.EndToStart) {
                            viewModel.deleteAlarm(alarm)
                            true
                        } else false
                    },
                    positionalThreshold = { totalDistance -> totalDistance * 0.4f }
                )
                SwipeToDismissBox(
                    state = state,
                    backgroundContent = { DismissBackground(state) },
                    enableDismissFromEndToStart = true
                ) {
                    AlarmItem(
                        alarm = alarm,
                        onClick = {
                            navController.navigate(
                                Screen.SetAlarm.route.replace(
                                    "{alarmId}",
                                    alarm.id.toString()
                                )
                            )
                        }
                    ) { isChecked ->
                        viewModel.updateAlarm(alarm.copy(isOn = isChecked))
                    }
                }
            }
        }
    }
}

