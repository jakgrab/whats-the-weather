package com.example.whatstheweather.model

import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.NavType
import com.google.gson.Gson
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

//@Parcelize
data class Weather(
    val current:  Current,
    val forecast:  Forecast,
    val location:  Location)
//) : Parcelable {
//    companion object NavigationType : NavType<Weather>(isNullableAllowed = false) {
//        override fun get(bundle: Bundle, key: String): Weather? {
//            return bundle.getParcelable(key, Class<Weather> )
//        }
//
//        override fun parseValue(value: String): Weather {
//            return Gson().fromJson(value, Weather::class.java)
//        }
//
//        override fun put(bundle: Bundle, key: String, value: Weather) {
//            bundle.putParcelable(key, value)
//        }
//    }
//}