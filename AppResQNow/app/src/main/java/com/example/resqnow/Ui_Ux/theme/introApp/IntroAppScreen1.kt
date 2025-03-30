package com.example.resqnow.Ui_Ux.theme.introApp

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
import androidx.compose.ui.unit.dp


// Intro bắt đầu ứng dụng


@Composable
fun IntroScreen (){
    Box(modifier = Modifier.fillMaxSize().background(color = Intro_color)) {
        Image(painter = painterResource(R.drawable.intro1_1),contentDescription = "Đường kẽ ngang"
            , modifier = Modifier
                .padding(top = 10.dp)
                .fillMaxSize()

        )
        Image(painter = painterResource(R.drawable.ambulance_withoutbackground),contentDescription = "Xe cứu thương"
            ,modifier = Modifier
                .padding(start = 0.dp, top =350.dp)
                .size(width = 87.dp, height = 200.dp)

        )



    }
}


//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun GreetingPreview() {
//    IntroScreen()
//}