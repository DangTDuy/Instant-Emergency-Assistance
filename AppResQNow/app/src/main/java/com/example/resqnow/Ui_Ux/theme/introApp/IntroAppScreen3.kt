package com.example.resqnow.Ui_Ux.theme.introApp



import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.resqnow.Components.Bắt_đầu_color

import com.example.resqnow.R

@Composable
fun IntroScreen3() {
    Box(modifier = Modifier.fillMaxSize()){
        Image(painter = painterResource(R.drawable.wave_hand),contentDescription = "tay cầm phone"
            , modifier = Modifier
                .padding(start = 0.dp,top = (-20).dp)
                .fillMaxSize()
        )

        Image(painter = painterResource(R.drawable.text_intro3),contentDescription = "chữ "
            ,modifier = Modifier
                .padding(start = 10.dp, top =600.dp)
                .size(width = 355.69.dp, height = 59.dp)
        )

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier
            .padding(start = 2.dp, top = 700.dp)

        ) {
            Box(
                modifier = Modifier
                    .size(width = 40.dp, height = 8.dp)
                    .clip(RoundedCornerShape(3.dp))
                    .background(Color.Blue)
            )
            Box(
                modifier = Modifier
                    .size(width = 40.dp, height = 8.dp)
                    .clip(RoundedCornerShape(3.dp))
                    .background(Color.Gray)
            )

        }
        Button(
            onClick = { /* TODO: Điều hướng màn hình */ },
            shape = CircleShape,
            colors = ButtonDefaults.buttonColors(Bắt_đầu_color),
            modifier = Modifier
                .align(Alignment.BottomEnd) // Căn vào góc phải
                .offset(x = (-16).dp, y = (-50).dp) // Điều chỉnh vị trí so với góc phải
                .size(80.dp)
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_arrow_forward),
                contentDescription = "Next",
                tint = Color.White
                ,modifier = Modifier.size(37.dp)
            )
        }
    }

}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewIntroScreen3() {
    IntroScreen3()
}
