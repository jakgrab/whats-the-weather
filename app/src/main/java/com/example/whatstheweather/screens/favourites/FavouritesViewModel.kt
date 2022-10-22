package com.example.whatstheweather.screens.favourites

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.whatstheweather.repository.DatabaseRepository
import com.example.whatstheweather.room.FavouriteEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouritesViewModel @Inject constructor(private val repository: DatabaseRepository) :
    ViewModel() {
    private val _faveList = MutableStateFlow<List<FavouriteEntity>>(emptyList())
    val faveList = _faveList.asStateFlow()

    private val _default = MutableStateFlow(FavouriteEntity(city = "San Francisco"))
    val default = _default.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllFavourites().distinctUntilChanged()
                .collect { listOfFavs ->
                    if (listOfFavs.isEmpty())
                        Log.d("FVM", "list of favourites is empty")

                    _faveList.value = listOfFavs
                    Log.d("FVM", ":list of favourites is not empty! ${faveList.value} ")
                }
        }
    }

    fun insertFavourite(favourite: FavouriteEntity) = viewModelScope.launch {
        repository.insertFavourite(favourite)
    }

    fun updateFavourite(favourite: FavouriteEntity) = viewModelScope.launch {
        repository.updateFavourite(favourite)
    }
    
    fun deleteFavourite(favourite: FavouriteEntity) = viewModelScope.launch {
        repository.deleteFavourite(favourite)
    }

//    fun markAsDefault(favourite: FavouriteEntity) {
//        Log.d("FVM", "REPLACED default city with ${favourite.city}")
//        repository.
//    }
}