package com.example.resqnow.Ui_Ux.theme.FirstAidGuide

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.resqnow.Components.Snake_NonVenoument
import com.example.resqnow.Components.Snake_venoument
import com.example.resqnow.Components.background_firstaid
import com.example.resqnow.Components.borderBackground
import com.example.resqnow.Components.brokenArm
import com.example.resqnow.Components.burn
import com.example.resqnow.Components.stroke
import com.example.resqnow.Components.swallow_Poison
import com.example.resqnow.R

@Composable
fun LC_FirstAids() {
    //lazycolumn
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 5.dp, vertical = 5.dp)
            .background(background_firstaid)
            .border(width = 1.dp, color = borderBackground, shape = RoundedCornerShape(15.dp))
    ) {
        LazyColumn(
            modifier = Modifier.padding(10.dp)
        ) {
            //dòng 1
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 30.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    //Gãy tay (Không hở )
                    Card(
                        colors = CardDefaults.cardColors(brokenArm),
                        modifier = Modifier
                            .weight(1f)
                            .size(width = 110.dp, height = 150.dp)
                            .padding(horizontal = 4.dp),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(
                                painter = painterResource(R.drawable.gaytay),
                                contentDescription = "gaytay",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .size(width = 111.dp, height = 70.dp)
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            Text(
                                text = "Gãy tay \n(Không hở)", fontSize = 17.sp,
                                color = Color.Red,
                                fontWeight = FontWeight.Bold, textAlign = TextAlign.Center
                            )
                        }
                    }

                    //Đột Quỵ
                    Card(
                        colors = CardDefaults.cardColors(stroke),
                        modifier = Modifier
                            .weight(1f)
                            .size(width = 110.dp, height = 150.dp)
                            .padding(horizontal = 4.dp),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize(),

                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(
                                painter = painterResource(R.drawable.stroke_1),
                                contentDescription = "gaytay",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier

                                    .size(width = 113.dp, height = 66.dp)
                            )
                            Spacer(modifier = Modifier.height(20.dp))
                            Text(
                                text = "Đột quỵ", fontSize = 17.sp,
                                color = Color.Red,
                                fontWeight = FontWeight.Bold, textAlign = TextAlign.Center
                            )
                        }
                    }

                    //Nuốt chất độc
                    Card(
                        colors = CardDefaults.cardColors(swallow_Poison),
                        modifier = Modifier
                            .weight(1f)
                            .size(width = 110.dp, height = 150.dp)
                            .padding(horizontal = 4.dp),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize(),

                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(
                                painter = painterResource(R.drawable.swallow),
                                contentDescription = "gaytay",
                                contentScale = ContentScale.Fit,
                                modifier = Modifier

                                    .size(width = 110.dp, height = 67.dp)
                            )
                            Spacer(modifier = Modifier.height(23.dp))
                            Text(
                                text = "Nuốt chất độc", fontSize = 17.sp,
                                color = Color.Red,
                                fontWeight = FontWeight.Bold, textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }

            //dòng 2
            item {
                Row(

                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    //Rắn cắn (có độc)
                    Card(
                        colors = CardDefaults.cardColors(Snake_venoument),
                        modifier = Modifier
                            .weight(1f)
                            .size(width = 110.dp, height = 150.dp)
                            .padding(horizontal = 4.dp),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(
                                painter = painterResource(R.drawable.snake_venoument),
                                contentDescription = "gaytay",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier

                                    .size(width = 94.dp, height = 72.dp)
                            )
                            Spacer(modifier = Modifier.height(23.dp))
                            Text(
                                text = "Rắn cắn\n(có độc)", fontSize = 17.sp,
                                color = Color.Red,
                                fontWeight = FontWeight.Bold, textAlign = TextAlign.Center
                            )
                        }
                    }
                    //Rắn cắn ( không độc )
                    Card(
                        colors = CardDefaults.cardColors(Snake_NonVenoument),
                        modifier = Modifier
                            .weight(1f)
                            .size(width = 110.dp, height = 150.dp)
                            .padding(horizontal = 4.dp),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(
                                painter = painterResource(R.drawable.nonvenoument),
                                contentDescription = "gaytay",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier

                                    .size(width = 111.dp, height = 72.dp)
                            )
                            Spacer(modifier = Modifier.height(23.dp))
                            Text(
                                text = "Rắn cắn\n(không độc)", fontSize = 17.sp,
                                color = Color.Red,
                                fontWeight = FontWeight.Bold, textAlign = TextAlign.Center
                            )
                        }
                    }

                    //Bỏng nhiệt
                    Card(
                        colors = CardDefaults.cardColors(burn),
                        modifier = Modifier
                            .weight(1f)
                            .size(width = 110.dp, height = 150.dp)
                            .padding(horizontal = 4.dp),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(
                                painter = painterResource(R.drawable.burnpng),
                                contentDescription = "gaytay",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier

                                    .size(width = 111.dp, height = 72.dp)
                            )
                            Spacer(modifier = Modifier.height(23.dp))
                            Text(
                                text = "Bỏng nhiệt", fontSize = 17.sp,
                                color = Color.Red,
                                fontWeight = FontWeight.Bold, textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }

            //dòng 3
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 30.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    //Đuối nước
                    Card(
                        colors = CardDefaults.cardColors(Color(0xFFF1D278)),
                        modifier = Modifier
                            .weight(1f)
                            .size(width = 110.dp, height = 150.dp)
                            .padding(horizontal = 4.dp),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(
                                painter = painterResource(R.drawable.browning),
                                contentDescription = "gaytay",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier

                                    .size(width = 111.dp, height = 72.dp)
                            )
                            Spacer(modifier = Modifier.height(23.dp))
                            Text(
                                text = "Đuối nước", fontSize = 17.sp,
                                color = Color.Red,
                                fontWeight = FontWeight.Bold, textAlign = TextAlign.Center
                            )
                        }
                    }

                    //Gãy chân
                    Card(
                        colors = CardDefaults.cardColors(Color(0xFFE2F5FF)),
                        modifier = Modifier
                            .weight(1f)
                            .size(width = 110.dp, height = 150.dp)
                            .padding(horizontal = 4.dp),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(
                                painter = painterResource(R.drawable.brokenleg),
                                contentDescription = "gaytay",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier

                                    .size(width = 111.dp, height = 75.dp)
                            )
                            Spacer(modifier = Modifier.height(23.dp))
                            Text(
                                text = "Gãy chân", fontSize = 17.sp,
                                color = Color.Red,
                                fontWeight = FontWeight.Bold, textAlign = TextAlign.Center
                            )
                        }
                    }

                    //Chảy máu
                    Card(
                        colors = CardDefaults.cardColors(Color(0xFFFCD8E1)),
                        modifier = Modifier
                            .weight(1f)
                            .size(width = 110.dp, height = 150.dp)
                            .padding(horizontal = 4.dp),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(
                                painter = painterResource(R.drawable.bleeding),
                                contentDescription = "gaytay",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier

                                    .size(width = 111.dp, height = 75.dp)
                            )
                            Spacer(modifier = Modifier.height(23.dp))
                            Text(
                                text = "Chảy máu", fontSize = 17.sp,
                                color = Color.Red,
                                fontWeight = FontWeight.Bold, textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }
        }
    }
}
