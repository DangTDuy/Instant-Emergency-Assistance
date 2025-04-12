package com.example.resqnow.Ui_Ux.theme.Profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.resqnow.Components.LightPink
import com.example.resqnow.Components.Text_color
import com.example.resqnow.R



@Composable
fun ProfileScreenWithoutAccount(navController: NavController? = null){
    Column(

        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(top = 38.dp)
    ){
            Box() {
                Image(
                    painter = painterResource(R.drawable.profile_top_background),
                    contentDescription = "topbackground",
                    modifier = Modifier
                        .size(width = 411.dp, height = 100.dp)
                )
                Row(modifier = Modifier.fillMaxWidth()) {
                    IconButton(onClick = { }) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Đóng",
                            modifier = Modifier
                                .size(44.dp)
                                .padding(top = 15.dp)
                        )
                    }
                    Text(
                        text = "Trang Cá Nhân",
                        fontSize = 30.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = Text_color,
                        modifier = Modifier
                            .padding(start = 55.dp, top = 25.dp)
                    )
                }
                Box(
                    modifier = Modifier
                        .padding(top = 62.dp)
                        .align(Alignment.Center)
                        .background(color = Color.White, shape = RoundedCornerShape(15.dp))
                        .size(width = 250.dp, height = 81.dp)
                ) {
                    Button(
                        onClick = {},
                        colors = ButtonDefaults.buttonColors(LightPink),
                        modifier = Modifier
                            .padding(start = 58.dp, top = 16.dp)
                            .size(width = 134.dp, height = 49.dp)
                            .border(
                                width = 1.dp,
                                color = Color.Black,
                                shape = RoundedCornerShape(10.dp)
                            )
                            .background(color = LightPink, shape = RoundedCornerShape(10.dp))
                    ) {
                        Box() {
                            Text(
                                text = "Đăng Ký",
                                fontSize = 20.sp,

                                fontWeight = FontWeight.ExtraBold,
                                color = Color.Black,
                                style = TextStyle(
                                    drawStyle = Stroke(width = 4f)
                                ),
                                modifier = Modifier
                                    .align(Alignment.Center)
                            )
                            Text(
                                text = "Đăng Ký",
                                fontSize = 20.sp,

                                fontWeight = FontWeight.ExtraBold,
                                color = Color.Red,
                                modifier = Modifier
                                    .align(Alignment.Center)
                            )
                        }
                    }
                }
            }

            Text(
                text = "Thông tin cơ bản ",
                fontSize = 18.sp,
                fontWeight = FontWeight.Black,
                modifier = Modifier
                    .padding(start = 5.dp, top = 10.dp)
            )
            Text(
                text = "Nhập thông tin cá nhân của bạn ở đây",
                fontSize = 13.sp,
                fontWeight = FontWeight.ExtraBold,
                modifier = Modifier
                    .padding(start = 5.dp, top = 2.dp)
            )
        Spacer(modifier = Modifier.height(10.dp))

            Box(modifier = Modifier.fillMaxWidth()) {
                Box(
                    modifier = Modifier
                        .size(width = 320.dp, height = 350.dp)
                        .padding(start = 78.dp, top = 25.dp)
                        .border(
                            width = 1.dp,
                            color = Color.Black,
                            shape = RoundedCornerShape(15.dp)
                        )
                ) {
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = "Họ và Tên ",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier
                                .padding(start = 24.dp, top = 17.dp)
                        )
                        Text(
                            text = "",
                            fontSize = 13.sp,
                            color = Color.Red,
                            fontWeight = FontWeight.ExtraBold,
                            modifier = Modifier
                                .padding(start = 100.dp, top = 8.dp)
                        )
                    }
                    Divider(
                        color = Color.Gray,
                        thickness = 1.dp,
                        modifier = Modifier
                            .width(200.dp)
                            .padding(start = 40.dp, top = 75.dp)
                    )
                    Column(modifier = Modifier.padding(top = 10.dp)) {
                        Text(
                            text = "Số di động",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier
                                .padding(start = 24.dp, top = 100.dp)
                        )
                        Text(
                            text = "", fontSize = 13.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = Color.Red, modifier = Modifier
                                .padding(start = 150.dp, top = 5.dp)
                        )
                    }
                    Divider(
                        color = Color.Gray,
                        thickness = 1.dp,
                        modifier = Modifier
                            .width(200.dp)
                            .padding(start = 40.dp, top = 156.dp)
                    )
                    Column(modifier = Modifier.padding(top = 25.dp)) {
                        Text(
                            text = "Giới Tính",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier
                                .padding(start = 24.dp, top = 164.dp)
                        )
                        Text(
                            text = "", fontSize = 13.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = Color.Red, modifier = Modifier
                                .padding(start = 190.dp, top = 6.dp)
                        )
                    }
                    Divider(
                        color = Color.Gray,
                        thickness = 1.dp,
                        modifier = Modifier
                            .width(200.dp)
                            .padding(start = 40.dp, top = 240.dp)
                    )
                    Column(modifier = Modifier.padding(top = 25.dp)) {
                        Text(
                            text = "Tuổi",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier
                                .padding(start = 25.dp, top = 246.dp)
                        )
                        Text(
                            text = "", fontSize = 13.sp,
                            color = Color.Red,
                            fontWeight = FontWeight.ExtraBold,
                            modifier = Modifier
                                .padding(start = 203.dp, top = 2.dp)
                        )
                    }
                }

        }
//
//        //Đăng xuất
//        Column (modifier = Modifier.fillMaxWidth()){
//            Button(
//                onClick = {},
//                colors = ButtonDefaults.buttonColors(Color.White),
//                modifier = Modifier
//                    .align(Alignment.CenterHorizontally)
//                    .padding(top = 17.dp)
//                    .border(width = 1.dp, color = Color.Red, shape = RoundedCornerShape(5.dp))
//                    .size(width = 150.dp, height = 35.dp)
//            ) {
//                Row(
//                    verticalAlignment = Alignment.CenterVertically,
//                    modifier = Modifier.fillMaxSize(),
//                ) {
//                    Image(
//                        painter = painterResource(id = R.drawable.logout),
//                        contentDescription = "Đăng xuất",
//                        modifier = Modifier
//                            .size(14.dp)
//                            .weight(1f)
//                    )
//                    Text(
//                        text = "Đăng xuất",
//                        fontSize = 15.sp,
//                        fontWeight = FontWeight.Bold,
//                        color = Color.Red,
//                        modifier = Modifier
//                            .weight(3f)
//                            .padding(start = 1.dp)
//                    )
//                }
//            }
//        }
        Row() {
            Column() {
                Text(
                    text = "Cá Nhân Hóa", fontSize = 18.sp, fontWeight = FontWeight.Black,
                    modifier = Modifier
                        .padding(start = 29.dp, top = 20.dp)
                )
                Text(
                    text = "Tra cứu các loại bệnh theo độ tuổi của bạn",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.ExtraBold,
                    modifier = Modifier
                        .padding(start = 29.dp, top = 5.dp)
                )
            }

                Image(
                    painter = painterResource(id = R.drawable.icon_personal),
                    contentDescription = "Cá nhân hóa",
                    modifier = Modifier
                        .padding(start = 45.dp, top = 20.dp)
                        .size(44.dp)
                        .border(width = 2.dp, color = Color.Red, shape = RoundedCornerShape(5.dp))


                )


        }
        Box(){
            Image(painter = painterResource(id = R.drawable.new_nav_bar), contentDescription = "navbar"
                , modifier = Modifier
                    .padding(top = 20.dp,start = 0.dp, end = 0.dp)
                    .size(width = 600.dp, height = 120.dp)
            )
            Row(){
               Image(painter = painterResource(id = R.drawable.home), contentDescription = "home",
                   modifier = Modifier
                       .padding(start = 9.dp, top = 78.dp)
                       .size(width = 39.dp , height = 37.dp)
                   )
                Image(painter = painterResource(id = R.drawable.a), contentDescription = "home",
                    modifier = Modifier
                        .padding(start = 70.dp, top = 78.dp)
                        .size(width = 39.dp , height = 37.dp)
                )
                Image(painter = painterResource(id = R.drawable.hospital), contentDescription = "home",
                    modifier = Modifier
                        .padding(start = 60.dp, top = 78.dp)
                        .size(width = 37.dp , height = 35.dp)
                )
                Image(painter = painterResource(id = R.drawable.c), contentDescription = "home",
                    modifier = Modifier
                        .padding(start = 47.dp, top = 65.dp)
                        .size(width = 39.dp , height = 37.dp)
                )

            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewIntroScreen3() {
    ProfileScreenWithoutAccount()
}


