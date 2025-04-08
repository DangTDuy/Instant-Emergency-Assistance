package com.example.resqnow.Ui_Ux.theme.SignIn




import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.example.resqnow.R
import com.example.resqnow.Components.background
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.ui.Alignment
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.withStyle
import androidx.navigation.NavController
import com.example.resqnow.Data.Api_and_Firebase.FireBaseGoogle.GoogleAuthUiClient

@Composable
fun SignInSuccessScreen(navController: NavController, googleAuthUiClient: GoogleAuthUiClient) {
    Box (
        modifier = Modifier
            .fillMaxSize()
            .background(background)

    ) {
        Image(
            painter = painterResource(R.drawable.top_background1),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter)
                .height(365.dp)
        )
        Image(
            painter = painterResource(R.drawable.home_page),
            contentDescription = null,
            modifier = Modifier
                .size(150.dp)
                .padding(start = 9.dp, top = 35.dp)
                .zIndex(1f),
        )
        Text(
            text = "Đăng Nhập Thành Công",
            fontSize = 25.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier
                .padding(start = 65.dp,top = 365.dp)
            )
        Button(
            onClick = {navController.navigate("HomeScreen1")},
            colors = ButtonDefaults.buttonColors(Color(0xFFF5F5DC)),
            modifier = Modifier
                .padding(start = 95.dp, top = 420.dp)
                .border(3.dp, Color.Red, shape = RoundedCornerShape(14.dp))
                .size(width = 200.dp, height = 47.dp)
        ){
            Text(text = "Trang Chủ", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.Black)
        }


    }
}

