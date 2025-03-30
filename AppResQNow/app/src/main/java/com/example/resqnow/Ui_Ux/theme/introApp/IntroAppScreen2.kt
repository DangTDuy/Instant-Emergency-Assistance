package com.example.resqnow.Ui_Ux.theme.introApp


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.resqnow.Components.Bắt_đầu_color
import com.example.resqnow.Components.Intro_color
import com.example.resqnow.R

@Composable
fun IntroScreen2(navController: NavController){
    Box(modifier = Modifier.fillMaxSize().background(color = Intro_color)) {

        Image(
            painter = painterResource(R.drawable.logo),
            contentDescription = "Logo",
            modifier = Modifier
                .offset(x = (-43).dp, y = 50.dp) // Đẩy một phần ảnh ra ngoài màn hình
                .size(width = 304.dp, height = 95.dp)
                .graphicsLayer(clip = false) // Không cắt nội dung của ảnh
        )

        Image(painter = painterResource(R.drawable.intro1_1),contentDescription = "Đường kẽ ngang"
            , modifier = Modifier
                .padding(top = 10.dp)
                .fillMaxSize()

        )
        Image(painter = painterResource(R.drawable.ambulance_car),contentDescription = "Xe cứu thương"
            ,modifier = Modifier
                .padding(start = 10.dp, top =320.dp)
                .size(width = 355.69.dp, height = 200.dp)

        )
        Column(modifier = Modifier.fillMaxSize()) {
            Button(
                onClick = { navController.navigate("IntroAppScreen3") }, shape = RoundedCornerShape(14.dp), colors =ButtonDefaults.buttonColors(Color.White),
                modifier = Modifier

                    .padding(top = 700.dp)
                    .size(width = 227.dp, height = 57.dp)
                    .align ( Alignment.CenterHorizontally )

            ) {
                Text(text= "Bắt Đầu", fontSize = 28.sp, color = Bắt_đầu_color, fontWeight = FontWeight.Bold

                )
            }
        }
    }

}
//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun GreetingPreview() {
//    IntroScreen2()
//}