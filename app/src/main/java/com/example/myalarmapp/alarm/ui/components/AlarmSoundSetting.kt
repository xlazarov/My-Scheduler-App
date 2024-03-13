package com.example.myalarmapp.alarm.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myalarmapp.R


@Composable
fun AlarmSoundSetting(
    tune: String,
    volume: Float,
    onVolumeChange: (Float) -> Unit,
    vibrationOn: Boolean,
    onClickToggle: (Boolean) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Volume",
            fontSize = 18.sp,
            color = MaterialTheme.colorScheme.secondary
        )
        Slider(
            value = volume,
            onValueChange = onVolumeChange
        )
        Text(
            text = "Sound",
            fontSize = 18.sp,
            color = MaterialTheme.colorScheme.secondary
        )
        Row {
            Row(modifier = Modifier
                .weight(1f)
                .padding(end = 16.dp)
                .background(MaterialTheme.colorScheme.secondaryContainer)
                .clickable { }
            ) {
                Icon(
                    imageVector = Icons.Default.PlayArrow,
                    contentDescription = null
                )
                Text(text = tune)
            }
            IconToggleButton(
                checked = vibrationOn,
                onCheckedChange = { toggled -> onClickToggle(toggled) }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.twotone_vibration_24),
                    contentDescription = null
                )
            }
        }
    }
}