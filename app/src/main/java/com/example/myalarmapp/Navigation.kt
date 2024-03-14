package com.example.myalarmapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myalarmapp.alarm.data.Alarm
import com.example.myalarmapp.alarm.ui.AlarmViewModel
import com.example.myalarmapp.alarm.ui.AlarmScreen
import com.example.myalarmapp.alarm.ui.SetAlarmScreen
import com.example.myalarmapp.utils.getIdFromBundle
import kotlinx.coroutines.delay

/**
 * enum values that represent the screens in the app
 */
enum class Screen(val route: String) {
    Welcome("welcome"),
    Home("home"),
    Alarm("alarm"),
    SetAlarm("setAlarm/{alarmId}?"),
    Notes("notes"),
    Calendar("calendar"),
    Reminder("reminder")
}

@Composable
fun Navigation(
    navController: NavHostController = rememberNavController(),
    alarmViewModel: AlarmViewModel = hiltViewModel()
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Alarm.route
    ) {
        composable(route = Screen.Welcome.route) {
            //WelcomeScreen(navController = navController)
            LaunchedEffect(key1 = Unit) {
                delay(2000)
                navController.navigate(Screen.Home.route)
            }
        }
        composable(route = Screen.Home.route) {

        }
        composable(route = Screen.Alarm.route) {
            AlarmScreen(navController = navController)
        }
        composable(route = Screen.SetAlarm.route) {
            val alarmId = getIdFromBundle(navController)
            val alarm = alarmViewModel.getItemById(alarmId)

            if (alarm != null)
                SetAlarmScreen(alarm = alarm, isNew = false, navController = navController)
            else
                SetAlarmScreen(alarm = Alarm(), navController = navController)
        }
        composable(route = Screen.Notes.route) {

        }
        composable(route = Screen.Reminder.route) {

        }
    }
}