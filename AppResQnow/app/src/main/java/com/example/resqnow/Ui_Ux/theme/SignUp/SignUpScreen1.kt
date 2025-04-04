package com.example.resqnow.Ui_Ux.theme.SignUp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
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
import androidx.compose.ui.Alignment
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.withStyle

@Composable
fun SignupScreen() {
    var passwordVisible by rememberSaveable { mutableStateOf(false) }
    var phoneNumber by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(background)

    ) {
        Image(
            painter = painterResource(R.drawable.top_background1),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()

                .height(365.dp)
            ,contentScale = ContentScale.Crop

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
            text = "Đăng Nhập",
            fontSize = 40.sp,
            fontWeight = FontWeight.ExtraBold,
            modifier = Modifier
                .padding(top = 297.dp, start = 0.dp)
        )


        OutlinedTextField(
            value = phoneNumber,
            onValueChange = { phoneNumber = it },
            modifier = Modifier
                .width(365.dp)
                .height(65.dp)
                .offset(x = 23.dp, y = 400.dp)
                .padding(end = 5.dp),
            leadingIcon = {
                Image(
                    painter = painterResource(R.drawable.solar_phone_outline),
                    contentDescription = null,
                    modifier = Modifier
                        .size(24.dp)
                        .padding(start = 6.dp)
                )
            },
            label = {
                Text(
                    text = "Số điện thoại",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold
                )
            },
            placeholder = {Text(text = "Nhập số điện thoại của bạn ")},
            shape = RoundedCornerShape(15.dp),
        )

        Spacer(modifier = Modifier.width(16.dp))


        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            modifier = Modifier
                .width(365.dp)
                .height(65.dp)
                .offset(x = 23.dp, y = 470.dp)
                .padding(end = 5.dp),
            leadingIcon = {
                Image(
                    painter = painterResource(R.drawable.solar_lock_password_broken),
                    contentDescription = null,
                    modifier = Modifier
                        .size(24.dp)
                        .padding(start = 6.dp)
                )
            },
            trailingIcon = {
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(
                        imageVector = if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                        contentDescription = "Toggle password visibility"
                    )
                }
            },
            label = {
                Text(
                    text = "Mật khẩu",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold
                )
            },
            placeholder = {Text(text = "Nhập mật khẩu của bạn ")},
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            shape = RoundedCornerShape(15.dp),
        )


        Button(
            onClick = {},
            modifier = Modifier
                .offset(x = 260.dp, y = 550.dp)
                .size(width = 127.dp, height = 37.dp)
                .clip(RoundedCornerShape(15.dp)),
            colors = ButtonDefaults.buttonColors(
                Color(0xFFEF291E) // Màu nền của button
            )
        ) {
            Text(text = "Tiếp tục", fontSize = 17.sp, fontWeight = FontWeight.ExtraBold, color = Color.White)
        }

        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 630.dp)
        ) {
            // Facebook button
            Button(
                onClick = {},
                modifier = Modifier
                    .size(width = 150.dp, height = 49.dp)
                    .border(3.dp, Color.Red, shape = RoundedCornerShape(15.dp)),
                colors = ButtonDefaults.buttonColors(Color(0xFFF5F5DC))
            ) {
                Row(
                    modifier = Modifier.fillMaxSize()
                        .clip(RoundedCornerShape(15.dp)),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(R.drawable.logos_facebook),
                        contentDescription = null,
                        modifier = Modifier
                            .size(38.dp)
                            .padding(start = 0.dp)
                    )
                    Text(
                        text = "Facebook",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                }
            }
            Spacer(modifier = Modifier.width(16.dp))

            // Google button
            Button(
                onClick = {},
                modifier = Modifier
                    .size(width = 150.dp, height = 49.dp)
                    .border(3.dp, Color.Red, shape = RoundedCornerShape(15.dp)),
                colors = ButtonDefaults.buttonColors(Color(0xFFF5F5DC))
            ) {
                Row(
                    modifier = Modifier.fillMaxSize()
                        .clip(RoundedCornerShape(15.dp)),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(R.drawable.logo_google),
                        contentDescription = null,
                        modifier = Modifier
                            .size(39.dp)
                            .padding(start = 0.dp)
                    )
                    Text(
                        text = "Google",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                }
            }
        }

        // Register link
        Row(modifier = Modifier.fillMaxSize(), horizontalArrangement = Arrangement.Center) {
            Text(
                modifier = Modifier.padding(top = 700.dp),
                text = buildAnnotatedString {
                    append("Bạn là người mới? ")
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append("Đăng ký")
                    }
                }
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewSignupScreen() {
    SignupScreen()
}
