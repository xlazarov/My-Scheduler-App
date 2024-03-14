package com.example.myalarmapp.common.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.twotone.StickyNote2
import androidx.compose.material.icons.twotone.Alarm
import androidx.compose.material.icons.twotone.CalendarMonth
import androidx.compose.material.icons.twotone.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myalarmapp.Screen

@Composable
fun NavigationBottomBar(navController: NavController, modifier: Modifier = Modifier) {
    var currentScreen by remember { mutableStateOf(Screen.Alarm.route) }

    NavigationBar(
        modifier = modifier,
        tonalElevation = 4.dp,
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        contentColor = MaterialTheme.colorScheme.onPrimaryContainer
    ) {

        NavigationBarItem(
            selected = currentScreen == Screen.Calendar.route,
            onClick = {
                navController.navigate(Screen.Calendar.route)
                currentScreen = Screen.Calendar.route
            },
            icon = {
                Icon(
                    imageVector = Icons.TwoTone.CalendarMonth,
                    contentDescription = null
                )
            }
        )
        NavigationBarItem(
            selected = currentScreen == Screen.Reminder.route,
            onClick = {
                navController.navigate(Screen.Reminder.route)
                currentScreen = Screen.Reminder.route
            },
            icon = {
                Icon(
                    imageVector = Icons.TwoTone.Notifications,
                    contentDescription = null
                )
            }
        )
        NavigationBarItem(
            selected = currentScreen == Screen.Alarm.route,
            onClick = {
                navController.navigate(Screen.Alarm.route)
                currentScreen = Screen.Alarm.route
            },
            icon = {
                Icon(
                    imageVector = Icons.TwoTone.Alarm,
                    contentDescription = null
                )
            }
        )
        NavigationBarItem(
            selected = currentScreen == Screen.Notes.route,
            onClick = {
                navController.navigate(Screen.Notes.route)
                currentScreen = Screen.Notes.route
            },
            icon = {
                Icon(
                    imageVector = Icons.AutoMirrored.TwoTone.StickyNote2,
                    contentDescription = null
                )
            }
        )
    }
}