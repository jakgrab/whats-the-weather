package com.example.whatstheweather.room

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherDAO {
    @Query("SELECT * from fav_tbl")
    fun getAllFavourites(): Flow<List<FavouriteEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavourite(favourite: FavouriteEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateFavourite(favourite: FavouriteEntity)

    @Delete
    suspend fun deleteFavourite(favourite: FavouriteEntity)

//    @Query("SELECT * from fav_tbl WHERE `default` = :isDefault")
//    suspend fun getDefault(isDefault: Boolean): Flow<FavouriteEntity>
}