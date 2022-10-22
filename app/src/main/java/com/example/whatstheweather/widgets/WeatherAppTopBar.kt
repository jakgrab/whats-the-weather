package com.example.whatstheweather.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.whatstheweather.room.FavouriteEntity
import com.example.whatstheweather.screens.favourites.FavouritesViewModel
import kotlinx.coroutines.coroutineScope

//@Preview
@Composable
fun WeatherAppTopBar(
    title: String = "Seattle",
    icon: ImageVector? = null,
    isMainScreen: Boolean? = true,
    elevation: Dp = 0.dp,
    navController: NavController,
    favouritesViewModel: FavouritesViewModel = hiltViewModel(),
    onSearchClicked: () -> Unit = {},
    onButtonClicked: () -> Unit = {}
) {
    //was width = 70.dp
    val modifier = Modifier.size(width = 80.dp, height = 50.dp)
    val tintState = remember {
        mutableStateOf(Color.DarkGray.copy(alpha = 0.7f))
    }
    TopAppBar(
        title = {
            Text(
                text = title,
                modifier = Modifier.fillMaxWidth(),//.padding(start = 20.dp),
                textAlign = TextAlign.Center
            )
        },
        actions = {
            Row(
                modifier = modifier,
                horizontalArrangement = Arrangement.Start
            ) {
                if (isMainScreen == true) {
                    val isFavourite = favouritesViewModel.faveList.collectAsState().value.filter {
                        it.city == title
                    }
                    IconButton(
                        onClick = {
                            if (isFavourite.isEmpty()) {
                                favouritesViewModel.insertFavourite(
                                    FavouriteEntity(city = title)
                                )
                                tintState.value = Color(0xffe756bf)
                            }
                            if (isFavourite.isNotEmpty()) {
                                favouritesViewModel.deleteFavourite(
                                    FavouriteEntity(city = title)
                                )
                            }
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Favorite,
                            contentDescription = "Add to favourites icon",
                            modifier = Modifier.size(30.dp),
                            tint = if (isFavourite.isNotEmpty()) Color(0xffe756bf) else tintState.value
                        )
                    }
                    IconButton(onClick = { onSearchClicked.invoke() }) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search icon",
                            modifier = Modifier.size(30.dp)
                        )
                    }
                } else
                    Box {}
            }
        },
        navigationIcon = {
            if (icon != null) {
                Icon(
                    imageVector = icon,
                    contentDescription = "Go back Arrow",
                    modifier = Modifier
                        .padding(start = 15.dp, end = 15.dp)
                        .clickable {
                            onButtonClicked.invoke()
                        }
                )
            } else {
                Box {}
            }
        },
        backgroundColor = Color.Transparent,
        elevation = elevation
    )
}


