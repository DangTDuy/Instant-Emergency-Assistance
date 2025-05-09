package com.example.resqnow.Ui_Ux.theme.IntroductionGuide

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.resqnow.R
import com.example.resqnow.Ui_Ux.theme.FirstAidGuide.ViewMenu.MenuFirstAidGuide.bottomBar

// màn hình giới thiệu ứng dụng

@Composable
fun IntroductionGuide(navController: NavController) {
    Scaffold (
        containerColor = Color.White,
        bottomBar = {

            bottomBar(navController = navController)
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxWidth()
                .padding(top = 60.dp)
        ) {
            Row(
                modifier = Modifier.padding(start = 16.dp)
            ) {
                Text(
                    text = "ResQnow",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.width(80.dp))


                Spacer(modifier = Modifier.width(5.dp))

                val gradientBrush = Brush.linearGradient(
                    colors = listOf(Color(0xFFFA382D), Color(0xFF94211A))
                )


            }

            Spacer(modifier = Modifier.height(20.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(630.dp)
            ) {
                Image(
                    painter = painterResource(R.drawable.nen),
                    contentDescription = "bg",
                    modifier = Modifier
                        .height(200.dp)
                        .width(500.dp)
                )
                Text(
                    text = "Giới Thiệu",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.padding(start = 240.dp, top = 70.dp)
                )
                Image(
                    painter = painterResource(R.drawable.nen1),
                    contentDescription = "ảnh",
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 200.dp)
                        .height(430.dp)
                )
                Image(
                    painter = painterResource(R.drawable.gt),
                    contentDescription = "ảnh",
                    modifier = Modifier
                        .padding(end = 150.dp, top = 30.dp)
                        .height(230.dp)
                        .width(350.dp)
                )
                LazyColumn(
                    modifier = Modifier
                        .padding(top = 240.dp)
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                ) {
                    items(
                        listOf(
                            R.drawable.buttonsotaycuuthuong,
                            R.drawable.btcanhanhoa,
                            R.drawable.bthocphuongphapsocuu,
                            R.drawable.btlienlackhancap,
                            R.drawable.bttimdiachibenhvien
                        )
                    ) { imageRes ->
                        Image(
                            painter = painterResource(imageRes),
                            contentDescription = "",
                            contentScale = ContentScale.Fit,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp)
                                .padding(vertical = 8.dp)
                        )
                    }
                }
            }
        }
    }

}