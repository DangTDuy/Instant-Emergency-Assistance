package com.example.resqnow.Ui_Ux.theme.FirstAidGuide.stroke

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.resqnow.R
import com.example.resqnow.Ui_Ux.theme.FirstAidGuide.MenuFirstAidGuide.FirstAidGuideScreen
import com.example.resqnow.Ui_Ux.theme.FirstAidGuide.MenuFirstAidGuide.bottomBar
import com.example.resqnow.Ui_Ux.theme.Router.Screen

@Composable
fun signsOfStroke(navController: NavController){
    Scaffold (
        containerColor = Color.White,
        bottomBar = {
            bottomBar(navController = navController)
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding)
        ) {
            Row(
                modifier = Modifier
                    .padding(horizontal = 4.dp)
            ) {
                Text(
                    text = "Dấu hiệu đột quỵ",
                    fontSize = 25.sp,
                    fontWeight = FontWeight.ExtraBold,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                )

                Spacer(modifier = Modifier.weight(1f))


                Image(
                    painter = painterResource(id = R.drawable.exit),
                    contentDescription = "Logo",
                    modifier = Modifier
                        .size(35.dp)
                        .clickable {
                            navController.popBackStack()
                        }
                )
            }
            Spacer(modifier = Modifier.height(15.dp))

            Card(
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .size(width = 391.dp, height = 500.dp)
                    .border(width = 1.dp, shape = RoundedCornerShape(10.dp), color = Color.Black),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                shape = RoundedCornerShape(10.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Card(
                        modifier = Modifier
                            .padding(18.dp)
                            .width(354.dp)
                            .height(78.dp),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFF5CEA8))
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxSize()
                            ,verticalAlignment = Alignment.CenterVertically
                        ){
                            Image(painter = painterResource(id = R.drawable.face), contentDescription = "Logo"
                                ,modifier = Modifier
                                    .padding(horizontal = 4.dp)
                                    .size(width =92.dp,height = 50.dp)
                                    .align  (Alignment.CenterVertically)
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Column (
                                modifier = Modifier.fillMaxSize()
                                    .padding (vertical = 10.dp),
                                verticalArrangement = Arrangement.Top,
                                horizontalAlignment = Alignment.Start
                            ){
                                Text(text = "Gương mặt", fontSize = 20.sp, fontWeight = FontWeight.SemiBold
                                    ,modifier = Modifier

                                )
                                Spacer(modifier = Modifier.height(10.dp))

                                Text(text = "mặt bị xéo một bên", fontSize = 17.sp, fontWeight = FontWeight.W400
                                    ,modifier = Modifier

                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(5.dp))
                    Card(
                        modifier = Modifier
                            .padding(18.dp)
                            .width(370.dp)
                            .height(95.dp),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFF5CEA8))
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxSize()
                            ,verticalAlignment = Alignment.CenterVertically
                        ){
                            Image(painter = painterResource(id = R.drawable.checkarm), contentDescription = "Logo"
                                ,modifier = Modifier
                                    .padding(horizontal = 10.dp)
                                    .size(width =70.dp,height = 70.dp)
                                    .align  (Alignment.CenterVertically)
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Column (
                                modifier = Modifier.fillMaxSize()
                                    .padding (vertical = 10.dp),
                                verticalArrangement = Arrangement.Top,
                                horizontalAlignment = Alignment.Start
                            ){
                                Text(text = "Cánh tay", fontSize = 20.sp, fontWeight = FontWeight.SemiBold
                                    ,modifier = Modifier

                                )
                                Spacer(modifier = Modifier.height(3.dp))

                                Text(text = "nạn nhân không thể nhấc cả hai tay" +
                                        " ngang vai và giữ trong 10s", fontSize = 15.sp, fontWeight = FontWeight.W400
                                    ,modifier = Modifier

                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(7.dp))
                    Card(
                        modifier = Modifier
                            .padding(18.dp)
                            .width(354.dp)
                            .height(90.dp),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFF5CEA8))
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxSize()
                            ,verticalAlignment = Alignment.CenterVertically
                        ){
                            Image(painter = painterResource(id = R.drawable.spech), contentDescription = "Logo"
                                ,modifier = Modifier
                                    .padding(horizontal = 10.dp)
                                    .size(width =70.dp,height = 70.dp)
                                    .align  (Alignment.CenterVertically)
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Column (
                                modifier = Modifier.fillMaxSize()
                                    .padding (vertical = 10.dp),
                                verticalArrangement = Arrangement.Top,
                                horizontalAlignment = Alignment.Start
                            ){
                                Text(text = "Lời nói", fontSize = 20.sp, fontWeight = FontWeight.SemiBold
                                    ,modifier = Modifier

                                )
                                Spacer(modifier = Modifier.height(3.dp))

                                Text(text = "khó nói chuyện ,nói ú ớ , không thành lời", fontSize = 15.sp, fontWeight = FontWeight.W400
                                    ,modifier = Modifier

                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(2.dp))
                    Card(
                        modifier = Modifier
                            .padding(5.dp)
                            .width(354.dp)
                            ,
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFF5CEA8))
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxSize()
                            ,verticalAlignment = Alignment.CenterVertically
                        ){
                            Image(painter = painterResource(id = R.drawable.time), contentDescription = "Logo"
                                ,modifier = Modifier
                                    .padding(horizontal = 10.dp)
                                    .size(width =70.dp,height = 70.dp)
                                    .align  (Alignment.CenterVertically)
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Column (
                                modifier = Modifier.fillMaxSize()
                                    .padding (vertical = 2.dp),
                                verticalArrangement = Arrangement.Top,
                                horizontalAlignment = Alignment.Start
                            ){
                                Text(text = "Thời gian", fontSize = 20.sp, fontWeight = FontWeight.SemiBold
                                    ,modifier = Modifier

                                )
                                Spacer(modifier = Modifier.height(1.dp))

                                Text(text = "Nếu có dấu hiệu ngưng tim gọi 115 và ngay lập tức thực hiện thao tác hồi sinh tim phổi",
                                    fontSize = 14.sp, fontWeight = FontWeight.W400
                                    ,modifier = Modifier

                                )
                            }
                        }
                    }
                }
            }//end card
            Spacer(modifier = Modifier.height(17.dp))

            Button(onClick = {navController.navigate(Screen.Stroke.route)}
                , shape = RoundedCornerShape(10.dp)
                , colors = ButtonDefaults.buttonColors(Color.White)

            ,modifier = Modifier
                    .padding(horizontal = 85.dp)
                    .size(width = 242.dp, height = 47.dp)
                    .border(width = 2.dp, shape = RoundedCornerShape(10.dp), color = Color.Red)
                    ,contentPadding = PaddingValues(0.dp)
            )
            {
                Row(modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 5.dp)
                    ,verticalAlignment = Alignment.CenterVertically

                ) {
                    Text(
                        buildAnnotatedString {
                            withStyle(style = SpanStyle(color = Color(0xFFF14D42), fontWeight = FontWeight.Bold)) {
                                append("Phương pháp : ")
                            }
                            withStyle(style = SpanStyle(color = Color.Black,fontWeight = FontWeight.Bold)) {
                                append("hồi sinh tim phổi")
                            }
                        }
                        ,fontSize = 14.sp


                    )
                }
            }

        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MyScreenPreview() {
    val navController = rememberNavController()
    signsOfStroke(navController)
}