package com.example.myalarmapp.common.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.twotone.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun BackIconButton(navController: NavController, modifier: Modifier = Modifier) {
    IconButton(
        modifier = modifier,
        onClick = { navController.navigateUp() }
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.TwoTone.ArrowBack,
            contentDescription = null
        )
    }
}