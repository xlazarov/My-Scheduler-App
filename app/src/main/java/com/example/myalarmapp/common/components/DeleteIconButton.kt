package com.example.myalarmapp.common.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.tooling.preview.Preview
import com.example.myalarmapp.ui.theme.MyAlarmAppTheme

@Composable
fun DeleteIconButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    IconButton(
        modifier = modifier,
        onClick = onClick
    ) {
        Icon(
            imageVector = Icons.TwoTone.Delete,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.error,
            modifier = Modifier.scale(1.3f)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DeleteIconButtonPreview() {
    MyAlarmAppTheme {
        DeleteIconButton {}
    }
}