package com.example.myalarmapp.alarm.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.twotone.MusicNote
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myalarmapp.R
import com.example.myalarmapp.alarm.data.Alarm


@Composable
fun AlarmSoundSetting(
    alarm: Alarm,
    onVolumeChange: (Float) -> Unit,
    onVibrationToggle: (Boolean) -> Unit
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
            value = alarm.volume,
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
                .height(48.dp)
                .background(MaterialTheme.colorScheme.secondaryContainer)
                .clickable { }
            ) {
                Icon(
                    imageVector = Icons.TwoTone.MusicNote,
                    contentDescription = null
                )
                Text(text = alarm.tune)
            }
            IconToggleButton(
                checked = alarm.vibrationOn,
                modifier = Modifier.padding(horizontal = 16.dp),
                onCheckedChange = { toggled -> onVibrationToggle(toggled) }
            ) {
                val vibrationIcon by rememberUpdatedState(newValue =
                    if (alarm.vibrationOn) R.drawable.twotone_vibration_on_24
                    else R.drawable.twotone_vibration_off_24
                )
                Icon(
                    painter = painterResource(id = vibrationIcon),
                    contentDescription = null,
                    modifier = Modifier.scale(1.3f)
                )
            }
        }
    }
}