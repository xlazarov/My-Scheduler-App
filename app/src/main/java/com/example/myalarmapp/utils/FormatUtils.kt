package com.example.myalarmapp.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

fun formatTime(num: Int): String =
    if (num < 10) "0$num" else "$num"

@Composable
fun getIdFromBundle(navController: NavController, key: String = "alarmId"): Long {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val arguments = navBackStackEntry?.arguments
    return arguments?.getString(key)?.toLongOrNull() ?: 0L
}