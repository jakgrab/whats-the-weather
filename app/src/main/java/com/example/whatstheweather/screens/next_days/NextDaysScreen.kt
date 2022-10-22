package com.example.whatstheweather.screens.next_days

import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBackIosNew
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.whatstheweather.data.DataOrException
import com.example.whatstheweather.model.Forecastday
import com.example.whatstheweather.model.Weather
import com.example.whatstheweather.screens.main.MainViewModel
import com.example.whatstheweather.utils.*
import com.example.whatstheweather.widgets.HourWeatherItem
import com.example.whatstheweather.widgets.NextDayWeatherInfo
import com.example.whatstheweather.widgets.WeatherAppTopBar

@Composable
fun NextDaysScreen(navController: NavController, mainViewModel: MainViewModel, city: String?) {

    val weatherData = produceState<DataOrException<Weather, Boolean, Exception>>(
        initialValue = DataOrException(loading = true)
    ) {
        value = mainViewModel.getWeatherData(city!!)
    }.value

    if (weatherData.loading == true)
        CircularProgressIndicator()
    else if (weatherData.data != null) {

        NextDaysScaffold(data = weatherData.data!!, navController)

    }
}

@Composable
fun NextDaysScaffold(data: Weather, navController: NavController) {

    val daysList = data.forecast.forecastday
    val timestamp = data.location.localtime_epoch
    val offset = remember {
        mutableStateOf(0f)
    }
    val scrollState = rememberScrollState()
    LaunchedEffect(Unit) {
        scrollState.animateScrollTo(100)
    }

    Scaffold(

//            .scrollable(
//            orientation = Orientation.Vertical,
//            state = rememberScrollableState { delta ->
//                offset.value = offset.value + delta
//                delta
//            },
        // enabled = true
        //),
        topBar = {
            WeatherAppTopBar(
                title = data.location.name,
                icon = Icons.Rounded.ArrowBackIosNew,
                isMainScreen = false,
                navController = navController
            ) {
                navController.popBackStack()
            }
        }
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(
                    state = scrollState
                ),
            ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                verticalArrangement = Arrangement.SpaceAround,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(Modifier.height(50.dp))
                DayWeather(day = daysList[1], isTomorrow = true)
                Spacer(Modifier.height(100.dp))
                DayWeather(day = daysList[2], timestamp = timestamp)

            }
        }
    }
}

@Composable
fun DayWeather(day: Forecastday, isTomorrow: Boolean = false, timestamp: Int = 0) {

    val showHoursState = remember {
        mutableStateOf(false)
    }
    val cardHeight = if (showHoursState.value) 450.dp else 300.dp
    val dayOfWeekString = if (isTomorrow) "Tomorrow" else formatDayOfTheWeek(timestamp)

    val minMaxTemp =
        formatTemperature(day.day.maxtemp_c) + "°/" + formatTemperature(day.day.mintemp_c) + "°"

    Card(
        modifier = Modifier
            .height(cardHeight)
            .width(350.dp)
            .clickable {
                showHoursState.value = !showHoursState.value
            },
        shape = RoundedCornerShape(25.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Gradients.BACKGROUND_GRADIENT)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                verticalAlignment = Alignment.Top
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(0.5f),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Text(
                            text = dayOfWeekString,
                            modifier = Modifier.textBrush(Gradients.SEARCH_SCREEN_GRADIENT),
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 26.sp
                        )
                    }
                    Row(
                        modifier = Modifier
                            .width(140.dp)
                            .height(90.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = minMaxTemp,
                            color = Color.White.copy(alpha = 0.8f),
                            fontSize = 35.sp
                        )
                    }
                }
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = getCurrentWeatherIcon(day)),
                        contentDescription = "Day weather icon",
                        modifier = Modifier.size(150.dp),
                        contentScale = ContentScale.Fit
                    )
                }
            }
            NextDayWeatherInfo(
                windSpeed = day.day.maxwind_kph,
                humidity = day.day.avghumidity,
                rainChance = day.day.daily_chance_of_rain
            )
            if (showHoursState.value)
                NextDayHours(day = day)

        }
    }
}


@Composable
fun NextDayHours(day: Forecastday) {
    LazyRow(
        contentPadding = PaddingValues(horizontal = 15.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        val hoursList = day.hour
        items(items = hoursList) { hour ->
            HourWeatherItem(hour)
        }
    }
}


