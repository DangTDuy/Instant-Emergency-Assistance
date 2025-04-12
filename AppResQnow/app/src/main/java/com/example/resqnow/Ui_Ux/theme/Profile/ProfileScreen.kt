package com.example.resqnow.Ui_Ux.theme.Profile


import androidx.compose.material3.TextField
import android.widget.Toast
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.resqnow.Components.LightPink
import com.example.resqnow.Components.Text_color
import com.example.resqnow.Data.Api_and_Firebase.FireBaseGoogle.AppUserData
import com.example.resqnow.Data.Api_and_Firebase.FireBaseGoogle.GoogleAuthUiClient
import com.example.resqnow.R
import kotlinx.coroutines.launch
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.runtime.LaunchedEffect
import com.example.resqnow.Data.Api_and_Firebase.DataStore.clearUserData

import com.example.resqnow.Data.Api_and_Firebase.DataStore.readUserData
import com.example.resqnow.Data.Api_and_Firebase.DataStore.saveUserData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.withContext


@Composable
fun ProfileScreen(
    userData: AppUserData?,
    googleAuthUiClient: GoogleAuthUiClient,
    onSignedOut: () -> Unit

) {



    //keyboard
    val keyboardController = LocalSoftwareKeyboardController.current
    //Textfield
    var name by remember { mutableStateOf("") }
    var number_phone by remember {mutableStateOf("")}
    var sex by remember {mutableStateOf("")}
    var age by remember {mutableStateOf("")}
    //Google
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    //DataStore
    LaunchedEffect(true) {
        readUserData(context)?.also {
            name = it.name
            number_phone = it.phone
            sex = it.sex
            age = it.age
        }
    }



    Column(

        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(top = 38.dp)
    ) {
        Box() {
            Image(
                painter = painterResource(R.drawable.profile_top_background),
                contentDescription = "topbackground",
                modifier = Modifier
                    .size(width = 411.dp, height = 100.dp)
            )
            Row(modifier = Modifier.fillMaxWidth()) {
                IconButton(onClick = { }) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Đóng",
                        modifier = Modifier
                            .size(44.dp)
                            .padding(top = 15.dp)
                    )
                }
                Text(
                    text = "Trang Cá Nhân",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Text_color,
                    modifier = Modifier
                        .padding(start = 55.dp, top = 25.dp)
                )
            }
            Box(
                modifier = Modifier
                    .padding(top = 62.dp)
                    .align(Alignment.Center)
                    .background(color = Color.White, shape = RoundedCornerShape(15.dp))
                    .size(width = 250.dp, height = 81.dp)
            ) {
                Button(
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(LightPink),
                    modifier = Modifier
                        .size(width = 200.dp, height = 70.dp)
                        .align(Alignment.Center)
                        .border(
                            width = 1.dp,
                            color = Color.Black,
                            shape = RoundedCornerShape(10.dp)
                        )
                        .background(color = LightPink, shape = RoundedCornerShape(10.dp))
                ) {
                    Row(modifier = Modifier.fillMaxSize()) {
                        userData?.profilePictureUrl?.let { url ->
                            AsyncImage(
                                model = url,
                                contentDescription = "Avatar",
                                modifier = Modifier
                                    .size(70.dp)
                                    .weight(2f)
                                    .clip(RoundedCornerShape(49.dp))
                                    .background(
                                        color = Color.White,
                                        shape = RoundedCornerShape(49.dp)
                                    )
                                    .border(
                                        width = 1.dp,
                                        color = Color.White,
                                        shape = RoundedCornerShape(49.dp)
                                    )
                            )
                        }

                        Spacer(modifier = Modifier.width(5.dp))
                        Column {
                            Text(
                                text = userData?.name ?: "Người dùng",
                                modifier = Modifier
                                    .align(Alignment.CenterHorizontally)
                                    .padding(top = 5.dp),
                                style = TextStyle(
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Black,

                                    )
                            )
                            Text(
                                text = "Thành viên ResQ",
                                color = Color.Black,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Light,
                                modifier = Modifier
                                    .padding(2.dp)
                            )
                        }
                    }
                }
            }
        }

        Text(
            text = "Thông tin cơ bản ",
            fontSize = 18.sp,
            fontWeight = FontWeight.Black,
            modifier = Modifier
                .padding(start = 5.dp, top = 10.dp)
        )
        Text(
            text = "Nhập thông tin cá nhân của bạn ở đây",
            fontSize = 13.sp,
            fontWeight = FontWeight.ExtraBold,
            modifier = Modifier
                .padding(start = 5.dp, top = 2.dp)
        )
        Spacer(modifier = Modifier.height(2.dp))

        Box(modifier = Modifier.fillMaxWidth()) {
            Box(
                modifier = Modifier
                    .size(width = 320.dp, height = 350.dp) // Tăng chiều cao
                    .padding(start = 78.dp, top = 10.dp)
                    .border(
                        width = 1.dp,
                        color = Color.Black,
                        shape = RoundedCornerShape(15.dp)
                    )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 10.dp, vertical = 5.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {

                    // Template cho từng ô nhập liệu
                    @Composable
                    fun InputField(label: String, value: String, onChange: (String) -> Unit, hint: String) {
                        Column() {
                            Text(
                                text = label,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.align(Alignment.Start) // căn trái cho label
                            )
                            TextField(
                                value = value,
                                onValueChange = onChange,
                                placeholder = {
                                    Text(text = hint, fontSize = 11.sp, color = Color.Gray
                                        ,modifier = Modifier.align(Alignment.End)
                                    )
                                },
                                textStyle = TextStyle(
                                    fontSize = 13.sp,
                                    color = Color.Red,
                                    fontWeight = FontWeight.ExtraBold
                                ),
                                colors = TextFieldDefaults.colors(
                                     focusedTextColor = Color.Red,
                                        unfocusedTextColor = Color.Red,
                                        cursorColor = Color.Red,
                                        focusedIndicatorColor = Color.Black,
                                        unfocusedIndicatorColor = Color.LightGray,
                                        focusedContainerColor = Color.Transparent,
                                        unfocusedContainerColor = Color.Transparent

                                ),
                                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                                keyboardActions = KeyboardActions(onDone = {
                                    keyboardController?.hide()
                                }),
                                modifier = Modifier
                                    .padding(start = 30.dp)
                                    .width(200.dp)
                                    .height(50.dp)
                            )

                        }
                    }

                    // Các ô nhập liệu
                    InputField("Họ và Tên", name, { name = it }, "Mời bạn nhập họ và tên")
                    InputField("Số di động", number_phone, { number_phone = it }, "Nhập số điện thoại")
                    InputField("Giới tính", sex, { sex = it }, "Nam / Nữ")
                    InputField("Tuổi", age, { age = it }, "Nhập tuổi")
                }
            }
        }

        //Đăng xuất
        Row(modifier = Modifier.fillMaxWidth()) {
            Button(
                onClick = {
                    scope.launch {
                        clearUserData(context)
                        googleAuthUiClient.signOut()
                        Toast.makeText(context, "Đã đăng xuất", Toast.LENGTH_SHORT).show()
                        onSignedOut()
                    }
                },
                colors = ButtonDefaults.buttonColors(Color.White),
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(top = 17.dp, start = 130.dp)
                    .border(width = 1.dp, color = Color.Red, shape = RoundedCornerShape(5.dp))
                    .size(width = 150.dp, height = 35.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxSize(),
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.logout),
                        contentDescription = "Đăng xuất",
                        modifier = Modifier
                            .size(14.dp)
                            .weight(1f)
                    )
                    Text(
                        text = "Đăng xuất",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Red,
                        modifier = Modifier
                            .weight(3f)
                            .padding(start = 1.dp)
                    )
                }
            }
            TextButton(onClick = {
                CoroutineScope(Dispatchers.IO).launch {
                    saveUserData(context, name, number_phone, sex, age)
                    withContext(Dispatchers.Main) {
                        Toast.makeText(context, "Lưu thành công", Toast.LENGTH_SHORT).show()
                    }
                }
            },
                modifier = Modifier.padding(start = 12.dp,top = 30.dp)
            ){
                Text(text  = "Xác Nhận",
                fontSize = 13.sp,
                fontWeight = FontWeight.ExtraBold,
                )
            }
        }


        Row() {
            Column() {
                Text(
                    text = "Cá Nhân Hóa", fontSize = 18.sp, fontWeight = FontWeight.Black,
                    modifier = Modifier
                        .padding(start = 29.dp, top = 20.dp)
                )
                Text(
                    text = "Tra cứu các loại bệnh theo độ tuổi của bạn",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.ExtraBold,
                    modifier = Modifier
                        .padding(start = 29.dp, top = 5.dp)
                )
            }

            Image(
                painter = painterResource(id = R.drawable.icon_personal),
                contentDescription = "Cá nhân hóa",
                modifier = Modifier
                    .padding(start = 45.dp, top = 20.dp)
                    .size(44.dp)
                    .border(width = 2.dp, color = Color.Red, shape = RoundedCornerShape(5.dp))


            )


        }
        Box() {
            Image(
                painter = painterResource(id = R.drawable.new_nav_bar),
                contentDescription = "navbar",
                modifier = Modifier
                    .padding(top = 20.dp, start = 0.dp, end = 0.dp)
                    .size(width = 600.dp, height = 120.dp)
            )
            Row() {
                Image(
                    painter = painterResource(id = R.drawable.home),
                    contentDescription = "home",
                    modifier = Modifier
                        .padding(start = 9.dp, top = 78.dp)
                        .size(width = 39.dp, height = 37.dp)
                )
                Image(
                    painter = painterResource(id = R.drawable.a), contentDescription = "home",
                    modifier = Modifier
                        .padding(start = 70.dp, top = 78.dp)
                        .size(width = 39.dp, height = 37.dp)
                )
                Image(
                    painter = painterResource(id = R.drawable.hospital),
                    contentDescription = "home",
                    modifier = Modifier
                        .padding(start = 60.dp, top = 78.dp)
                        .size(width = 37.dp, height = 35.dp)
                )
                Image(
                    painter = painterResource(id = R.drawable.c), contentDescription = "home",
                    modifier = Modifier
                        .padding(start = 48.dp, top = 60.dp)
                        .size(width = 39.dp, height = 37.dp)
                )

            }
        }
    }
}
  