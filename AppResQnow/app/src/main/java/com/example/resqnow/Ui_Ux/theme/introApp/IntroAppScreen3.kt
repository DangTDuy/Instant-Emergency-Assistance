package com.example.resqnow.Ui_Ux.theme.introApp

import androidx.compose.ui.unit.sp


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.resqnow.Components.Bắt_đầu_color

import com.example.resqnow.R
import kotlinx.coroutines.delay

@Composable
fun IntroScreen3(navController: NavController) {
    var isVisible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        isVisible = true // Ẩn xe trước khi chuyển màn hình
        // Đợi hiệu ứng hoàn tất
        delay(500)

    }
    Box(modifier = Modifier.fillMaxSize()){
        Image(painter = painterResource(R.drawable.wave_hand),contentDescription = "tay cầm phone"
            , modifier = Modifier
                .offset(x = 0.dp, y = (-20).dp)
                .fillMaxSize()
        )


        Box(
            modifier = Modifier.fillMaxSize() // Mở rộng toàn bộ màn hình
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 270.dp)
                    .align(Alignment.BottomCenter), // Căn xuống dưới
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Sơ cứu - Đúng cách - An toàn",
                    style = MaterialTheme.typography.headlineLarge,
                    color = Color.Black,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        AnimatedVisibility(
            visible = isVisible,
            enter = slideInHorizontally(initialOffsetX = { -100 }) + fadeIn(),
            exit = slideOutHorizontally(targetOffsetX = { 100 }) + fadeOut()
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier
                    .padding(start = 2.dp, top = 700.dp)

            ) {
                Box(
                    modifier = Modifier
                        .size(width = 40.dp, height = 8.dp)
                        .clip(RoundedCornerShape(3.dp))
                        .background(Color.Blue)
                )
                Box(
                    modifier = Modifier
                        .size(width = 40.dp, height = 8.dp)
                        .clip(RoundedCornerShape(3.dp))
                        .background(Color.Gray)
                )

            }
        }

            Button(
                onClick = { navController.navigate("IntroAppScreen4") },
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(Bắt_đầu_color),
                modifier = Modifier
                    .align(Alignment.BottomEnd) // Căn vào góc phải
                    .offset(x = (-16).dp, y = (-50).dp) // Điều chỉnh vị trí so với góc phải
                    .size(80.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_arrow_forward),
                    contentDescription = "Next",
                    tint = Color.White, modifier = Modifier.size(37.dp)
                )
            }
        }
    }


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewIntroScreen3() {
    IntroScreen3(navController = rememberNavController())
}
