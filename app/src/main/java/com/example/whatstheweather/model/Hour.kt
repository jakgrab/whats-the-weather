package com.example.whatstheweather.model

data class Hour(
    val chance_of_rain: Int,
    val chance_of_snow: Int,
    val cloud: Int,
    val condition: Condition,
    val feelslike_c: Double,
    val humidity: Int,
    val is_day: Int,
    val precip_mm: Double,
    val pressure_mb: Double,
    val temp_c: Double,
    val time: String,
    val wind_kph: Double
)