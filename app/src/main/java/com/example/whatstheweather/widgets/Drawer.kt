package com.example.whatstheweather.widgets

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.WbSunny
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.whatstheweather.R

@Preview
@Composable
//, navController: NavController
fun Drawer(
    modifier: Modifier = Modifier,
    onFavouritesClicked: () -> Unit = {},
    onAboutClicked: () -> Unit = {},
    onSettingsClicked: () -> Unit = {}
) {
    Surface(
        modifier = modifier
            .fillMaxSize(),
    ) {
        Column(
            modifier
                .padding(40.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = modifier.width(300.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Image(
                    painter = painterResource(id = R.drawable.storm),
                    contentDescription = "Splash screen icon",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.size(70.dp)
                )
                Text(
                    text = "Whats The Weather?",
                    color = Color.DarkGray,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
            Spacer(modifier = modifier.height(30.dp))
            Divider(
                modifier = modifier.fillMaxWidth(),
                color = Color(0xffb125f0),
                thickness = 1.dp
            )

            Column(
                modifier = Modifier.height(500.dp),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                NavigationRow(text = "Favourites", icon = Icons.Outlined.WbSunny) {
                    onFavouritesClicked.invoke()
                }
                NavigationRow(text = "About", icon = Icons.Outlined.Info) {
                    onAboutClicked.invoke()
                }
                NavigationRow(text = "Settings", icon = Icons.Outlined.Settings) {
                    onSettingsClicked.invoke()
                }
            }
        }

    }
}

@Composable
fun NavigationRow(text: String, icon: ImageVector, onRowClicked: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .height(60.dp)
            .clickable {
                onRowClicked()
            },
        shape = RoundedCornerShape(20.dp),
        border = BorderStroke(2.dp, Color(0xFF847C88))
    ) {
        Row(
            modifier = Modifier
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Icon(
                imageVector = icon,
                contentDescription = "Favourites icon",
                modifier = Modifier.size(40.dp),
                tint = Color(0xffb125f0)
            )
            Text(
                text = text,
                color = Color(0xffb125f0),
                fontSize = 25.sp,
                fontWeight = FontWeight.Light
            )
        }
    }
}
