package com.example.resqnow.Ui_Ux.theme.personalization.MiddleAge


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
import androidx.compose.material3.IconButton
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
import com.example.resqnow.Ui_Ux.theme.Router.Screen


@Composable
fun MiddlePathology(navController: NavController) {
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
                    .padding(start = 50.dp)
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
            painter = painterResource(id = R.drawable.trungnien),
            contentDescription = "trungnien",
            modifier = Modifier
                .size(width = 200.dp, height = 150.dp)
                .align(Alignment.CenterHorizontally)
        )
        Image(
            painter = painterResource(id = R.drawable.trungnien1),
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
            text = "Một số bệnh hay mắc phải ở\n        tuổi trung niên",
            fontSize = 19.sp,
            fontWeight = FontWeight.Black,
            color = Color.Red,
            modifier = Modifier
                .padding(start = 16.dp)
                .align(Alignment.CenterHorizontally)
                .offset(y = - 140.dp)
        )



    }
    LazyColumn(modifier = Modifier
        .fillMaxWidth()
        .padding(top = 420.dp, bottom = 100.dp)) {
        item {
            Spacer(modifier = Modifier.height(25.dp))
            Row() {
                Image(
                    painter = painterResource(id = R.drawable.caohuyetap),
                    contentDescription = "",
                    modifier = Modifier
                        .size(50.dp)
                        .offset(y = -25.dp)
                )
                Text(
                    text = "1. Cao huyết áp và các bệnh lý tim mạch",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Black,
                    modifier = Modifier
                        .padding(start = 16.dp)
                )
            }
            Box(modifier = Modifier.fillMaxWidth()) {
                Image(
                    painter = painterResource(id = R.drawable.caohuyetap1),
                    contentDescription = "",
                    modifier = Modifier
                        .size(width = 410.dp, height = 260.dp)
                        .align(Alignment.Center)
                        .offset(y = -90.dp)
                )
            }
            Box(modifier = Modifier.fillMaxWidth()) {
                Image(
                    painter = painterResource(id = R.drawable.line),
                    contentDescription = "",
                    modifier = Modifier
                        .size(width = 250.dp, height = 25.dp)
                        .offset(y = -160.dp)
                        .align(Alignment.Center)
                )
            }


            Row(modifier = Modifier.offset(y = -150.dp)) {
                Image(
                    painter = painterResource(id = R.drawable.daithaoduong),
                    contentDescription = "",
                    modifier = Modifier
                        .size(50.dp)
                        .offset(y = -25.dp)
                )
                Text(
                    text = "2. Đái tháo đường (tiểu đường)",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Black,
                    modifier = Modifier
                        .padding(start = 16.dp)
                )
            }
            Box(modifier = Modifier.fillMaxWidth()) {
                Image(
                    painter = painterResource(id = R.drawable.daithaoduong1),
                    contentDescription = "",
                    modifier = Modifier
                        .size(width = 410.dp, height = 260.dp)
                        .align(Alignment.Center)
                        .offset(y = -230.dp)
                )
            }
            Box(modifier = Modifier.fillMaxWidth()) {
                Image(
                    painter = painterResource(id = R.drawable.line),
                    contentDescription = "",
                    modifier = Modifier
                        .size(width = 250.dp, height = 25.dp)
                        .offset(y = -300.dp)
                        .align(Alignment.Center)
                )
            }

            Row(modifier = Modifier.offset(y = -290.dp)) {
                Image(
                    painter = painterResource(id = R.drawable.gout),
                    contentDescription = "",
                    modifier = Modifier
                        .size(50.dp)
                        .offset(y = -25.dp)
                )
                Text(
                    text = "3. Bệnh gout (bệnh gút)",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Black,
                    modifier = Modifier
                        .padding(start = 16.dp)
                )
            }
            Box(modifier = Modifier.fillMaxWidth()) {
                Image(
                    painter = painterResource(id = R.drawable.gout1),
                    contentDescription = "",
                    modifier = Modifier
                        .size(width = 410.dp, height = 260.dp)
                        .align(Alignment.Center)
                        .offset(y = -380.dp)
                )
            }
            Box(modifier = Modifier.fillMaxWidth()) {
                Image(
                    painter = painterResource(id = R.drawable.line),
                    contentDescription = "",
                    modifier = Modifier
                        .size(width = 250.dp, height = 25.dp)
                        .offset(y = -450.dp)
                        .align(Alignment.Center)
                )
            }

            Row(modifier = Modifier.offset(y = -440.dp)) {
                Image(
                    painter = painterResource(id = R.drawable.tienliettuyen),
                    contentDescription = "",
                    modifier = Modifier
                        .size(50.dp)
                        .offset(y = -25.dp)
                )
                Text(
                    text = "4. U xơ tiền liệt tuyến",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Black,
                    modifier = Modifier
                        .padding(start = 16.dp)
                )
            }
            Box(modifier = Modifier.fillMaxWidth()) {
                Image(
                    painter = painterResource(id = R.drawable.tienliettuyen1),
                    contentDescription = "",
                    modifier = Modifier
                        .size(width = 410.dp, height = 260.dp)
                        .align(Alignment.Center)
                        .offset(y = -510.dp)
                )
            }
            Box(modifier = Modifier.fillMaxWidth().padding(bottom = 250.dp)) {
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








    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Bottom
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .navigationBarsPadding()
                .fillMaxWidth()
                .height(105.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.firstaid_navbar),
                contentDescription = "Logo",
                modifier = Modifier.fillMaxSize()
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = { navController.navigate(Screen.HomePage1.route) },
                    modifier = Modifier
                        .padding(bottom = 4.dp)
                        .size(60.dp)
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.home),
                            contentDescription = "Logo",
                            modifier = Modifier.size(41.dp, 39.dp)
                                .clickable {navController.navigate(Screen.HomePage1.route) }
                        )
                    }
                }
                IconButton(
                    onClick = { navController.navigate(Screen.ContactScreen.route) },
                    modifier = Modifier
                        .padding(top = 40.dp)
                        .size(60.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.a),
                        contentDescription = "Logo",
                        alignment = Alignment.Center,
                        modifier = Modifier.size(37.dp, 32.dp)
                    )
                }
                IconButton(
                    onClick = {
                        if (navController.graph.findNode("Maps") != null) {
                            navController.navigate("Maps")
                        }
                    },
                    modifier = Modifier
                        .padding(top = 40.dp)
                        .size(60.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.hospital),
                        contentDescription = "Logo",
                        alignment = Alignment.Center,
                        modifier = Modifier.size(35.dp)
                    )
                }
                IconButton(
                    onClick = { navController.navigate(Screen.ProfileScreen.route) },
                    modifier = Modifier
                        .padding(top = 40.dp)
                        .size(60.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.c),
                        contentDescription = "Logo",
                        alignment = Alignment.Center,
                        modifier = Modifier.size(35.dp)
                    )
                }
            }
        }
    }
}

