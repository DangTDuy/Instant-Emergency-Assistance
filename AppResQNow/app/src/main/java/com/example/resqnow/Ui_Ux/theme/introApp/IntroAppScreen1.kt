package com.example.resqnow.Ui_Ux.theme.common

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.Image
import com.example.resqnow.R
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.resqnow.Components.Intro_color
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.resqnow.Ui_Ux.theme.Navigation.Screen
import kotlinx.coroutines.delay
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally


// Intro bắt đầu ứng dụng


@Composable
fun IntroScreen1 (navController: NavController){
    var isVisible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {

        isVisible = true // Ẩn xe trước khi chuyển màn hình
        delay(200) // Đợi hiệu ứng hoàn tất
        delay(500)
        navController.navigate(Screen.ScreenIntro2.route)
    }
    Box(modifier = Modifier.fillMaxSize().background(color = Intro_color)) {
        Image(painter = painterResource(R.drawable.intro1_1),contentDescription = "Đường kẽ ngang"
            , modifier = Modifier
                .padding(top = 10.dp)
                .fillMaxSize()

        )
        AnimatedVisibility(
            visible = isVisible,
            enter = slideInHorizontally(initialOffsetX = { -100 }) + fadeIn(),
            exit = slideOutHorizontally(targetOffsetX = { 100 }) + fadeOut()
        ) {
            Image(
                painter = painterResource(R.drawable.ambulance_withoutbackground),
                contentDescription = "Xe cứu thương",
                modifier = Modifier
                    .padding(start = 0.dp, top = 350.dp)
                    .size(width = 87.dp, height = 200.dp)

            )


        }
    }
}


//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun GreetingPreview() {
//    IntroScreen()
//}