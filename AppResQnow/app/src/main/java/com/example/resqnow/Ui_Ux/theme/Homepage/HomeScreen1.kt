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
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.modifier.modifierLocalConsumer
import com.example.resqnow.Components.borderBackground
import com.example.resqnow.Components.boxInScafold
import kotlin.math.cos
import kotlin.math.sin

val gradientBrush = Brush.linearGradient(
    colors = listOf(Color(0xFFFA382D), Color(0xFF94211A))
)


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun HomePage1(navController: NavController, googleAuthUiClient: GoogleAuthUiClient) {
    //personalization
    var showDialog by remember { mutableStateOf(false) }


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
            .background(Color.White)
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

            MovingChatbotImage()

            Spacer(modifier = Modifier.width(2.dp))



            Box(
                modifier = Modifier
                    .padding(horizontal = 5.dp)
                    .weight(1f)
                    .height(50.dp)
                    .background(brush = gradientBrush, shape = RoundedCornerShape(15.dp)),
                contentAlignment = Alignment.Center
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
                    delay(3000)
                    currentImageIndex = 1
                    delay(3000)
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
                                animationSpec = tween(durationMillis = 3000),
                                initialOffsetX = { fullWidth -> fullWidth } // Slide từ phải vào
                            ) with slideOutHorizontally(
                                animationSpec = tween(durationMillis = 3000),
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
                                currentImageIndex =
                                    if (currentImageIndex > 0) currentImageIndex - 1 else images.size - 1
                            }
                    )

                    Image(
                        painter = painterResource(R.drawable.phai),
                        contentDescription = "Next Image",
                        modifier = Modifier
                            .size(40.dp)
                            .align(Alignment.CenterEnd)
                            .clickable {
                                currentImageIndex =
                                    if (currentImageIndex < images.size - 1) currentImageIndex + 1 else 0
                            }
                    )
                }
            }
        }
        var showHomePage by remember { mutableStateOf(false) }
        if (showHomePage) {
            AlertDialog(
                onDismissRequest = { showHomePage = false },
                title = { Text("Xin chào bạn tôi !!!") },
                text = { Text("Bạn đang ở trang chủ mà người anh em.") },
                confirmButton = {
                    TextButton(onClick = { showHomePage = false }) {
                        Text("Đóng")
                    }
                }
            )
        }
        Scaffold(
            containerColor = Color.White,
            bottomBar = {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .navigationBarsPadding()
                        .fillMaxWidth()
                        .height(105.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.firstaid_navbar),
                        contentDescription = "Logo",
                        modifier = Modifier.fillMaxSize()
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(
                            onClick = { navController.navigate(Screen.HomePage1.route) },
                            modifier = Modifier
                                .padding(bottom = 4.dp)
                                .size(60.dp)
                        ) {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.home),
                                    contentDescription = "Logo",
                                    modifier = Modifier.size(41.dp, 39.dp)
                                        .clickable { showHomePage = true }
                                )
                            }
                        }
                        IconButton(
                            onClick = { navController.navigate(Screen.ContactScreen.route) },
                            modifier = Modifier
                                .padding(top = 40.dp)
                                .size(60.dp)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.a),
                                contentDescription = "Logo",
                                alignment = Alignment.Center,
                                modifier = Modifier.size(37.dp, 32.dp)
                            )
                        }
                        IconButton(
                            onClick = {
                                if (navController.graph.findNode("Maps") != null) {
                                    navController.navigate("Maps")
                                }
                            },
                            modifier = Modifier
                                .padding(top = 40.dp)
                                .size(60.dp)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.hospital),
                                contentDescription = "Logo",
                                alignment = Alignment.Center,
                                modifier = Modifier.size(35.dp)
                            )
                        }
                        IconButton(
                            onClick = { navController.navigate(Screen.ProfileScreen.route) },
                            modifier = Modifier
                                .padding(top = 40.dp)
                                .size(60.dp)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.c),
                                contentDescription = "Logo",
                                alignment = Alignment.Center,
                                modifier = Modifier.size(35.dp)
                            )
                        }
                    }
                }
            }
        ) {paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        top = 4.dp, // Hoặc 0.dp nếu muốn sát lề trên
                        start = paddingValues.calculateStartPadding(LayoutDirection.Ltr),
                        end = paddingValues.calculateEndPadding(LayoutDirection.Ltr),
                        bottom = paddingValues.calculateBottomPadding()
                    )
                    .clip(RoundedCornerShape(15.dp))
                    .background(boxInScafold)
                ,contentAlignment = Alignment.Center

            ) {
                MovingBackGroundHome()
                //introduceApp
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .align(Alignment.TopStart)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.introduction_image),
                        contentDescription = null
                        ,modifier = Modifier
                            .size(width = 130.dp,height = 130.dp)
                            .clickable {
                                if (navController.graph.findNode("IntroductionGuide") != null) {
                                    navController.navigate("IntroductionGuide")
                                }
                            }
                    )
                    Text(text = "Giới Thiệu", fontWeight = FontWeight.Black
                        ,textAlign = TextAlign.Center
                        ,color = Color.White
                        , fontSize = 15.sp
                    )
                }
                //FirstAidGuide
                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .size(width = 130.dp, height = 130.dp)

                ) {

                        Image(
                            painter = painterResource(id = R.drawable.firs_aid_guide),
                            contentDescription = null, modifier = Modifier
                                .size(width = 130.dp, height = 130.dp)
                                .clickable {
                                        navController.navigate(Screen.FirstAidGuideScreen.route)
                                }
                        )
                        Text(
                            text = "Mở sổ tay\nsơ cứu ",
                            fontWeight = FontWeight.Black,
                            textAlign = TextAlign.Center,
                            color = Color.White,
                            fontSize = 15.sp,
                            modifier = Modifier
                                .padding(end=20.dp)
                                .align(Alignment.Center)
                        )

                }
                //LearnFirstAid
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .size(width = 130.dp, height = 130.dp)

                ) {

                    Image(
                        painter = painterResource(id = R.drawable.learnfirstaid),
                        contentDescription = null, modifier = Modifier
                            .size(width = 130.dp, height = 130.dp)
                            .clickable{
                                if (navController.graph.findNode(Screen.LearnFirstAidScreen.route) != null) {
                                    navController.navigate(Screen.LearnFirstAidScreen.route)
                                }
                            }
                    )
                    Text(
                        text = "Học sơ cứu\nnào",
                        fontWeight = FontWeight.Black,
                        textAlign = TextAlign.Center,
                        color = Color.White,
                        fontSize = 15.sp,
                        modifier = Modifier
                            .padding(end=20.dp)
                            .align(Alignment.Center)
                    )

                }

                //Personalization
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.personal),
                        contentDescription = null
                        ,modifier = Modifier
                            .size(width = 130.dp,height = 130.dp)
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
                    Text(text = "Cá nhân\n hóa", fontWeight = FontWeight.Black
                        ,textAlign = TextAlign.Center
                        ,color = Color.White
                        , fontSize = 15.sp,
                        modifier = Modifier
                                .padding(top=5.dp)

                    )
                }
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
}
@Composable
fun MovingChatbotImage() {
    val infiniteTransition = rememberInfiniteTransition()
    val offsetY by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 8f, // Độ cao di chuyển lên xuống
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    Image(
        painter = painterResource(R.drawable.chatbotaipng),
        contentDescription = "chatBotAI",
        modifier = Modifier
            .offset(y = offsetY.dp) // <- Đây là hiệu ứng chính
            .size(41.dp)
            .clickable {}
            .padding(start = 5.dp)
    )
}
@Composable
fun MovingBackGroundHome() {
    //hệu ứng lỗi
//    // Tạo hiệu ứng xoay mỗi lần 90 độ trong thời gian ngắn
//    val rotationAngle by rememberInfiniteTransition().animateFloat(
//        initialValue = 0f,
//        targetValue = 90f, // Xoay 90 độ
//        animationSpec = infiniteRepeatable(
//            animation = tween(durationMillis = 200, easing = LinearEasing)
//        )
//    )

    Image(
        painter = painterResource(id = R.drawable.image_home),
        contentDescription = "Logo",
        modifier = Modifier
            .size(width = 320.dp, height = 290.dp)

    )
}

