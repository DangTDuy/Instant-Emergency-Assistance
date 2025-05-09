package com.example.resqnow.Ui_Ux.theme.FirstAidGuide.Nosebleed

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.resqnow.Components.VideoPlayerFromRaw
import com.example.resqnow.R
import com.example.resqnow.Ui_Ux.theme.FirstAidGuide.ViewMenu.MenuFirstAidGuide.bottomBar
import com.example.resqnow.Ui_Ux.theme.contact.makePhoneCall


@Composable
fun noseBleed(navController: NavController){
    val context = LocalContext.current
    var step by remember { mutableStateOf(1) }


    val videoRes = when(step) {
        1 -> R.raw.nosebleed1
        2 -> R.raw.nosebleed2
        else -> R.raw.broken_arm
    }

    val stepTitle = "Bước $step"
    val stepText = when(step) {
        1 -> "Cho nạn nhân ngồi , đầu hơi cuối về trước." +
                "Dùng ngón tay trỏ và ngón cái bóp phần" +
                " mềm trên hai cánh mũi . Nếu máu vẫn còn" +
                " nhỏ giọt ra sàn thì điều chỉnh vị trí của tay " +
                "và tăng áp lực lên mũi"
        2 -> "Nếu máu mũi chảy nhiều sau 5 phút" +
                " dùng đá lạnh chườm vào sau gáy nạn nhân giúp cầm " +
                "máu mũi nhanh hơn "
        else -> ""
    }
    Scaffold (
        containerColor = Color.White,
        bottomBar = {
            bottomBar(navController = navController)
        }
    ){  innerPadding ->
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding)
        ){
            Row(
                modifier = Modifier
                    .padding(horizontal = 4.dp)
            ){
                Text(text = "Chảy máu mũi", fontSize = 25.sp, fontWeight = FontWeight.ExtraBold
                    ,modifier = Modifier
                        .align(Alignment.CenterVertically)
                )

                Spacer(modifier = Modifier.weight(1f))

                Image(painter = painterResource(id = R.drawable.exit), contentDescription = "Logo"
                    ,modifier = Modifier
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
                    .border(width = 1.dp,shape = RoundedCornerShape(10.dp), color = Color.Black)
                ,
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                shape = RoundedCornerShape(10.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ){
                Column(modifier = Modifier.padding(16.dp)) {
                    VideoPlayerFromRaw(
                        context = context,
                        rawResId = videoRes,
                        modifier = Modifier
                            .padding(horizontal = 20.dp)
                            .fillMaxWidth()
                            .height(265.dp)
                            .clip(RoundedCornerShape(10.dp))
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = stepTitle,
                            color = Color(0xFF007AFF),
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Spacer(modifier = Modifier.height(5.dp))

                    Text(text = stepText
                        , fontSize = 17.sp,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center

                    )

                    Spacer(modifier = Modifier.height(10.dp))
//Vị trí của 1 trường hợp sơ cứu
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ){
                        Box(

                            modifier = Modifier
                                .size(width = 38.dp, height = 8.dp)
                                .background(
                                    color = if (step == 1) Color(0xFF007AFF) else Color.Gray,

                                    shape = RoundedCornerShape(56.dp)
                                )

                        ){}
                        Spacer(modifier = Modifier.width(8.dp))
                        Box(

                            modifier = Modifier
                                .size(width = 38.dp, height = 8.dp)
                                .background(
                                    color = if (step == 2) Color(0xFF007AFF)  else Color.Gray

                                    ,shape = RoundedCornerShape(56.dp)
                                )

                        ){}
                    }


                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp),
            ) {
                Button(onClick = {
                    makePhoneCall(context,"115")
                }
                    ,colors = ButtonDefaults.buttonColors(Color.Red)
                    ,shape = RoundedCornerShape(5.dp)
                    ,modifier = Modifier

                        .size(width = 140.dp, height = 40.dp)
                ){
                    Image(painter = painterResource(id = R.drawable.baseline_local_phone_24), contentDescription = "Logo")

                    Spacer(modifier = Modifier.weight(1f))

                    Text(text = "Gọi 115",color = Color.White, fontSize = 15.sp, fontWeight = FontWeight.ExtraBold)
                }
                Spacer(modifier = Modifier.weight(1f))
                AnimatedVisibility(
                    visible = (step == 2),
                    enter = fadeIn(),
                    exit = fadeOut(),
                    modifier = Modifier.align(Alignment.CenterVertically)
                ){
                    Image(
                        painter = painterResource(R.drawable.poppackarrows),
                        contentDescription = "stepPrevious",
                        modifier = Modifier
                            .padding(horizontal = 5.dp)
                            .size(35.dp)

                            .clickable{
                                if (step > 1) step--
                            }

                    )
                }
                Image(painter = painterResource(R.drawable.next_step),contentDescription = "stepNext"
                    ,modifier = Modifier
                        .size(80.dp)
                        .clickable{
                            if (step < 2) step++
                        }
                )
            }


        }
    }

}