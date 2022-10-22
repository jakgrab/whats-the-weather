package com.example.whatstheweather.widgets

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Air
import androidx.compose.material.icons.rounded.Opacity
import androidx.compose.material.icons.rounded.Waves
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.whatstheweather.R
import com.example.whatstheweather.model.Weather
import com.example.whatstheweather.utils.formatPressure
import com.example.whatstheweather.utils.formatRainAndHumidity
import com.example.whatstheweather.utils.formatWindSpeed


@Composable
fun BasicWeatherInfo(data: Weather) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.padding(10.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = Icons.Rounded.Air,
                contentDescription = "Wind icon",
                modifier = Modifier.scale(1.5f),
                tint = Color(0xff6c3ffe)
            )
            //
            Spacer(modifier = Modifier.height(15.dp))
            Text(text = formatWindSpeed(data.current.wind_kph), color = Color.Black, fontSize = 20.sp)
            Text(
                text = stringResource(R.string.wind_speed),
                color = Color(0xffb3b3b3),
                fontSize = 15.sp
            )
        }
        Column(
            modifier = Modifier.padding(10.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = Icons.Rounded.Waves,
                contentDescription = "Pressure icon",
                modifier = Modifier.scale(1.5f),
                tint = Color(0xff6c3ffe)
            )
            Spacer(modifier = Modifier.height(15.dp))
            //
            Text(text = formatPressure(data.current.pressure_mb), color = Color.Black, fontSize = 20.sp)
            Text(
                text = stringResource(R.string.pressure),
                color = Color(0xffb3b3b3),
                fontSize = 15.sp
            )
        }
        Column(
            modifier = Modifier.padding(10.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = Icons.Rounded.Opacity,
                contentDescription = "Humidity icon",
                modifier = Modifier.scale(1.5f),
                tint = Color(0xff6c3ffe)
            )
            Spacer(modifier = Modifier.height(15.dp))

            //
            Text(text = formatRainAndHumidity(data.current.humidity), color = Color.Black, fontSize = 20.sp)
            Text(
                text = stringResource(R.string.humidity),
                color = Color(0xffb3b3b3),
                fontSize = 15.sp
            )
        }
    }
}