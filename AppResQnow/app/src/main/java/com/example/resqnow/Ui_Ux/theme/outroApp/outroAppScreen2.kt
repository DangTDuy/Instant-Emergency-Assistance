package com.example.resqnow.Ui_Ux.theme.outroApp

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.resqnow.Components.Intro_color
import com.example.resqnow.R
import com.example.resqnow.Ui_Ux.theme.Router.Screen
import kotlinx.coroutines.delay


@Composable
fun OutroScreen2 (navController: NavController){
    var isVisible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        isVisible = true
        delay(500) // Chờ animation chạy xong
        isVisible = false // Ẩn xe cứu thương trước khi chuyển màn hình
        delay(200)
        navController.navigate(Screen.ScreenOutro3.route) {
            popUpTo(Screen.ScreenOutro1.route) { inclusive = true }
        }
    }
    Box(modifier = Modifier.fillMaxSize().background(color = Intro_color)) {
        Image(
            painter = painterResource(R.drawable.intro1_1),
            contentDescription = "Đường kẽ ngang",
            modifier = Modifier
                .padding(top = 10.dp)
                .fillMaxSize()

        )
        AnimatedVisibility(
            visible = isVisible,
            enter = slideInHorizontally(initialOffsetX = { -100 }) + fadeIn(),
            exit = slideOutHorizontally(targetOffsetX = { 100 }) + fadeOut()
        ){
        Image(
            painter = painterResource(R.drawable.ambulance_car),
            contentDescription = "Xe cứu thương",
            modifier = Modifier
                .offset(x = (-20).dp, y = 350.dp)
                .size(width = 355.69.dp, height = 200.dp)

        )
    }
    }
}

//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun PreviewIntroScreen4() {
//    OutroScreen1()
//}
