package com.example.whatstheweather.widgets

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.whatstheweather.model.Hour
import com.example.whatstheweather.model.Weather
import com.example.whatstheweather.utils.*

@Composable
fun HourWeather(data: Weather) {
    LazyRow(
        contentPadding = PaddingValues(horizontal = 15.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        val hoursList = formatHoursList(data)

        items(items = hoursList) { hour ->
            HourWeatherItem(hour)
        }
    }
}

@Composable
fun HourWeatherItem(hour: Hour) {
    Card(
        modifier = Modifier
            .size(
                width = 80.dp,
                height = 120.dp
            ),
        shape = RoundedCornerShape(10.dp),
        backgroundColor = Color(0xFFFFF4FA),
        elevation = 5.dp
    ) {
        Column(
            modifier = Modifier,
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "${formatTemperature(hour.temp_c)}°"
            )
            Image(
                bitmap = ImageBitmap.imageResource(id = getCurrentWeatherIcon(hour)),
                contentDescription = "Weather icon",
                modifier = Modifier.size(40.dp),
                contentScale = ContentScale.Fit
            )
            Text(
                text = formatTime(hour.time),
                fontSize = 15.sp,
                color = Color.LightGray
            )
        }

    }
}
