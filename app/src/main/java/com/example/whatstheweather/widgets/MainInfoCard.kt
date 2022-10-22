package com.example.whatstheweather.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.whatstheweather.model.Weather
import com.example.whatstheweather.utils.*


@Composable
fun MainInfoCard(data: Weather, content: @Composable () -> Unit) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .height(530.dp)
            .background(Color.White)
    ) {
        val (mainCard, basicInfoRow) = createRefs()

        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .height(480.dp)
                .background(brush = Gradients.MAIN_CARD_GRADIENT)
                .constrainAs(mainCard) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }

        ) {
            val (card, image, date) = createRefs()

            Card(
                modifier = Modifier
                    .size(270.dp)
                    .constrainAs(card) {
                        top.linkTo(parent.top, margin = 20.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                backgroundColor = Color.Transparent,
                shape = RoundedCornerShape(80.dp),
                elevation = 7.dp
            ) {
                TemperatureRow(
                    data = data,
                    gradientTemp = Gradients.BACKGROUND_GRADIENT,
                    gradientText = Gradients.TEXT_GRADIENT
                )
            }

            // Current Weather Icon
            Image(
                bitmap = ImageBitmap.imageResource(getCurrentWeatherIcon(data)),
                contentDescription = "Current weather icon",
                modifier = Modifier
                    .size(250.dp)
                    .constrainAs(image) {
                        top.linkTo(parent.top, margin = 150.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                contentScale = ContentScale.Fit
            )
            // Date
            Card(
                modifier = Modifier
                    .constrainAs(date) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                shape = RoundedCornerShape(15.dp),
                backgroundColor = Color.White.copy(alpha = 0.95f)
            ) {
                Text(//
                    text = formatDate(data.location.localtime_epoch),
                    modifier = Modifier.padding(6.dp),
                    fontSize = 15.sp
                )
            }
        }
        // Basic weather info
        Card(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .constrainAs(basicInfoRow) {
                    bottom.linkTo(parent.bottom, margin = 20.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            shape = RoundedCornerShape(15.dp),
            backgroundColor = Color.White
        ) {
            content()
        }
    }
}

@Composable
private fun TemperatureRow(
    data: Weather,
    gradientTemp: Brush,
    gradientText: Brush
) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(gradientTemp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        ConstraintLayout(modifier = Modifier.fillMaxSize()) {
            val (temp, degrees, weather) = createRefs()
            Text(
                //
                text = formatTemperature(data.current.temp_c),
                modifier = Modifier
                    .textBrush(brush = gradientText)
                    .constrainAs(temp) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)//degrees.start)
                    },
                fontSize = 170.sp,
            )
            Text(
                text = "°",
                modifier = Modifier
                    .textBrush(brush = gradientText)
                    .constrainAs(degrees) {
                        top.linkTo(parent.top, margin = 35.dp)
                        start.linkTo(temp.end)
                        end.linkTo(parent.end)
                    },
                fontSize = 70.sp,
            )
        }
    }
}
//}
