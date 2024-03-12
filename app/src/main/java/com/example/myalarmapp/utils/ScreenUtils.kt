package com.example.myalarmapp.utils

import android.util.DisplayMetrics
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class ScreenDimensions(val width: Dp, val height: Dp)

fun screenDimensionDp(): ScreenDimensions {
    val displayMetrics = DisplayMetrics()
    val width = displayMetrics.widthPixels / displayMetrics.density
    val height = displayMetrics.heightPixels / displayMetrics.density
    return ScreenDimensions(width.dp, height.dp)
}