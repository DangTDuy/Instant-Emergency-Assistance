package com.example.resqnow.Ui_Ux.theme.personalization.Child


import androidx.compose.foundation.Image
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.resqnow.Data.Api_and_Firebase.DataStore.readUserData
import com.example.resqnow.R


@Composable
fun ChildPreventivemeasures(navController: NavController){
    val context = LocalContext.current
    val ageState = remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        val userData = readUserData(context)
        ageState.value = userData.age
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 60.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = "ResQnow",
                fontSize = 25.sp,
                fontStyle = FontStyle.Italic,
                fontWeight = FontWeight.Black
            );

        }
        Spacer(modifier = Modifier.height(5.dp))
        Box(

            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
        ){

            Image(painter = painterResource(id = R.drawable.headbar_firstaid), contentDescription = "Logo"

                ,contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(top = 20.dp)
                    .fillMaxWidth()
                    .height( 90.27.dp)
            )
            Image(painter = painterResource(id = R.drawable.canhanhoaicon), contentDescription = "Logo"
                ,alignment = Alignment.Center
                ,modifier = Modifier
                    .padding(start = 57.dp)
                    .size(width = 93.dp, height = 80.dp)
            )
            Text(text = "Độ tuổi hiện tại của bạn ${ageState.value}",
                fontSize = 18.sp,
                textAlign = TextAlign.Center,
                color = Color.White,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Center)
                    .padding(start  = 120.dp)
            )
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 165.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.thieunien),
            contentDescription = "thieunien",
            modifier = Modifier
                .size(width = 200.dp, height = 150.dp)
                .align(Alignment.CenterHorizontally)
        )
        Image(
            painter = painterResource(id = R.drawable.thieunien1),
            contentDescription = "",
            modifier = Modifier
                .size(width = 370.dp, height = 170.dp)
                .offset(y = -100.dp)
                .align(Alignment.CenterHorizontally)
        )
        Image(
            painter = painterResource(id = R.drawable.line),
            contentDescription = "",
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .size(width = 250.dp, height = 25.dp)
                .offset(y = - 150.dp)
        )
        Text(
            text = "    Một số phương pháp\nphòng bệnh ở tuổi dậy thì",
            fontSize = 19.sp,
            fontWeight = FontWeight.Black,
            color = Color.Red,
            modifier = Modifier
                .padding(start = 16.dp)
                .align(Alignment.CenterHorizontally)
                .offset(y = - 140.dp)
        )

        LazyColumn(modifier = Modifier
            .fillMaxWidth()
            .offset(y = -120.dp)
            .padding(top = 10.dp, bottom = 10.dp)) {
            item {
                Box(modifier = Modifier.fillMaxWidth()) {
                    Box(
                        modifier = Modifier
                            .align(Alignment.CenterStart)
                            .offset(y = -50.dp)
                            .padding(start = 16.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.giutamlythoaimai),
                            contentDescription = "",
                            modifier = Modifier.size(250.dp)
                        )
                        Image(
                            painter = painterResource(id = R.drawable.xemthem),
                            contentDescription = "",
                            modifier = Modifier
                                .size(50.dp)
                                .offset(x = 140.dp, y = 120.dp)
                        )
                    }
                }

                Box(modifier = Modifier.fillMaxWidth()) {
                    Box(
                        modifier = Modifier
                            .align(Alignment.CenterEnd)
                            .offset(y = -190.dp)
                            .padding(end = 16.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.chedosinhhoatkhoahoc),
                            contentDescription = "",
                            modifier = Modifier.size(250.dp)
                        )
                        Image(
                            painter = painterResource(id = R.drawable.xemthem),
                            contentDescription = "",
                            modifier = Modifier
                                .size(50.dp)
                                .offset(x = 140.dp, y = 120.dp)
                        )
                    }
                }




                Box(modifier = Modifier.fillMaxWidth().offset(y = -280.dp)) {
                    Box(
                        modifier = Modifier
                            .align(Alignment.CenterStart)
                            .offset(y = -50.dp)
                            .padding(start = 16.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.giuvesinhcanhan),
                            contentDescription = "",
                            modifier = Modifier.size(250.dp)
                        )
                        Image(
                            painter = painterResource(id = R.drawable.xemthem),
                            contentDescription = "",
                            modifier = Modifier
                                .size(50.dp)
                                .offset(x = 140.dp, y = 120.dp)
                        )
                    }
                }

                Box(modifier = Modifier.fillMaxWidth().offset(y = -280.dp)) {
                    Box(
                        modifier = Modifier
                            .align(Alignment.CenterEnd)
                            .offset(y = -190.dp)
                            .padding(end = 16.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.tangcuongvandong),
                            contentDescription = "",
                            modifier = Modifier.size(250.dp)
                        )
                        Image(
                            painter = painterResource(id = R.drawable.xemthem),
                            contentDescription = "",
                            modifier = Modifier
                                .size(50.dp)
                                .offset(x = 140.dp, y = 120.dp)
                        )
                    }
                }

                Box(modifier = Modifier.fillMaxWidth().offset(y = -560.dp)) {
                    Box(
                        modifier = Modifier
                            .align(Alignment.CenterStart)
                            .offset(y = -50.dp)
                            .padding(start = 16.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.tranhxachatkichthich),
                            contentDescription = "",
                            modifier = Modifier.size(250.dp)
                        )
                        Image(
                            painter = painterResource(id = R.drawable.xemthem),
                            contentDescription = "",
                            modifier = Modifier
                                .size(50.dp)
                                .offset(x = 140.dp, y = 120.dp)
                        )
                    }
                }

                Box(modifier = Modifier.fillMaxWidth().padding(bottom = 100.dp).offset(y = -200.dp)) {
                    Image(
                        painter = painterResource(id = R.drawable.line),
                        contentDescription = "",
                        modifier = Modifier
                            .size(width = 250.dp, height = 25.dp)
                            .align(Alignment.Center)
                            .offset(y = 20.dp)
                    )
                    Text(
                        text = "Tham khảo thêm tại: google.com",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Black,
                        modifier = Modifier
                            .align(Alignment.Center)
                    )
                }


            }
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Bottom
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .navigationBarsPadding()
                .height(80.dp),
            contentAlignment = Alignment.Center
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                var showDialog by remember { mutableStateOf(false) }

                Image(
                    painter = painterResource(R.drawable.trangchu),
                    contentDescription = "Trang chủ",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .size(140.dp)
                        .clickable (onClick = {navController.navigate("HomeScreen1")})
                )

                Spacer(modifier = Modifier.width(10.dp))
                Image(
                    painter = painterResource(R.drawable.a),
                    contentDescription = "Contact",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .size(50.dp)
                        .clickable {
                            if (navController.graph.findNode("ContactScreen") != null) {
                                navController.navigate("ContactScreen")
                            }
                        }
                )

                Spacer(modifier = Modifier.width(10.dp))
                Image(
                    painter = painterResource(R.drawable.hospital),
                    contentDescription = "Maps",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .size(40.dp)
                        .clickable {
                            if (navController.graph.findNode("Maps") != null) {
                                navController.navigate("Maps")
                            }
                        }
                )

                Spacer(modifier = Modifier.width(10.dp))
                Image(
                    painter = painterResource(R.drawable.c),
                    contentDescription = "Profile",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .size(40.dp)
                        .clickable {
                            if (navController.graph.findNode("ProfileScreen") != null) {
                                navController.navigate("ProfileScreen")
                            }
                        }
                )
            }
        }
    }
}