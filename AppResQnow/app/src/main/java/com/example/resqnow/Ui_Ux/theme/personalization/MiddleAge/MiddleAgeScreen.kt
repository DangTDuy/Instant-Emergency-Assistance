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
        )
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