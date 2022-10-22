package com.example.whatstheweather.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun WeatherCondition(icon: ImageVector, text: String, stringResource: String) {
    Column(
        modifier = Modifier
            .padding(10.dp)
            .height(80.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = icon,
            contentDescription = "Condition icon",
            modifier = Modifier.scale(1.5f),
            tint = Color(0xff6c3ffe)
        )
        Text(
            text = text,
            color = Color.Black,
            fontSize = 15.sp
        )
        Text(
            text = stringResource,
            color = Color(0xFFFFFFFF),
            fontSize = 15.sp
        )
    }
}