package com.example.whatstheweather.utils

import com.example.whatstheweather.R
import com.example.whatstheweather.model.Forecastday
import com.example.whatstheweather.model.Hour
import com.example.whatstheweather.model.Weather

fun getCurrentWeatherIcon(data: Any): Int {

    val code = when (data) {
        is Weather -> data.current.condition.code
        is Hour -> data.condition.code
        is Forecastday -> data.day.condition.code
        else -> null
    }
    val isDay = when (data) {
        is Weather -> data.current.is_day
        is Hour -> data.is_day
        is Forecastday -> 1
        else -> null
    }

    return if (isDay == 1) {
        when (code) {
            1000 -> R.drawable.sunny
            1003 -> R.drawable.partly_cloudy
            1117, 1237, 1261, 1264 -> R.drawable.blizzard
            1006, 1009 -> R.drawable.cloudy
            1030, 1135, 1147 -> R.drawable.fog
            1072, 1183, 1189, 1198 -> R.drawable.freezing_drizzle
            1282 -> R.drawable.heavy_snow_thunder
            1063, 1150, 1153, 1168, 1180, 1186, 1192, 1240 -> R.drawable.patchy_rain
            1066, 1210, 1216 -> R.drawable.patchy_snow
            1069, 1204, 1207, 1249, 1252 -> R.drawable.patchy_sleet
            1087, 1273 -> R.drawable.thundery_outbreaks
            1276 -> R.drawable.thunder
            1171, 1195, 1201, 1243 -> R.drawable.rain
            1114, 1213, 1219, 1222, 1225, 1255, 1258 -> R.drawable.snow
            1246 -> R.drawable.storm
            1279 -> R.drawable.snow_thunder
            else -> R.drawable.test
        }
    } else {
        when (code) {
            1000 -> R.drawable.clear_night
            1003 -> R.drawable.partly_cloudy_night
            1117, 1237, 1261, 1264 -> R.drawable.blizzard
            1006, 1009 -> R.drawable.cloudy_night
            1135, 1147 -> R.drawable.fog_night
            1030 -> R.drawable.mist_night
            1072, 1198 -> R.drawable.patchy_freezing_drizzle_night
            1279, 1282 -> R.drawable.snow_thunder
            1063, 1150, 1153, 1180, 1183, 1186, 1240 -> R.drawable.patchy_rain_night
            1168, 1171 -> R.drawable.freezing_drizzle
            1192, 1189 -> R.drawable.rain_night
            1066, 1210 -> R.drawable.patchy_snow_night
            1069, 1204, 1207, 1249, 1252 -> R.drawable.sleet_night
            1087, 1273 -> R.drawable.thundery_outbreaks_night
            1195, 1201, 1243 -> R.drawable.rain
            1114, 1213, 1216, 1255 -> R.drawable.snow_night
            1219, 1222, 1225, 1258 -> R.drawable.snow
            1246, 1276 -> R.drawable.storm_night
            else -> R.drawable.test
        }
    }
}
