package com.example.whatstheweather.repository

import android.util.Log
import com.example.whatstheweather.data.DataOrException
import com.example.whatstheweather.model.Weather
import com.example.whatstheweather.network.WeatherAPI
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val api: WeatherAPI) {

    suspend fun getWeather(cityQuery: String)
    : DataOrException<Weather, Boolean, Exception>  {
        val response = try {
            api.getWeather(query = cityQuery)

        } catch (e: Exception) {
            Log.d("FAIL", "EXCEPTION: $e")
            return DataOrException(exception = e)
        }
        Log.d("GET", "REPOSITORY $response")
        return DataOrException(data = response)
    }
}