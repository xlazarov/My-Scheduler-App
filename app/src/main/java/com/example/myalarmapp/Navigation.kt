package com.example.myalarmapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myalarmapp.screens.AlarmScreen
import com.example.myalarmapp.screens.SetAlarmScreen
import com.example.myalarmapp.screens.WelcomeScreen
import kotlinx.coroutines.delay

/**
 * enum values that represent the screens in the app
 */
enum class Screens(val route: String) {
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
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Screens.Alarm.route
    ) {
        composable(route = Screens.Welcome.route) {
            WelcomeScreen(navController = navController)
            LaunchedEffect(key1 = Unit) {
                delay(2000)
                navController.navigate(Screens.Home.route)
            }
        }
        composable(route = Screens.Home.route) {

        }
        composable(route = Screens.Alarm.route) {
            AlarmScreen(navController = navController)
        }
        composable(route = Screens.SetAlarm.route) {
            SetAlarmScreen(navController = navController)
        }
        composable(route = Screens.Notes.route) {

        }
        composable(route = Screens.Reminder.route) {

        }
    }
}