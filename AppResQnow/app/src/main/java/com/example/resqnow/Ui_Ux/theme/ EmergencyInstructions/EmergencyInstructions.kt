package com.example.resqnow.Ui_Ux.theme.EmergencyInstructions

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.resqnow.R

// màn hinh hướng dẫn khẩn cấp
 @Composable
fun EmergencyInstructions(navController: NavController){
    Column(
        modifier = Modifier
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

            Image(
                painter = painterResource(R.drawable.kinhlup),
                contentDescription = "Search",
                modifier = Modifier
                    .size(40.dp)
                    .clickable {}
                    .offset(x = 10.dp)
            )
            Spacer(modifier = Modifier.width(5.dp))

            val gradientBrush = Brush.linearGradient(
                colors = listOf(Color(0xFFFA382D), Color(0xFF94211A))
            )

            Box(
                modifier = Modifier
                    .offset(x = 10.dp, y = -5.dp)
                    .background(brush = gradientBrush, shape = RoundedCornerShape(50.dp))
            ) {
                Button(
                    onClick = { navController.navigate("SignupScreen") },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                    modifier = Modifier
                        .size(width = 127.dp, height = 45.dp)
                        .clip(RoundedCornerShape(15.dp))
                ) {
                    Text(
                        text = "Đăng Nhập",
                        fontSize = 14.sp,
                        color = Color.White
                    )
                }
            }
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
                text = "Hướng dẫn",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(start = 250.dp, top = 60.dp)
            )
            Text(
                text = "  khẩn cấp",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(start = 250.dp, top = 95.dp)
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
                painter = painterResource(R.drawable.huongdankhancap),
                contentDescription = "ảnh",
                modifier = Modifier
                    .padding(end = 150.dp, top = 30.dp)
                    .height(200.dp)
                    .width(300.dp)
            )
        }
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 750.dp)
            .height(80.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(R.drawable.trangchu),
                contentDescription = "Trang chủ",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(140.dp)
                    .clickable (onClick = {navController.navigate("HomeScreen1")})
            )
            Spacer(modifier = Modifier.width(10.dp))
            Image(
                painter = painterResource(R.drawable.a),
                contentDescription = "",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(50.dp)
                    .clickable {}
            )
            Spacer(modifier = Modifier.width(10.dp))
            Image(
                painter = painterResource(R.drawable.b),
                contentDescription = "",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(40.dp)
                    .clickable {}
            )
            Spacer(modifier = Modifier.width(10.dp))
            Image(
                painter = painterResource(R.drawable.c),
                contentDescription = "",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(40.dp)
                    .clickable {}
            )
        }
    }
}