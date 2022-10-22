package com.example.whatstheweather.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.whatstheweather.screens.about.AboutScreen
import com.example.whatstheweather.screens.main.MainScreen
import com.example.whatstheweather.screens.WeatherSplashScreen
import com.example.whatstheweather.screens.favourites.FavouritesScreen
import com.example.whatstheweather.screens.favourites.FavouritesViewModel
import com.example.whatstheweather.screens.main.MainViewModel
import com.example.whatstheweather.screens.next_days.NextDaysScreen
import com.example.whatstheweather.screens.search.SearchScreen
import com.example.whatstheweather.screens.settings.SettingsScreen

@Composable
fun WeatherNavigation() {
    val navController = rememberNavController()
    val favouritesViewModel = hiltViewModel<FavouritesViewModel>()

    NavHost(
        navController = navController,
        startDestination = WeatherScreens.SplashScreen.name
    ) {
        composable(WeatherScreens.SplashScreen.name) {
            WeatherSplashScreen(navController, favouritesViewModel)
        }

        val route = WeatherScreens.MainScreen.name
        composable(
            route = "$route/{city}",
            arguments = listOf(
                navArgument(name = "city") {
                    type = NavType.StringType
                }
            )
        ) { navBack ->
            navBack.arguments?.getString("city").let { city ->
                val mainViewModel = hiltViewModel<MainViewModel>()
                MainScreen(navController, mainViewModel, city)
            }
        }

        composable(
            route = WeatherScreens.NextDaysScreen.name +"/{city}",
            arguments = listOf(
                navArgument(name = "city") {
                    type = NavType.StringType
                }
            )
            ) { navBack ->
            navBack.arguments?.getString("city").let { city->
                val mainViewModel = hiltViewModel<MainViewModel>()
                NextDaysScreen(navController, mainViewModel, city)
            }

        }

        composable(WeatherScreens.SearchScreen.name) {
            val mainViewModel = hiltViewModel<MainViewModel>()
            SearchScreen(navController = navController, mainViewModel)
        }

        composable(WeatherScreens.AboutScreen.name) {
            AboutScreen(navController = navController)
        }

        composable(WeatherScreens.SettingsScreen.name) {
            SettingsScreen(navController = navController)
        }

        composable(WeatherScreens.FavouritesScreen.name) {
            FavouritesScreen(navController = navController, favouritesViewModel)
        }
    }

}