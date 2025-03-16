package com.dg.testesbiryani

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

private val foodItems: List<String> = listOf("Pizza", "Burger", "Pasta", "Salad", "Organic", "Shitty","Pizza", "Burger", "Pasta", "Salad", "Organic", "Shitty")
@Composable
fun StashCompose3(innerPadding: PaddingValues) {
    //Spacer(modifier = Modifier.padding(innerPadding))
    Box(
        modifier = Modifier
            .background(Color.Blue.copy(alpha = 0.1f))
            .fillMaxSize()
            .padding(innerPadding)



    ) {
        val scrollState = rememberScrollState()
        Column(modifier = Modifier.verticalScroll(scrollState)) {
            Header()
            VerticalLine()
            Spacer(modifier = Modifier.size(8.dp))
            FoodStrip()
            Spacer(modifier = Modifier.size(8.dp))
            AndroidPicksBar()
            ContentStrip()
            AndroidPicksBar()
            ContentFoodStrip()
        }
    }
}

@Composable
fun Header() {
    Row(modifier = Modifier
        .fillMaxWidth()
        .height(40.dp), horizontalArrangement = Arrangement.SpaceAround, verticalAlignment = Alignment.CenterVertically) {
        Text(text = "Delivery to 123 Street")
        Icon(painter = painterResource(R.drawable.ic_launcher_foreground), contentDescription = null)
    }
}

@Composable
fun AndroidPicksBar() {
    Row(modifier = Modifier
        .fillMaxWidth()
        .height(40.dp), horizontalArrangement = Arrangement.SpaceAround, verticalAlignment = Alignment.CenterVertically) {
        Text(text = "Android picks")
        Icon(painter = painterResource(R.drawable.ic_launcher_foreground), contentDescription = null)
    }
}

@Composable
fun VerticalLine() {
    Spacer(modifier = Modifier
        .fillMaxWidth()
        .height(1.dp)
        .background(Color.Gray))
}

@Composable
fun FoodStrip() {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.LightGray)
            .height(64.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp)
    ) {
        item {
            Icon(
                painter = painterResource(R.drawable.ic_launcher_foreground),
                contentDescription = null,
                modifier = Modifier
                    .size(32.dp)
                    .clip(androidx.compose.foundation.shape.CircleShape)
                    .border(4.dp, Color.Magenta)
            )

        }
        items(foodItems) {
            Box(
                modifier = Modifier
                    .padding(6.dp)
//                    .clip(RoundedCornerShape(24.dp))
                    .border(
                        width = 2.dp,
                        brush = Brush.verticalGradient(listOf(Color.Cyan, Color.Magenta)),
                        shape = RoundedCornerShape(20.dp)
                    )
                    .padding(horizontal = 20.dp, vertical = 12.dp)
            ) {
                Text(text = it)
            }
        }
    }
}


@Composable
fun ContentStrip() {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.LightGray)
            ,
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp)
    ) {
        items(foodItems) {
            Box(
                modifier = Modifier
                    .padding(6.dp)
                    .height(300.dp)
                    .width(200.dp)
                    .border(
                        width = 1.dp,
                        color = Color.Gray,
                        shape = RoundedCornerShape(20.dp)
                    )
//                    .padding(horizontal = 20.dp, vertical = 12.dp)
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp)
                        .clip(
                            RoundedCornerShape(
                                topStart = 16.dp, // Curve top-left corner
                                topEnd = 16.dp,   // Curve top-right corner
                                bottomEnd = 0.dp, // No curve on bottom-right corner
                                bottomStart = 0.dp // No curve on bottom-left corner
                            )
                        )
                        .background(
                            brush = Brush.horizontalGradient(
                                listOf(Color.Blue, Color.Cyan)
                            )
                        )

                )

                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .offset(y = (-50).dp)
                        .clip(CircleShape)
                        .background(color = Color.Green.copy(alpha = 0.5f))
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_launcher_foreground),
                        contentDescription = null
                    )
                }

                Text(text = it)

                }
            }
        }
    }
}


@Composable
fun ContentFoodStrip() {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.LightGray.copy(alpha = 0.43f))
        ,
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp)
    ) {
        items(foodItems) {
            Box(
                modifier = Modifier
                    .padding(6.dp)

                    .background(color = Color.Cyan.copy(alpha = 0.2f))

//                    .padding(horizontal = 20.dp, vertical = 12.dp)
            ) {
                Column(modifier = Modifier.align(
                    Alignment.Center
                ), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(12.dp)) {

                    Box(
                        modifier = Modifier
                            .padding(12.dp)
                            .size(100.dp)
                            .clip(CircleShape)
                            .background(color = Color.Green.copy(alpha = 0.5f))
                            .padding(30.dp)

                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_launcher_foreground),
                            contentDescription = null
                        )
                    }


                    Text(text = it)

                }
            }
        }
    }
}