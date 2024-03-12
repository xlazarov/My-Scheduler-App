package com.example.myalarmapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.myalarmapp.data.AlarmViewModel

@Composable
fun WelcomeScreen(
    viewModel: AlarmViewModel = viewModel(),
    navController: NavController
) {
    Box(modifier = Modifier.fillMaxSize().background(color = MaterialTheme.colors.secondary)){
        TODO("Lottie animation - Figma")
    }
}