package com.example.myalarmapp.utils

fun formatTime(num: Int): String =
    if (num < 10) "0$num" else "$num"