package com.example.whatstheweather.screens.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBackIosNew
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.whatstheweather.utils.Gradients
import com.example.whatstheweather.widgets.WeatherAppTopBar

@Composable
fun SettingsScreen(navController: NavController) {
    Scaffold(
        topBar = {
            WeatherAppTopBar(
                title = "Settings",
                icon = Icons.Rounded.ArrowBackIosNew,
                isMainScreen = false,
                navController = navController
            ) {
                navController.popBackStack()
            }
        }
    ) {
        Surface(
            modifier = Modifier.fillMaxSize()

        ) {
            Column(
                modifier = Modifier.background(Gradients.SEARCH_SCREEN_GRADIENT),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "SettingsScreen")
            }
        }
    }
}