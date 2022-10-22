package com.example.whatstheweather.utils

import com.example.whatstheweather.model.Hour
import com.example.whatstheweather.model.Weather

fun formatHoursList(data: Weather): List<Hour> {
    val hours = data.forecast.forecastday[0].hour
    val hoursNextDay = data.forecast.forecastday[1].hour

    val localTime = data.location.localtime.split(" ")
    val hourString = localTime[1].split(":")
    val currentHour = hourString[0].toInt()

    val newHours = hours.filter { hour ->
        hourToInt(hour) >= currentHour
    }

    val newHoursNextDay = hoursNextDay.filter { hour ->
        hourToInt(hour) < currentHour
    }
    return newHours + newHoursNextDay
}

fun hourToInt(hour: Hour): Int {
    val stringSplitter = hour.time.split(" ")
    val hourSplitter = stringSplitter[1].split(":")
    return hourSplitter[0].toInt()
}