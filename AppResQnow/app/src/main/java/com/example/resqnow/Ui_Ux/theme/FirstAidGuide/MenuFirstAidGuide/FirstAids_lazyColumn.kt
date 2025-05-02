package com.example.resqnow.Ui_Ux.theme.FirstAidGuide.MenuFirstAidGuide

import android.R.attr.onClick
import android.R.attr.title
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.resqnow.Components.Snake_NonVenoument
import com.example.resqnow.Components.Snake_venoument
import com.example.resqnow.Components.background_firstaid
import com.example.resqnow.Components.borderBackground
import com.example.resqnow.Components.brokenArm
import com.example.resqnow.Components.burn
import com.example.resqnow.Components.stroke
import com.example.resqnow.Components.swallow_Poison
import com.example.resqnow.R
import com.example.resqnow.Ui_Ux.theme.Router.Screen
import com.example.resqnow.viewModel.FirstAidGuideViewModel

@Composable
fun LC_FirstAids(viewModel: FirstAidGuideViewModel,searchText: String,navController: NavController) {

    val guides by viewModel.guides.collectAsState()
    val filteredGuides = guides.filter {
        it.title.contains(searchText, ignoreCase = true)
    }
    if (searchText.isNotBlank()) {

        LazyVerticalGrid (
            columns = GridCells.Fixed(3),
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                    ,horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            items(filteredGuides.size) { index ->
                val guide = filteredGuides[index]

                val (color, image) = when (guide.title) {
                    "Gãy tay" -> Pair(brokenArm, R.drawable.gaytay)
                    "Đột quỵ" -> Pair(stroke, R.drawable.stroke_1)
                    "Nuốt chất độc" -> Pair(swallow_Poison, R.drawable.swallow)
                    "Rắn cắn (có độc)" -> Pair(Snake_venoument, R.drawable.snake_venoument)
                    "Rắn cắn (không độc)" -> Pair(Snake_NonVenoument, R.drawable.nonvenoument)
                    "Bỏng nhiệt" -> Pair(burn, R.drawable.burnpng)
                    "Đuối nước" -> Pair(Color(0xFFF1D278), R.drawable.browning)
                    "Gãy chân" -> Pair(Color(0xFFE2F5FF), R.drawable.brokenleg)
                    "Chảy máu" -> Pair(Color(0xFFFCD8E1), R.drawable.bleeding)
                    else -> Pair(Color(0xFFDCE4F5), R.drawable.swallow) // fallback
                }

                    GuideCard(
                        title = guide.title,
                        color = color,
                        imageRes = image,
                        navController = navController

                    )

            }
        }
    }
    else {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 5.dp, vertical = 5.dp)
                .background(background_firstaid)
                .border(width = 1.dp, color = borderBackground, shape = RoundedCornerShape(15.dp))
        ) {
            LazyColumn(modifier = Modifier.padding(10.dp)) {
                item {
                    // First Row
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 20.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        GuideCard(
                            title = "Gãy tay",
                            color = brokenArm,
                            imageRes = R.drawable.gaytay,
                            navController =navController

                        )
                        GuideCard(
                            title = "Đột quỵ",
                            color = stroke,
                            imageRes = R.drawable.stroke_1,
                            navController =navController


                        )
                        GuideCard(
                            title = "Nuốt chất độc",
                            color = swallow_Poison,
                            imageRes = R.drawable.swallow
                            ,navController =navController

                        )
                    }
                }

                item {
                    // Second Row
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        GuideCard(
                            title = "Rắn cắn (có độc)",
                            color = Snake_venoument,
                            imageRes = R.drawable.snake_venoument
                            ,navController =navController
                        )
                        GuideCard(
                            title = "Rắn cắn (không độc)",
                            color = Snake_NonVenoument,
                            imageRes = R.drawable.nonvenoument
                            ,navController =navController
                        )
                        GuideCard(
                            title = "Bỏng nhiệt",
                            color = burn,
                            imageRes = R.drawable.burnpng
                            ,navController =navController
                        )
                    }
                }

                item {
                    // Third Row
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 30.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        GuideCard(
                            title = "Đuối nước",
                            color = Color(0xFFF1D278),
                            imageRes = R.drawable.browning
                            ,navController =navController
                        )
                        GuideCard(
                            title = "Gãy chân",
                            color = Color(0xFFE2F5FF),
                            imageRes = R.drawable.brokenleg
                            ,navController =navController
                        )
                        GuideCard(
                            title = "Chảy máu",
                            color = Color(0xFFFCD8E1),
                            imageRes = R.drawable.bleeding
                            ,navController =navController
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun GuideCard(title: String, color: Color, imageRes: Int,navController : NavController) {
    Card(
        colors = CardDefaults.cardColors(color),
        modifier = Modifier
            .size(width = 110.dp, height = 150.dp)
            .padding(horizontal = 4.dp)
            .clickable {
                Log.d("GuideCard", "Clicked on $title")
                when (title) {
                    "Gãy tay" -> navController.navigate(Screen.brokenArmFirstAid1.route )
                    "Đột quỵ" -> navController.navigate(Screen.signsOfStroke.route)
                    "Nuốt chất độc" -> navController.navigate(Screen.poisoning.route)
                    "Rắn cắn (có độc)" -> navController.navigate(Screen.venomous_Snake.route)
                    "Rắn cắn (không độc)" -> navController.navigate(Screen.nonVenomus_Snake.route)
                    "Bỏng nhiệt" -> navController.navigate(Screen.burn.route)

                    else -> {
                        navController.navigate(Screen.poisoning.route)
                    }

                }
            }
        ,

        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Image(
                painter = painterResource(imageRes),
                contentDescription = title,
                alignment = Alignment.TopCenter,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(width = 111.dp, height = 72.dp)

            )
            Spacer(modifier = Modifier.height(23.dp))
            Text(
                text = title,
                fontSize = 17.sp,
                color = Color.Red,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }
    }
}
