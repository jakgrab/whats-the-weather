package com.example.whatstheweather.utils

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

class Gradients {
    companion object {
        val MAIN_CARD_GRADIENT = Brush.verticalGradient(
            0.0f to Color.White,
            0.7f to Color(0xfff0e7fe)
        )
        val SEARCH_SCREEN_GRADIENT = Brush.verticalGradient(
            0.0f to Color.White,
            0.7f to Color(0xFFEBD2E6)
        )
        val BACKGROUND_GRADIENT = Brush.linearGradient(
            0.0f to Color(0xffb125f0),
            0.4f to Color(0xffe756bf),
            0.9f to Color(0xfffcbfba),
            1.0f to Color(0xfff0c5cb)
        )

        val TEXT_GRADIENT = Brush.verticalGradient(
            0.4f to Color.White,
            0.5f to Color(0xfff8beec)
        )

        val DRAWER_GRADIENT = Brush.horizontalGradient(
            0.0f to Color(0xffb125f0),
            0.4f to Color(0xffe756bf)
        )
    }
}