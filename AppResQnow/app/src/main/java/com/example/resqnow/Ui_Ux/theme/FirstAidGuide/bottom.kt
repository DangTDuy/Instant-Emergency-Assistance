package com.example.resqnow.Ui_Ux.theme.FirstAidGuide

import androidx.compose.foundation.Image
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
import com.example.resqnow.R

@Composable
fun bottomBar(){
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .navigationBarsPadding()  // Thêm padding để tránh bị che
            .fillMaxWidth()
            .height(105.dp)
    ){
        Image(painter = painterResource(id = R.drawable.firstaid_navbar), contentDescription = "Logo"
            , modifier = Modifier
                .fillMaxSize()
        )
        Row (
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(horizontal = 16.dp)
                .fillMaxSize(),

            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            IconButton(onClick = {}
                ,modifier = Modifier
                    .padding(top =  20.dp)
                    .size(60.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.home),
                    contentDescription = "Logo",
                    modifier = Modifier
                        .size(width = 41.dp, height = 39.dp)

                )
            }
            IconButton(onClick = {}
                ,modifier = Modifier
                    .padding(top = 40.dp)
                    .size(60.dp)
            ) {
                Image(painter = painterResource(id = R.drawable.a), contentDescription = "Logo"
                    , modifier = Modifier
                        .size(width = 37.dp, height = 32.dp)
                )
            }
            IconButton(onClick = {}
                ,modifier = Modifier
                    .padding(top = 40.dp)
                    .size(60.dp)
            ) {
                Image(painter = painterResource(id = R.drawable.hospital), contentDescription = "Logo"
                    ,modifier = Modifier
                        .size(width = 35.dp, height = 35.dp)
                )
            }
            IconButton(onClick = {}
                ,modifier = Modifier
                    .padding(top = 40.dp)
                    .size(60.dp)
            ) {
                Image(painter = painterResource(id = R.drawable.c), contentDescription = "Logo"
                    ,modifier = Modifier
                        .size(width = 35.dp, height = 35.dp)
                )
            }
        }

    }
}