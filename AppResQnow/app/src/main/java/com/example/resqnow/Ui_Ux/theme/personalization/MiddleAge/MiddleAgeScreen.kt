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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
fun MiddleAgeScreen(navController: NavController) {
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
        Text(
            text = "Các bệnh lý hay mắc phải",
            fontSize = 19.sp,
            fontWeight = FontWeight.Black,
            modifier = Modifier
                .padding(start = 16.dp)
        )
        Spacer(modifier = Modifier.height(5.dp))
        Image(
            painter = painterResource(id = R.drawable.benhhaymacphai),
            contentDescription = "",
            modifier = Modifier
                .size(width = 370.dp, height = 170.dp)
                .align(Alignment.CenterHorizontally)
                .clickable(onClick = {navController.navigate("MiddlePathology")})
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Phương pháp phòng bệnh",
            fontSize = 19.sp,
            fontWeight = FontWeight.Black,
            modifier = Modifier
                .padding(start = 16.dp)
        )
        Spacer(modifier = Modifier.height(5.dp))
        Image(
            painter = painterResource(id = R.drawable.phuongphapphongbenh),
            contentDescription = "",
            modifier = Modifier
                .size(width = 370.dp, height = 170.dp)
                .align(Alignment.CenterHorizontally)
                .clickable(onClick = {navController.navigate("MiddlePreventivemeasures")})
        )
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