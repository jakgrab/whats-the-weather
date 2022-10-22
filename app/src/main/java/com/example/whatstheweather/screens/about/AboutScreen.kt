package com.example.whatstheweather.screens.about

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBackIosNew
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.whatstheweather.utils.Gradients
import com.example.whatstheweather.widgets.WeatherAppTopBar


@Composable
fun AboutScreen(navController: NavController) {
    Scaffold(
        topBar = {
            WeatherAppTopBar(
                title = "About",
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
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val apiString = buildAnnotatedString {
                    append("Data coming from ")

                    pushStringAnnotation(
                        tag = "api",
                        annotation = "https://www.weatherapi.com/"
                    )
                    withStyle(
                        style = SpanStyle(color = Color(0xffe756bf))
                    ) {
                        append("WeatherAPI")
                    }
                    pop()
                }
                val uiString = buildAnnotatedString {
                    append("UI inspired from ")

                    pushStringAnnotation(
                        tag = "ui",
                        annotation = "https://dribbble.com/shots/15208692-Weather-App"
                    )
                    withStyle(
                        style = SpanStyle(color = Color(0xffe756bf))
                    ) {
                        append("Herdetya Priambodo")
                    }
                    pop()
                }

                val iconsString = buildAnnotatedString {
                    append("Icons by ")

                    pushStringAnnotation(
                        tag = "icons",
                        annotation = "https://ui8.net/hosein_bagheri/products?status=6"
                    )
                    withStyle(
                        style = SpanStyle(color = Color(0xffe756bf))
                    ) {
                        append("hosein_bagheri")
                    }
                    pop()
                }

                val uriHandler = LocalUriHandler.current

                Row(
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    ClickableText(
                        text = apiString,
                        style = MaterialTheme.typography.subtitle1,
                        onClick = { offset ->
                            apiString.getStringAnnotations(
                                tag = "api",
                                start = offset,
                                end = offset
                            ).firstOrNull()?.let {
                                uriHandler.openUri(uri = it.item)
                            }
                        }
                    )
                }
                Spacer(Modifier.height(25.dp))

                Divider(
                    Modifier
                        .fillMaxWidth(0.6f)
                        .background(Color(0xFFF89ED4))
                )

                Spacer(Modifier.height(25.dp))

                Row(
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    ClickableText(
                        text = uiString,
                        style = MaterialTheme.typography.subtitle1,
                        onClick = { offset ->
                            uiString.getStringAnnotations(
                                tag = "ui",
                                start = offset,
                                end = offset
                            ).firstOrNull()?.let {
                                uriHandler.openUri(uri = it.item)
                            }
                        }
                    )
                }
                Spacer(Modifier.height(25.dp))

                Divider(
                    Modifier
                        .fillMaxWidth(0.6f)
                        .background(Color(0xFFF89ED4))
                )

                Spacer(Modifier.height(25.dp))

                Row(
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    ClickableText(text = iconsString,
                        style = MaterialTheme.typography.subtitle1,
                        onClick = { offset ->
                            iconsString.getStringAnnotations(
                                tag = "icons",
                                start = offset,
                                end = offset
                            ).firstOrNull()?.let {
                                uriHandler.openUri(uri = it.item)
                            }
                        })
                }

                Spacer(Modifier.height(375.dp))

                Text(
                    text = "by Jakub Grabowski"
                )
            }
        }
    }
}