package com.example.whatstheweather.screens


import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.whatstheweather.R
import com.example.whatstheweather.navigation.WeatherScreens
import com.example.whatstheweather.screens.favourites.FavouritesViewModel
import com.example.whatstheweather.utils.Gradients
import kotlinx.coroutines.delay

@Composable
fun WeatherSplashScreen(
    navController: NavController, favouritesViewModel: FavouritesViewModel = hiltViewModel()
) {
    val scale = remember {
        Animatable(initialValue = 0f)
    }

    val defaultCity =
        if (favouritesViewModel.faveList.collectAsState().value.isNotEmpty())
            favouritesViewModel.faveList.collectAsState().value.last().city
        else "London"

    LaunchedEffect(key1 = true, block = {

        scale.animateTo(targetValue = 0.9f, animationSpec = tween(durationMillis = 800, easing = {
            OvershootInterpolator(7f).getInterpolation(it)
        }))

        delay(2000L)

        navController.navigate(route = WeatherScreens.MainScreen.name + "/$defaultCity")
    })

    Box(
        modifier = Modifier
            .size(250.dp)
            .scale(scale.value)
            .clip(CircleShape),
    ) {
        Row(
            modifier = Modifier
                .background(Gradients.BACKGROUND_GRADIENT)
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.storm),
                contentDescription = "Splash screen icon",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .padding(12.dp)
                    .size(150.dp)
            )
        }

    }
}