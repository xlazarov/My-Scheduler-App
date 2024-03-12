package com.example.myalarmapp.presentation.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.DayOfWeek


@Composable
fun DaysToRepeatAlarm(
    selectedDays: List<String>,
    onDaySelected: (String) -> Unit
) {
    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        Text(text = "Repeat", fontSize = 24.sp, color = MaterialTheme.colorScheme.secondary)
    }
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        for (day in DayOfWeek.entries) {
            val textColor by rememberUpdatedState(
                newValue = if (selectedDays.contains(day.name)) MaterialTheme.colorScheme.onSecondaryContainer
                else Color.LightGray
            )
            val borderColor by rememberUpdatedState(
                newValue = if (selectedDays.contains(day.name)) MaterialTheme.colorScheme.onSecondaryContainer
                else Color.Transparent
            )
            Box(
                modifier = Modifier
                    .size(30.dp)
                    .clip(CircleShape)
                    .border(1.dp, borderColor, CircleShape)
                    .clickable { onDaySelected(day.name) },
                contentAlignment = Alignment.Center
            ) {
                Text(text = "${day.name.first()}", color = textColor, fontSize = 20.sp)
            }
        }
    }
}