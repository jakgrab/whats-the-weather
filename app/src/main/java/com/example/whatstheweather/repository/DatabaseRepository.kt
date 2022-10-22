package com.example.whatstheweather.repository

import com.example.whatstheweather.room.FavouriteEntity
import com.example.whatstheweather.room.WeatherDAO
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DatabaseRepository @Inject constructor(private val weatherDAO: WeatherDAO) {

    fun getAllFavourites(): Flow<List<FavouriteEntity>> = weatherDAO.getAllFavourites()

    suspend fun insertFavourite(favourite: FavouriteEntity) = weatherDAO.insertFavourite(favourite)

    suspend fun updateFavourite(favourite: FavouriteEntity) = weatherDAO.updateFavourite(favourite)

    suspend fun deleteFavourite(favourite: FavouriteEntity) = weatherDAO.deleteFavourite(favourite)

   // suspend fun getDefault(): Flow<FavouriteEntity> = weatherDAO.getDefault(isDefault = true)
}