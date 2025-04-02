package com.example.resqnow.Ui_Ux.theme.outroApp

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
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

@Composable
fun OutroScreen3 (navController: NavController ){
    var isVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        isVisible = true

    }
    AnimatedVisibility(
        visible = isVisible,
        enter = fadeIn() + scaleIn(initialScale = 0.9f),
        exit = fadeOut() + scaleOut(targetScale = 1.1f)

    ) {
        Box(modifier = Modifier.fillMaxSize().background(color = Intro_color)) {
            Image(
                painter = painterResource(R.drawable.intro1_1),
                contentDescription = "Đường kẽ ngang",
                modifier = Modifier
                    .padding(top = 10.dp)
                    .fillMaxSize()

            )
            Image(
                painter = painterResource(R.drawable.ambulance_car),
                contentDescription = "Xe cứu thương",
                modifier = Modifier
                    .offset(x = 222.dp, y = 350.dp)
                    .size(width = 355.69.dp, height = 200.dp)

            )
        }

    }
    LaunchedEffect(isVisible) {
        if (isVisible) {
            kotlinx.coroutines.delay(300) // Đợi 1 giây rồi chuyển màn hình
            navController.navigate("HomeScreen1") {
                popUpTo("OutroScreen3") { inclusive = true } // Xóa màn Outro khỏi stack
            }
        }
    }
}

//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun PreviewIntroScreen5() {
//    OutroScreen3()
//}
