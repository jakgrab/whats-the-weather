package com.example.whatstheweather.utils

import java.text.SimpleDateFormat
import java.util.*

fun formatDate(timestamp: Int): String {
    val sdf = SimpleDateFormat("EEEE, d MMM", Locale.ENGLISH)
    val date = Date(timestamp.toLong() * 1000)

    return sdf.format(date)
}

fun formatDayOfTheWeek(timestamp: Int): String {
    val secondsInDay = 86400
    val dayAfterTomorrow = timestamp + 2 * secondsInDay
    val formatter = SimpleDateFormat("EEEE", Locale.ENGLISH)
    val date = Date(dayAfterTomorrow.toLong() * 1000)

    return formatter.format(date)
}

fun formatTime(time: String): String {
    val split = time.split(" ")
    return split[1]
}

fun formatDecimals(item: Double): String {
    return " %.0f".format(item)
}

fun formatTemperature(temp: Double): String {
    return temp.toInt().toString()
}

fun formatWindSpeed(wind: Double): String {
    return wind.toInt().toString() + "km/h"
}

fun formatPressure(pressure: Double): String {
    return pressure.toInt().toString() + "hPa"
}

fun formatRainAndHumidity(percent: Int) : String {
    return "$percent%"
}