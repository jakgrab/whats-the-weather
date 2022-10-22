package com.example.whatstheweather.network

import com.example.whatstheweather.BuildConfig
import com.example.whatstheweather.model.Weather
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface WeatherAPI {
    @GET(value = "forecast.json")
    suspend fun getWeather(
        @Query("key") key: String = BuildConfig.API_KEY,
        @Query("q") query: String,
        @Query("days") days: String = "3",
        @Query("aqi") aqi: String = "no",
        @Query("alerts") alerts: String = "no"
    ): Weather

}