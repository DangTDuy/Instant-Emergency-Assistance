package com.example.resqnow.Ui_Ux.theme.FirstAidGuide

import android.R.attr.fontWeight
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton

import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.resqnow.R
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import com.example.resqnow.Components.background_firstaid
import com.example.resqnow.Components.borderBackground


// Màn hình chức năng  sổ tay sơ cứu



@Composable
fun FirstAidGuideScreen(){


    var text by remember { mutableStateOf("") }
    var clear  by remember { mutableStateOf(false) }


    Scaffold (
        containerColor = Color.White,
        bottomBar = {
            bottomBar()
        }
    ){ innerPadding ->
        Column (
            modifier = Modifier
                .fillMaxWidth()
            .padding(innerPadding)
        ){
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
                Image(painter = painterResource(id = R.drawable.huongdankhancap), contentDescription = "Logo"
                    ,modifier = Modifier
                        .padding(start = 45.dp)
                        .size(width = 93.dp, height = 80.dp)
                )
            }


            OutlinedTextField(
                shape = RoundedCornerShape(10.dp),
                textStyle = androidx.compose.ui.text.TextStyle(color = Color.Black, fontSize = 15.sp), // Màu chữ khi nhập

                //của OL_TextField
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .padding(horizontal = 47.dp),
                //Bên trong
                value = text,
                onValueChange = { text = it },
                label = { Text("Tìm kiếm") },
                placeholder = { Text("Phương pháp sơ cứu") },

                leadingIcon = {
                    Icon(
                        imageVector =Icons.Filled.Search,
                        contentDescription = "Search",
                        tint = Color.Red
                    )
                },
                trailingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Clear,
                            contentDescription = "Search",
                            modifier = Modifier
                                .clickable(onClick = {
                                    clear = true
                                    if(clear){
                                        text = ""
                                        clear = false
                                    }
                                })
                        )

                }
            )

            //lazycolumn
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 5.dp,vertical = 5.dp)
                    .background(background_firstaid)
                    .border(width = 1.dp, color = borderBackground, shape = RoundedCornerShape(15.dp))
            ){
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
                                modifier = Modifier
                                    .weight(1f)
                                    .size(width = 110.dp, height = 150.dp)
                                    .padding(horizontal = 4.dp),
                                shape = RoundedCornerShape(12.dp)
                            ) {
                                Column(
                                    modifier = Modifier
                                        .padding(16.dp),

                                ) {
                                    Image(painter = painterResource(R.drawable.gaytay),contentDescription = "gaytay"
                                        ,modifier = Modifier
                                            .size(width = 111.dp, height = 70.dp)
                                    )
                                }
                            }

                            //Đột Quỵ
                            Card(
                                modifier = Modifier
                                    .weight(1f)
                                    .size(width = 110.dp, height = 150.dp)
                                    .padding(horizontal = 4.dp),
                                shape = RoundedCornerShape(12.dp)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .padding(16.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(text = "minato")
                                }
                            }

                            //Nuốt chất độc
                            Card(
                                modifier = Modifier
                                    .weight(1f)
                                    .size(width = 110.dp, height = 150.dp)
                                    .padding(horizontal = 4.dp),
                                shape = RoundedCornerShape(12.dp)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .padding(16.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(text = "minato")
                                }
                            }
                        }
                    }

                    //dòng 2
                        item{
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 4.dp),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                //Rắn cắn (có độc)
                                Card(
                                    modifier = Modifier
                                        .weight(1f)
                                        .size(width = 110.dp, height = 150.dp)
                                        .padding(horizontal = 4.dp),
                                    shape = RoundedCornerShape(12.dp)
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .padding(16.dp),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text(text = "Chú bé đàn")
                                    }
                                }
                                //Rắn cắn ( không độc )
                                Card(
                                    modifier = Modifier
                                        .weight(1f)
                                        .size(width = 110.dp, height = 150.dp)
                                        .padding(horizontal = 4.dp),
                                    shape = RoundedCornerShape(12.dp)
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .padding(16.dp),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text(text = "bến tre")
                                    }
                                }

                                //Bỏng nhiệt
                                Card(
                                    modifier = Modifier
                                        .weight(1f)
                                        .size(width = 110.dp, height = 150.dp)
                                        .padding(horizontal = 4.dp),
                                    shape = RoundedCornerShape(12.dp)
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .padding(16.dp),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text(text = "trần hà linh")
                                    }
                                }
                            }
                        }

                    //dòng 3
                    item{
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 30.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            //Đuối nước
                            Card(
                                modifier = Modifier
                                    .weight(1f)
                                    .size(width = 110.dp, height = 150.dp)
                                    .padding(horizontal = 4.dp),
                                shape = RoundedCornerShape(12.dp)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .padding(16.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(text = "Chú bé đàn")
                                }
                            }

                            //Gãy chân
                            Card(
                                modifier = Modifier
                                    .weight(1f)
                                    .size(width = 110.dp, height = 150.dp)
                                    .padding(horizontal = 4.dp),
                                shape = RoundedCornerShape(12.dp)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .padding(16.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(text = "bến tre")
                                }
                            }

                            //Chảy máu
                            Card(
                                modifier = Modifier
                                    .weight(1f)
                                    .size(width = 110.dp, height = 150.dp)
                                    .padding(horizontal = 4.dp),
                                shape = RoundedCornerShape(12.dp)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .padding(16.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(text = "trần hà linh")
                                }
                            }
                        }
                    }
                }//end lazyycolumn
            }
        }

    }

}



@Preview(showBackground = true, showSystemUi = true)
@Composable
fun FirstAidGuideScreenPreview(){
    FirstAidGuideScreen()
}