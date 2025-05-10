package com.example.resqnow.Ui_Ux.theme.FirstAidGuide.ViewMenu.MenuFirstAidGuide

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.resqnow.R
import com.example.resqnow.Ui_Ux.theme.Router.Screen


@Composable
fun bottomBar(navController: NavController){
    Box(
        contentAlignment = Alignment.Center,

        modifier = Modifier
            .navigationBarsPadding()
            .fillMaxWidth()
            .height(105.dp)
    ){
        Image(painter = painterResource(id = R.drawable.firstaid_navbar), contentDescription = "Logo"
            , modifier = Modifier
                .fillMaxSize()

        )
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
,
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
                    )
                }
            }
            IconButton(onClick = {navController.navigate(Screen.ContactScreen.route)}
                ,modifier = Modifier
                    .padding(top = 40.dp)
                    .size(60.dp)
            ) {

                Image(painter = painterResource(id = R.drawable.a), contentDescription = "Logo"
                        ,alignment = Alignment.Center,
                    modifier = Modifier
                        .size(width = 37.dp, height = 32.dp)
                )
            }
            IconButton(onClick = {
                if (navController.graph.findNode("Maps") != null) {
                    navController.navigate("Maps")
                }
            }
                ,modifier = Modifier
                    .padding(top = 40.dp)
                    .size(60.dp)
            ) {
                Image(painter = painterResource(id = R.drawable.hospital), contentDescription = "Logo"
                    ,alignment = Alignment.Center
                    ,modifier = Modifier
                        .size(width = 35.dp, height = 35.dp)
                )

            }
            IconButton(onClick = {Screen.ProfileScreen.route}
                ,modifier = Modifier
                    .padding(top = 40.dp)
                    .size(60.dp)
            ) {
                Image(painter = painterResource(id = R.drawable.c), contentDescription = "Logo"
                    ,alignment = Alignment.Center
                    ,modifier = Modifier
                        .size(width = 35.dp, height = 35.dp)
                        .clickable{navController.navigate(Screen.ProfileScreen.route)}
                )
            }
        }

    }
}