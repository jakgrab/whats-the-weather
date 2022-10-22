package com.example.whatstheweather.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Air
import androidx.compose.material.icons.rounded.Opacity
import androidx.compose.material.icons.rounded.Umbrella
import androidx.compose.material.icons.rounded.Waves
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.example.whatstheweather.R
import com.example.whatstheweather.utils.formatPressure
import com.example.whatstheweather.utils.formatRainAndHumidity
import com.example.whatstheweather.utils.formatWindSpeed

@Composable
fun NextDayWeatherInfo(windSpeed: Double, humidity: Int, rainChance: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.Transparent),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        WeatherCondition(
            icon = Icons.Rounded.Air,
            text = formatWindSpeed(windSpeed),
            stringResource = stringResource(R.string.wind_speed)
        )
        WeatherCondition(
            icon = Icons.Rounded.Opacity,
            text = formatRainAndHumidity(humidity),
            stringResource = stringResource(R.string.humidity)
        )
        WeatherCondition(
            icon = Icons.Rounded.Umbrella,
            text = formatRainAndHumidity(rainChance),
            stringResource = stringResource(R.string.rain)
        )
    }
}