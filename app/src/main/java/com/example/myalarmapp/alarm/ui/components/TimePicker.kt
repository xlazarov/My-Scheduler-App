package com.example.myalarmapp.alarm.ui.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myalarmapp.utils.formatTime

@Composable
fun TimePicker(
    hour: Int,
    minute: Int,
    onHourChange: (Int) -> Unit,
    onMinuteChange: (Int) -> Unit
) {
    val hours = (0..23).toList()
    val minutes = (0..59).toList()
    Log.i("TimePicker", "first visible h: $hour, m: $minute")

    Row(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.secondaryContainer)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            NumberPicker(
                numbers = hours,
                firstVisibleNumber = hour,
                onValueChange = onHourChange
            )
            Text(
                text = "  |  ",
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                fontSize = 26.sp
            )
            NumberPicker(
                numbers = minutes,
                firstVisibleNumber = minute,
                onValueChange = onMinuteChange
            )
        }
    }
}

@Composable
fun NumberPicker(
    firstVisibleNumber: Int,
    numbers: List<Int>,
    onValueChange: (Int) -> Unit
) {
    Log.i("NumberPicker", "first visible: $firstVisibleNumber")

    val fadingEdgeGradient = Brush.verticalGradient(
        1f to MaterialTheme.colorScheme.secondaryContainer,
        1f to Color.Transparent,
        1f to Color.Transparent,
        1f to MaterialTheme.colorScheme.secondaryContainer
    )
    val size = 100.dp
    val listState =
        rememberLazyListState(initialFirstVisibleItemIndex = firstVisibleNumber + numbers.size * 2)

    LazyColumn(
        state = listState,
        modifier = Modifier
            .height(size)
            .drawWithContent {
                drawContent()
                drawRect(brush = fadingEdgeGradient, blendMode = BlendMode.DstIn)
            },
        contentPadding = PaddingValues(size / 4),
        flingBehavior = rememberSnapFlingBehavior(lazyListState = listState),

        ) {
        items(numbers.size * 5) { index ->
            val number = numbers[index % numbers.size]
            Box(
                modifier = Modifier.size(size / 2),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = formatTime(number),
                    fontSize = TextUnit(size.value / 3, TextUnitType.Sp),
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.onSecondaryContainer
                )
            }
            LaunchedEffect(listState) {
                val snappedNumberIndex = listState.layoutInfo.visibleItemsInfo[0].index + 1
                onValueChange(numbers[snappedNumberIndex % numbers.size])
            }
        }
    }
}
