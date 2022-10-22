package com.example.whatstheweather.room

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "fav_tbl")
data class FavouriteEntity(
    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "city")
    val city: String,

//    @ColumnInfo(name = "default")
//    val default: Boolean

)
