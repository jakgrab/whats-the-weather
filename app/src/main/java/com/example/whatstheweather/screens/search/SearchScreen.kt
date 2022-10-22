package com.example.whatstheweather.screens.search

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBackIosNew
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.whatstheweather.data.DataOrException
import com.example.whatstheweather.model.Weather
import com.example.whatstheweather.screens.main.MainViewModel
import com.example.whatstheweather.utils.unaccent
import com.example.whatstheweather.widgets.WeatherAppTopBar
import androidx.compose.ui.platform.LocalFocusManager
import com.example.whatstheweather.navigation.WeatherScreens
import com.example.whatstheweather.utils.Gradients

@Composable
fun SearchScreen(navController: NavController, mainViewModel: MainViewModel) {

    Scaffold(
        topBar = {
            WeatherAppTopBar(
                title = "Search",
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
                SearchField { searchQuery ->
                    navController.navigate(WeatherScreens.MainScreen.name + "/$searchQuery")
                }
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchField(
    onSearch: (String) -> Unit
) {
    val searchQueryState = rememberSaveable {
        mutableStateOf("")
    }
    val keyboardController = LocalSoftwareKeyboardController.current
    val validState = remember(searchQueryState.value) {
        searchQueryState.value.trim().isNotEmpty()
    }
    val focusManager = LocalFocusManager.current
    MyTextField(
        valueState = searchQueryState,
        placeHolder = "Search for city",
        onAction = KeyboardActions {
            if (!validState) return@KeyboardActions
            onSearch(searchQueryState.value.trim().unaccent())
            searchQueryState.value = ""
            keyboardController?.hide()
            focusManager.clearFocus()
        }
    )
}

@Composable
fun MyTextField(
    valueState: MutableState<String>,
    placeHolder: String,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Search,
    onAction: KeyboardActions = KeyboardActions.Default
) {
    OutlinedTextField(
        value = valueState.value,
        onValueChange = { newValue ->
            valueState.value = newValue
        },
        modifier = Modifier
            .width(400.dp)
            .height(80.dp)
            .padding(horizontal = 10.dp),
        label = { Text(text = "Type here to search") },
        placeholder = { Text(text = placeHolder) },
        leadingIcon = {
            Icon(
                imageVector = Icons.Rounded.Search,
                contentDescription = "Search icon"
            )
        },
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType, imeAction = imeAction),
        keyboardActions = onAction,
        singleLine = true,
        maxLines = 1,
        shape = RoundedCornerShape(45.dp),
        colors = TextFieldDefaults
            .outlinedTextFieldColors(
                textColor = Color.Black,
                disabledTextColor = Color.LightGray,
                cursorColor = Color(0xffb125f0),
                focusedBorderColor = Color(0x8D2C292B),
                unfocusedBorderColor = Color(0x5E4B4E48),
                leadingIconColor = Color(0xFF9432C0),
                trailingIconColor = Color(0xffb125f0),
                focusedLabelColor = Color(0xFF752599),
                unfocusedLabelColor = Color.DarkGray,
                placeholderColor = Color.LightGray

            )
    )
}
