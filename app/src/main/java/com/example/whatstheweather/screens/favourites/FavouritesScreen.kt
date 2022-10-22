package com.example.whatstheweather.screens.favourites

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBackIosNew
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.whatstheweather.navigation.WeatherScreens
import com.example.whatstheweather.room.FavouriteEntity
import com.example.whatstheweather.utils.Gradients
import com.example.whatstheweather.widgets.WeatherAppTopBar

@Composable
fun FavouritesScreen(
    navController: NavController,
    favouritesViewModel: FavouritesViewModel = hiltViewModel()
) {
    Scaffold(
        topBar = {
            WeatherAppTopBar(
                title = "Favourites",
                icon = Icons.Rounded.ArrowBackIosNew,
                isMainScreen = false,
                navController = navController
            ) {
                navController.popBackStack()
            }
        }
    ) {
        Surface(
            modifier = Modifier.fillMaxSize()

        ) {
            Column(
                modifier = Modifier.background(Gradients.SEARCH_SCREEN_GRADIENT),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val favouritesList = favouritesViewModel.faveList.collectAsState().value

                LazyVerticalGrid(
                    columns = GridCells.Fixed(count = 2),
                    contentPadding = PaddingValues(15.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalArrangement = Arrangement.Center
                ) {
                    items(items = favouritesList) { fav ->
                        FavouriteLocations(
                            favourite = fav,
                            navController = navController,
                            onDelete = {
                                favouritesViewModel.deleteFavourite(it)
                            },
                            onMarkAsDefault = {
                                //favouritesViewModel.markAsDefault(it)
                            }
                        )
                    }
                }

            }
        }
    }
}


@Composable
fun FavouriteLocations(
    favourite: FavouriteEntity,
    navController: NavController,
    onDelete: (favourite: FavouriteEntity) -> Unit,
    onMarkAsDefault: (favourite: FavouriteEntity) -> Unit
) {
    Card(
        modifier = Modifier
            .padding(15.dp)
            .width(250.dp)
            .height(150.dp)
            .clickable {
                navController.navigate(WeatherScreens.MainScreen.name + "/${favourite.city}")
            },
        shape = RoundedCornerShape(15.dp),
    ) {
        Column(
            modifier = Modifier
                .background(Gradients.BACKGROUND_GRADIENT),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = favourite.city,
                modifier = Modifier.padding(10.dp),
                color = Color.White,
                fontSize = 20.sp,
            )
            IconButton(onClick = { onMarkAsDefault(favourite) }) {
                Icon(
                    imageVector = Icons.Rounded.Star,
                    contentDescription = "Mark as default icon",
                    modifier = Modifier.size(30.dp),
                    tint = Color(0xfff0c5cb)
                )
            }
            IconButton(onClick = { onDelete(favourite) }) {
                Icon(
                    imageVector = Icons.Rounded.Delete,
                    contentDescription = "Delete location from favourites",
                    modifier = Modifier.size(30.dp),
                    tint = Color(0xfff0c5cb)
                )
            }


        }
    }
}
