package com.example.myalarmapp.alarm.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SwipeToDismissBoxState
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myalarmapp.R
import com.example.myalarmapp.alarm.data.Alarm

@Composable
fun AlarmItem(
    alarm: Alarm,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    onAlarmToggle: (Boolean) -> Unit
) {
    val colorOnOff by rememberUpdatedState(
        newValue =
        if (alarm.isOn) MaterialTheme.colorScheme.onSecondaryContainer
        else MaterialTheme.colorScheme.onSecondaryContainer.copy(0.6f)
    )

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(60.dp)
            .background(MaterialTheme.colorScheme.secondaryContainer)
            .clickable { onClick() }
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AlarmTime(
            alarm = alarm,
            colorOnOff = colorOnOff,
            modifier = Modifier.weight(1f)
        )
        AlarmToggleButton(
            alarm = alarm,
            onAlarmToggle = onAlarmToggle,
            colorOnOff = colorOnOff
        )
    }
}

@Composable
fun AlarmTime(alarm: Alarm, colorOnOff: Color, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = alarm.hour.toString().padStart(2, '0'),
            color = colorOnOff,
            fontSize = 30.sp
        )
        Text(
            text = "  |  ",
            color = colorOnOff,
            fontSize = 26.sp
        )
        Text(
            text = alarm.minute.toString().padStart(2, '0'),
            color = colorOnOff,
            fontSize = 30.sp
        )
    }
}

@Composable
fun AlarmToggleButton(
    alarm: Alarm,
    colorOnOff: Color,
    modifier: Modifier = Modifier,
    onAlarmToggle: (Boolean) -> Unit
) {
    val iconOnOff by rememberUpdatedState(
        newValue =
        if (alarm.isOn) R.drawable.alarm_on_24px else R.drawable.alarm_off_24px
    )
    IconToggleButton(
        checked = alarm.isOn,
        modifier = modifier,
        onCheckedChange = { isChecked -> onAlarmToggle(isChecked) }
    ) {
        Icon(
            painter = painterResource(id = iconOnOff),
            contentDescription = null,
            modifier = Modifier.scale(1.3f),
            tint = colorOnOff
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DismissBackground(dismissState: SwipeToDismissBoxState) {
    val alphaState by rememberUpdatedState(
        newValue =
        if (dismissState.targetValue == SwipeToDismissBoxValue.EndToStart) 0.8f else 0.3f
    )
    val iconScaleState by animateFloatAsState(
        targetValue =
        if (dismissState.targetValue == SwipeToDismissBoxValue.EndToStart) 1.2f else 0f,
        label = ""
    )
    Row(
        modifier = Modifier
            .background(Color.Red.copy(alpha = alphaState))
            .fillMaxWidth()
            .height(60.dp)
            .padding(end = 24.dp),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.TwoTone.Delete,
            contentDescription = null,
            modifier = Modifier.scale(iconScaleState),
            tint = Color.White
        )
    }
}