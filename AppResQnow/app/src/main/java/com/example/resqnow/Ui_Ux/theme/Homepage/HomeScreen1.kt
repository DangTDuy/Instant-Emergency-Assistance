package com.example.resqnow.Ui_Ux.theme.Homepage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.resqnow.Data.Api_and_Firebase.FireBaseGoogle.GoogleAuthUiClient
import com.example.resqnow.R
import com.example.resqnow.Ui_Ux.theme.Router.Screen
import kotlinx.coroutines.delay
import java.util.Calendar
import androidx.compose.animation.Crossfade
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.ui.text.style.TextAlign

val gradientBrush = Brush.linearGradient(
    colors = listOf(Color(0xFFFA382D), Color(0xFF94211A))
)


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun HomePage1(navController: NavController, googleAuthUiClient: GoogleAuthUiClient) {
    val user = googleAuthUiClient.getSignedInUser()
    //real time
    var currentTime by remember { mutableStateOf(Calendar.getInstance()) }
    // Cập nhật mỗi giây
    LaunchedEffect(Unit) {
        while (true) {
            currentTime = Calendar.getInstance()
            delay(1000)
        }
    }

    val hour = currentTime.get(Calendar.HOUR_OF_DAY)
    val minute = currentTime.get(Calendar.MINUTE)
    val second = currentTime.get(Calendar.SECOND)
    var day = currentTime.get(Calendar.DAY_OF_MONTH)
    var month = currentTime.get(Calendar.MONTH) + 1
    var year = currentTime.get(Calendar.YEAR)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 60.dp)
    ) {
        Row(
            modifier = Modifier.padding(start = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "ResQnow",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.width(40.dp))

            Image(
                painter = painterResource(R.drawable.kinhlup),
                contentDescription = "Search Icon",
                modifier = Modifier
                    .size(40.dp)
                    .clickable {}
                    .padding(start = 5.dp)
            )

            Spacer(modifier = Modifier.width(2.dp))



            Box(
                modifier = Modifier
                    .padding(horizontal = 5.dp)
                    .weight(1f)
                    . height (50.dp  )
                    .background(brush = gradientBrush, shape = RoundedCornerShape(15.dp))
                ,contentAlignment = Alignment.Center
            ) {
                if (user != null) {
                    Text(
                        text = user.name ?: "User",
                        fontSize = 15.sp,
                        color = Color.White,
                        fontWeight = FontWeight.ExtraBold,
                        modifier = Modifier.align(Alignment.Center)
                    )
                } else {
                    Text(
                        text = "Đăng ký",
                        fontSize = 18.sp,
                        color = Color.White,
                        fontWeight = FontWeight.ExtraBold,
                        modifier = Modifier
                            .align(Alignment.Center)
                            .clickable {
                                if (navController.graph.findNode("SignInScreen") != null) {
                                    navController.navigate("SignInScreen")
                                }
                            }

                    )
                }
            }
        }

        Spacer(modifier = Modifier.width(7.dp))

        Box {
            Image(
                painter = painterResource(R.drawable.bg),
                contentDescription = "Background Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = 5.dp)
                    .height(200.dp),
                contentScale = ContentScale.Crop
            )

            Row(
                modifier = Modifier
                    .padding(top = 20.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                ) {
                    Text(
                        text = "Ngày $day/$month/$year",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(start = 16.dp)
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = "$hour:$minute:$second",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(start = 16.dp)
                    )
                }

                Text(
                    text = "LH:0808.555.555",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 16.dp),
                    textAlign = TextAlign.End
                )
            }


            Row(
                modifier = Modifier
                    .padding(top = 50.dp, end = 16.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Image(
                    painter = painterResource(R.drawable.facebook),
                    contentDescription = "Facebook Icon",
                    modifier = Modifier
                        .clickable {}
                        .size(30.dp)
                )
                Spacer(modifier = Modifier.width(3.dp))
                Image(
                    painter = painterResource(R.drawable.tiktok),
                    contentDescription = "TikTok Icon",
                    modifier = Modifier
                        .clickable {}
                        .size(30.dp)
                )
                Spacer(modifier = Modifier.width(3.dp))
                Image(
                    painter = painterResource(R.drawable.instagram),
                    contentDescription = "Instagram Icon",
                    modifier = Modifier
                        .clickable {}
                        .size(30.dp)
                )
            }


            val images = listOf(R.drawable.hinh1, R.drawable.hinh2)
            var currentImageIndex by remember { mutableStateOf(0) }
            LaunchedEffect(Unit) {
                while (true) {
                    delay(2000)
                    currentImageIndex = 1
                    delay(2000)
                    currentImageIndex = 0
                }
            }

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
                    AnimatedContent(
                        targetState = currentImageIndex,
                        transitionSpec = {
                            // Điều chỉnh slide trái/phải nếu muốn
                            slideInHorizontally(
                                animationSpec = tween(durationMillis = 2000),
                                initialOffsetX = { fullWidth -> fullWidth } // Slide từ phải vào
                            ) with slideOutHorizontally(
                                animationSpec = tween(durationMillis = 2000),
                                targetOffsetX = { fullWidth -> -fullWidth } // Slide ra trái
                            )
                        },
                        label = "SlideImageAnimation"
                    ) { index ->
                        Image(
                            painter = painterResource(images[index]),
                            contentDescription = "Carousel Image ${index + 1}",
                            modifier = Modifier
                                .width(360.dp)
                                .height(250.dp),
                            contentScale = ContentScale.Fit
                        )
                    }

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
                        contentDescription = "Previous Image",
                        modifier = Modifier
                            .size(40.dp)
                            .align(Alignment.CenterStart)
                            .clickable {
                                currentImageIndex = if (currentImageIndex > 0) currentImageIndex - 1 else images.size - 1
                            }
                    )

                    Image(
                        painter = painterResource(R.drawable.phai),
                        contentDescription = "Next Image",
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
                .offset(y = -60.dp)
                .fillMaxWidth()
        ) {
            Image(
                painter = painterResource(R.drawable.bg1),
                contentDescription = "Background",
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = 34.dp)
                    .size(470.dp),
                contentScale = ContentScale.FillBounds
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = 20.dp)
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Image(
                    painter = painterResource(R.drawable.group_26),
                    contentDescription = "Emergency Instructions",
                    modifier = Modifier
                        .size(200.dp)
                        .clickable {
//                            if (navController.graph.findNode(Screen.FirstAidGuideScreen.route) != null) {
//                                navController.navigate(Screen.FirstAidGuideScreen.route)
//                            }
                            navController.navigate(Screen.FirstAidGuideScreen.route)
                        }
                )

                Spacer(modifier = Modifier.width(10.dp))

                Image(
                    painter = painterResource(R.drawable.hocsocuu),
                    contentDescription = "Learn First Aid",
                    modifier = Modifier
                        .offset(y = -10.dp)
                        .size(300.dp)
                        .clickable {
                            if (navController.graph.findNode("LearnFirstAid") != null) {
                                navController.navigate("LearnFirstAid")
                            }
                        }
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = 230.dp)
                    .padding(horizontal = 16.dp)
            ) {
                Image(
                    painter = painterResource(R.drawable.gioithieu),
                    contentDescription = "Introduction Guide",
                    modifier = Modifier
                        .size(190.dp)
                        .clickable {
                            if (navController.graph.findNode("IntroductionGuide") != null) {
                                navController.navigate("IntroductionGuide")
                            }
                        }
                )
                Spacer(modifier = Modifier.width(20.dp))
                var showDialog by remember { mutableStateOf(false) }

                Image(
                    painter = painterResource(R.drawable.canhanhoa),
                    contentDescription = "Personalization",
                    modifier = Modifier
                        .offset(y = 20.dp)
                        .size(200.dp)
                        .clickable {
                            if (user == null) {
                                showDialog = true
                            } else {
                                if (navController.graph.findNode("Personalization") != null) {
                                    navController.navigate("Personalization")
                                }
                            }
                        }
                )

                if (showDialog) {
                    AlertDialog(
                        onDismissRequest = { showDialog = false },
                        title = { Text("Xin chào!") },
                        text = { Text("Bạn cần đăng ký tài khoản để sử dụng tính năng.") },
                        confirmButton = {
                            TextButton(onClick = { showDialog = false }) {
                                Text("Đóng")
                            }
                        }
                    )
                }

            }
        }
    }


    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Bottom
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .navigationBarsPadding()
                .height(80.dp),
            contentAlignment = Alignment.Center
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
                    contentDescription = "Home Page",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .size(140.dp)
                        .clickable { showDialog = true }
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
                    contentDescription = "Contact",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .size(50.dp)
                        .clickable {
                            if (navController.graph.findNode("ContactScreen") != null) {
                                navController.navigate("ContactScreen")
                            }
                        }
                )

                Spacer(modifier = Modifier.width(10.dp))
                Image(
                    painter = painterResource(R.drawable.hospital),
                    contentDescription = "Maps",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .size(40.dp)
                        .clickable {
                            if (navController.graph.findNode("Maps") != null) {
                                navController.navigate("Maps")
                            }
                        }
                )

                Spacer(modifier = Modifier.width(10.dp))
                Image(
                    painter = painterResource(R.drawable.c),
                    contentDescription = "Profile",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .size(40.dp)
                        .clickable {
                            if (navController.graph.findNode("ProfileScreen") != null) {
                                navController.navigate("ProfileScreen")
                            }
                        }
                )
            }
        }
    }
}
