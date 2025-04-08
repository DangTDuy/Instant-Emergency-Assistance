package com.example.resqnow.Ui_Ux.theme.introApp



import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.resqnow.Components.Bắt_đầu_color

import com.example.resqnow.R

@Composable
fun IntroScreen4(navController: NavController) {
    Box(modifier = Modifier.fillMaxSize()){
        Image(painter = painterResource(R.drawable.image_intro4),contentDescription = "tay cầm phone"
            , modifier = Modifier
                .offset(x = 0.dp, y = (-60).dp)
                .fillMaxSize()
        )

        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 550.dp)
                    ,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Hướng dẫn sơ cứu tức thì",
                    style = MaterialTheme.typography.headlineLarge,
                    color = Color.Black,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Giúp bạn xử lý mọi tình huống khẩn cấp!",
                    style = MaterialTheme.typography.headlineLarge,
                    color = Color.Black,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier
            .padding(start = 2.dp, top = 700.dp)

        ) {
            Box(
                modifier = Modifier
                    .size(width = 40.dp, height = 8.dp)
                    .clip(RoundedCornerShape(3.dp))
                    .background(Color.Gray)
            )
            Box(
                modifier = Modifier
                    .size(width = 40.dp, height = 8.dp)
                    .clip(RoundedCornerShape(3.dp))
                    .background(Color.Blue)
            )

        }
        Button(
            onClick = { navController.navigate("OutroAppScreen1") },
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
                tint = Color.White
                ,modifier = Modifier.size(37.dp)
            )
        }
    }

}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewIntroScreen4() {
    IntroScreen4(navController = NavController(LocalContext.current))
}
