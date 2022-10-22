package com.example.whatstheweather.model

data class Current(
    val cloud: Int,
    val condition: Condition,
    val feelslike_c: Double,
    val gust_kph: Double,
    val humidity: Int,
    val is_day: Int,
    val precip_mm: Double,
    val pressure_mb: Double,
    val temp_c: Double,
    val uv: Double,
    val vis_km: Double,
    val wind_dir: String,
    val wind_kph: Double
)