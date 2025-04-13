package com.example.resqnow.Ui_Ux.theme.Homepage

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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.resqnow.Data.Api_and_Firebase.FireBaseGoogle.GoogleAuthUiClient
import com.example.resqnow.Data.Api_and_Firebase.FireBaseGoogle.UserViewModel
import com.example.resqnow.R

@Composable
fun HomePage1(navController: NavController,googleAuthUiClient: GoogleAuthUiClient) {
    val user = googleAuthUiClient.getSignedInUser()

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
                    .padding(start = 5.dp)
            )
            Spacer(modifier = Modifier.width(5.dp))

            val gradientBrush = Brush.linearGradient(
                colors = listOf(Color(0xFFFA382D), Color(0xFF94211A))
            )

            Box(
                modifier = Modifier

                    .padding(start = 5.dp)
                    .size(width = 150.dp,height = 45.dp)
                    .background(brush = gradientBrush, shape = RoundedCornerShape(50.dp))
            ) {
                if (user != null) {
                    // Nếu đã đăng nhập, hiển thị tên người dùng
                    Text(
                        text = user?.name ?: "Email",
                        fontSize = 15.sp,
                        color = Color.White, fontWeight = FontWeight.ExtraBold,
                        modifier = Modifier.align(Alignment.Center)
                    )
                } else {
                    // Nếu chưa đăng nhập, hiển thị "Đăng nhập" và cho phép nhấp
                    Text(
                        text = "Đăng Ký",
                        fontSize = 15.sp,
                        color = Color.White, fontWeight = FontWeight.ExtraBold,
                        modifier = Modifier
                            .align(Alignment.Center)
                            .clickable {
                            navController.navigate("SignInScreen")
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.width(5.dp))

        Box {
            Image(
                painter = painterResource(R.drawable.bg),
                contentDescription = "nền",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )

            Row(
                modifier = Modifier
                    .padding(top = 20.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = "14:00,Ngày 24 tháng 08",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 16.dp)
                )

                Spacer(modifier = Modifier.width(50.dp))

                Text(
                    text = "LH: 0808.555.555",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.offset(x = 35.dp)
                )
            }

            Row(modifier = Modifier.padding(top = 50.dp, start = 270.dp)) {
                Image(
                    painter = painterResource(R.drawable.facebook),
                    contentDescription = "fb",
                    modifier = Modifier
                        .clickable(onClick = {})
                        .size(30.dp)
                )
                Image(
                    painter = painterResource(R.drawable.tiktok),
                    contentDescription = "tt",
                    modifier = Modifier
                        .clickable(onClick = {})
                        .size(30.dp)
                )
                Image(
                    painter = painterResource(R.drawable.instagram),
                    contentDescription = "ig",
                    modifier = Modifier
                        .clickable(onClick = {})
                        .size(30.dp)
                )
            }

            val images = listOf(R.drawable.hinh1, R.drawable.hinh2)
            var currentImageIndex by remember { mutableStateOf(0) }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 60.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .height(200.dp)
                ) {
                    Image(
                        painter = painterResource(images[currentImageIndex]),
                        contentDescription = "image $currentImageIndex",
                        modifier = Modifier
                            .width(360.dp)
                            .height(250.dp)
                    )

                    Row(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(top = 100.dp),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        if (currentImageIndex == 0) {
                            Box(
                                modifier = Modifier
                                    .size(10.dp)
                                    .background(Color.Red, CircleShape)
                            )
                            Box(
                                modifier = Modifier
                                    .size(10.dp)
                                    .background(Color.White, CircleShape)
                            )
                        } else {
                            Box(
                                modifier = Modifier
                                    .size(10.dp)
                                    .background(Color.White, CircleShape)
                            )
                            Box(
                                modifier = Modifier
                                    .size(10.dp)
                                    .background(Color.Red, CircleShape)
                            )
                        }
                    }

                    Image(
                        painter = painterResource(R.drawable.trai),
                        contentDescription = "Nút trái",
                        modifier = Modifier
                            .size(40.dp)
                            .align(Alignment.CenterStart)
                            .clickable {
                                currentImageIndex = if (currentImageIndex > 0) currentImageIndex - 1 else images.size - 1
                            }
                    )

                    Image(
                        painter = painterResource(R.drawable.phai),
                        contentDescription = "Nút phải",
                        modifier = Modifier
                            .size(40.dp)
                            .align(Alignment.CenterEnd)
                            .clickable {
                                currentImageIndex = if (currentImageIndex < images.size - 1) currentImageIndex + 1 else 0
                            }
                    )
                }
            }
        }

        Box(
            modifier = Modifier
                .offset(y = -90.dp)
                .fillMaxWidth()
        ) {
            Image(
                painter = painterResource(R.drawable.bg1),
                contentDescription = "nền",
                modifier = Modifier
                    .fillMaxWidth()
                    .size(500.dp)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = 40.dp)
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Image(
                    painter = painterResource(R.drawable.group_26),
                    contentDescription = "tính năng 1",
                    modifier = Modifier
                        .size(200.dp)
                        .clickable(onClick = {navController.navigate("EmergencyInstructions") })
                )

                Spacer(modifier = Modifier.width(10.dp))

                Image(
                    painter = painterResource(R.drawable.hocsocuu),
                    contentDescription = "tính năng 2",
                    modifier = Modifier
                        .offset(y = -10.dp)
                        .size(300.dp)
                        .clickable(onClick = {navController.navigate("LearnFirstAid") })
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = 240.dp)
                    .padding(horizontal = 16.dp)
            ) {
                Image(
                    painter = painterResource(R.drawable.gioithieu),
                    contentDescription = "tính năng 3",
                    modifier = Modifier
                        .size(180.dp)
                        .clickable(onClick = {navController.navigate("IntroductionGuide") })
                )
                Spacer(modifier = Modifier.width(20.dp))
                Image(
                    painter = painterResource(R.drawable.canhanhoa),
                    contentDescription = "tính năng 4",
                    modifier = Modifier
                        .offset(y = 20.dp)
                        .size(190.dp)
                        .clickable(onClick = {navController.navigate("Personalization") })
                )
            }
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
            var showDialog by remember { mutableStateOf(false) }

            Image(
                painter = painterResource(R.drawable.trangchu),
                contentDescription = "Trang chủ",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(140.dp)
                    .clickable {
                        showDialog = true
                    }
            )

            if (showDialog) {
                AlertDialog(
                    onDismissRequest = { showDialog = false },
                    title = { Text("Xin chào!") },
                    text = { Text("Bạn đang ở trang chủ.") },
                    confirmButton = {
                        TextButton(onClick = { showDialog = false }) {
                            Text("Đóng")
                        }
                    }
                )
            }
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
                    .clickable { navController.navigate("Maps") }
            )
            Spacer(modifier = Modifier.width(10.dp))
            Image(
                painter = painterResource(R.drawable.c),
                contentDescription = "",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(40.dp)
                    .clickable {
                        navController.navigate("ProfileScreen")
                    }
            )
        }
    }
}