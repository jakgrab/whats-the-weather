package com.example.whatstheweather.screens.main

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.whatstheweather.data.DataOrException
import com.example.whatstheweather.model.Weather
import com.example.whatstheweather.navigation.WeatherScreens
import com.example.whatstheweather.widgets.*
import kotlinx.coroutines.launch


@Composable
fun MainScreen(navController: NavController, mainViewModel: MainViewModel, city: String?) {

    val weatherData = produceState<DataOrException<Weather, Boolean, Exception>>(
        initialValue = DataOrException(loading = true)
    ) {
        value = mainViewModel.getWeatherData(city!!)
    }.value
    Log.d("ERR", weatherData.exception.toString())
    if (weatherData.loading == true)
        CircularProgressIndicator()
    else if (weatherData.data != null) {

        MainScaffold(weather = weatherData.data!!, navController)

    }
}

@Composable
fun MainScaffold(weather: Weather, navController: NavController) {

    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            WeatherAppTopBar(
                title = weather.location.name,
                icon = Icons.Rounded.Menu,
                navController = navController,
                onSearchClicked = {
                    navController.navigate(WeatherScreens.SearchScreen.name)
                },
                onButtonClicked = {
                    scope.launch {
                        scaffoldState.drawerState.open()
                    }
                    Log.d("DR", "Drawer clicked")
                }
            )
        },
        drawerContent = {
            Drawer(
                onFavouritesClicked = {
                    navController.navigate(WeatherScreens.FavouritesScreen.name)
                },
                onAboutClicked = {
                    navController.navigate(WeatherScreens.AboutScreen.name)
                },
                onSettingsClicked = {
                    navController.navigate(WeatherScreens.SettingsScreen.name)
                }
            )
        },
        drawerGesturesEnabled = true,
        drawerShape = RoundedCornerShape(topEnd = 25.dp, bottomEnd = 25.dp),
        drawerElevation = 20.dp,
    ) {
        MainContent(data = weather, navController = navController)
    }
}

@OptIn(ExperimentalTextApi::class)
@Composable
fun MainContent(data: Weather, navController: NavController) {
    Column(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {//data = data)
        MainInfoCard(data) {
            BasicWeatherInfo(data)
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(30.dp)
                .padding(horizontal = 15.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Today",
                fontSize = 24.sp
            )
            Row(modifier = Modifier.clickable {
                navController.navigate(WeatherScreens.NextDaysScreen.name + "/${data.location.name}")
            }) {
                Text(
                    text = "Next Days",
                    fontSize = 24.sp
                )
                Icon(
                    imageVector = Icons.Rounded.NavigateNext,
                    contentDescription = "Next days icon"
                )
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        HourWeather(data = data)
    }
}
