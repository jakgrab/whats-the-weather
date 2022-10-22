package com.example.whatstheweather.di

import android.content.Context
import androidx.room.Room
import com.example.whatstheweather.BuildConfig
import com.example.whatstheweather.network.WeatherAPI
import com.example.whatstheweather.room.WeatherDAO
import com.example.whatstheweather.room.WeatherDatabase
import com.example.whatstheweather.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideWeatherAPI(): WeatherAPI {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(WeatherAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideWeatherDAO(weatherDatabase: WeatherDatabase): WeatherDAO =
        weatherDatabase.weatherDao()

    @Provides
    @Singleton
    fun provideWeatherDatabase(@ApplicationContext context: Context): WeatherDatabase =
        Room.databaseBuilder(
            context,
            WeatherDatabase::class.java,
            "fav_tbl"
        ).fallbackToDestructiveMigration().build()
}