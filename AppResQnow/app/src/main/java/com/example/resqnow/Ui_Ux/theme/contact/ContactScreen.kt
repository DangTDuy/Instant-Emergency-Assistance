package com.example.resqnow.Ui_Ux.theme.contact

import android.annotation.SuppressLint
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.resqnow.R
import com.example.resqnow.Room.ContactEntity
import com.example.resqnow.viewModel.ContactViewModel
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ripple.rememberRipple
import com.example.resqnow.Ui_Ux.theme.Router.Screen
import com.google.firebase.auth.FirebaseAuth


// Màn hình chức năng danh bạ


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ContactScreen(
    viewModel: ContactViewModel,
    showDialog: MutableState<Boolean>,
    navController: NavController,


) {
    var inputName by remember { mutableStateOf("") }
    var inputPhoneNumber by remember { mutableStateOf("") }
    var inputUrlImage by remember { mutableStateOf("") }
    val userId = FirebaseAuth.getInstance().currentUser?.uid ?: ""
    val context = LocalContext.current
    val contacts by viewModel.getContactsByUserId(userId).collectAsState(initial = emptyList())


    Scaffold(

        containerColor = Color.White,
        floatingActionButton = {
            Box(
                modifier = Modifier
                    .size(width = 134.dp, height = 40.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color.White)
                    .border(width = 1.dp, color = Color.Red, shape = RoundedCornerShape(12.dp))
                    .clickable { showDialog.value = true },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "+ Thêm liên hệ",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Red

                )

            }

        },
        bottomBar = {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .navigationBarsPadding()
                    .height(100.dp)
                    .background(Color.White)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.group),
                    contentDescription = "Logo",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 30.dp), // tất cả icon xuống thấp thêm chút
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.home),
                        contentDescription = "Trang chủ",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .padding(end = 20.dp)
                            .size(width = 41.dp, height = 39.dp)
                            .clickable{
                                    navController.navigate(Screen.HomePage1.route)
                            }
                    )
                    Image(
                        painter = painterResource(id = R.drawable.a),
                        contentDescription = "Liên hệ",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .padding(end =25.dp, bottom = 37.dp)
                            .size(width = 37.dp, height = 32.dp)

                    )
                    Image(
                        painter = painterResource(id = R.drawable.hospital),
                        contentDescription = "Bệnh viện",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .padding(end  = 30.dp)
                            .size(width = 35.dp, height = 35.dp)
                            .clickable{
                                ///
                            }
                    )
                    Image(
                        painter = painterResource(id = R.drawable.c),
                        contentDescription = "Tài khoản",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
//                            .padding(end = 15.dp, top = 40.dp)
                            .size(width = 35.dp, height = 35.dp)
                            .clickable{
                                navController.navigate(Screen.ProfileScreen.route)
                            }
                    )
                }
            }
        },

        ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
        ) {
            // HeadBar luôn hiển thị ở trên
            HeadBar()

        if (contacts.isEmpty()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .background(Color.White)
            ) {
                Column(
                    modifier = Modifier
                        .padding(paddingValues)
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(text = "Chưa có liên hệ khẩn cấp nào.")
                }
            }
        } else {

            LazyColumn(
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(top = 2.dp)
                    .fillMaxSize(),
                contentPadding = PaddingValues(10.dp)
            ) {

                items(contacts) { contact ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(5.dp),
                        elevation = CardDefaults.cardElevation(10.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .padding(10.dp)
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            // Bên trái: Avatar + Tên + SĐT
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                AsyncImage(
                                    model = Uri.parse(contact.urlImage),
                                    contentDescription = null,
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier
                                        .size(50.dp)
                                        .clip(CircleShape)
                                )
                                Column(modifier = Modifier.padding(start = 20.dp)) {
                                    Text(
                                        text = contact.name,
                                        fontSize = 20.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Text(
                                        text = contact.phoneNumber,
                                        fontSize = 16.sp
                                    )
                                }
                            }
                            Row() {
                                // Bên phải: IconButton gọi điện
                                IconButton(
                                    onClick = { makePhoneCall(context, contact.phoneNumber) },
                                    modifier = Modifier.padding(end = 2.dp)

                                ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.phone),
                                        contentDescription = "call",
                                        modifier = Modifier.size(20.dp)
                                    )
                                }

                                IconButton(
                                    onClick = { /* TODO */ },
                                    modifier = Modifier.padding(start = 2.dp)

                                ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.trash),
                                        contentDescription = "trash",
                                        modifier = Modifier.size(25.dp)
                                            .clickable {
                                                navController.navigate("CardScreen/${contact.id}")
                                            }
                                    )
                                }
                            }
                        }

                    }
                }
            }
        }
    }
}

    if (showDialog.value) {
        AlertDialog(
            onDismissRequest = { showDialog.value = false },
            dismissButton = {
//                Button(onClick = {
//                    showDialog.value = false
//                    inputName = ""
//                    inputPhoneNumber = ""
//                    inputUrlImage = ""
//                }, modifier = Modifier.size(width = 80.dp, height = 35.dp),colors = ButtonDefaults.buttonColors(Color.Red)
//                ) {
                Text(text = "Hủy", color = Color.Black, fontWeight = FontWeight.Medium,
                    modifier = Modifier.clickable { showDialog.value = false }
                        .padding(top = 15.dp)

                )
//                }
            },
            confirmButton = {
                Button(onClick = {
                    viewModel.addContact(
                        ContactEntity(
                            urlImage = inputUrlImage,
                            name = inputName,
                            phoneNumber = inputPhoneNumber,
                            userId = userId
                        )
                    )
                    showDialog.value = false
                    inputName = ""
                    inputPhoneNumber = ""
                    inputUrlImage = ""
                }, modifier = Modifier.size(width = 115.dp, height = 40.dp),colors = ButtonDefaults.buttonColors(Color.Red)
                ) {
                    Text(text = "Lưu",fontWeight = FontWeight.Bold, fontSize = 20.sp)
                }
            },
            title = {
                Text(
                    text = "Thêm liên hệ",
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp,
                    modifier = Modifier.padding(5.dp)
                )
            },
            text = {
                Column {
                    ImagePickerSample() { selectedUrl ->
                        inputUrlImage = selectedUrl
                    }
                    OutlinedTextField(
                        leadingIcon = {
                            Image(
                                painter = painterResource(id = R.drawable.fullname),
                                contentDescription = "Họ và tên",
                                modifier = Modifier.size(20.dp)

                            )
                        },
                        value = inputName,
                        onValueChange = { inputName = it },
                        label = { Text("Họ và Tên") },
                        placeholder = { Text("Nhập tên liên hệ") },
                        modifier = Modifier.padding(10.dp)
                    )
                    OutlinedTextField(
                        leadingIcon = {
                            Image(
                                painter = painterResource(id = R.drawable.phone),
                                contentDescription = "Họ và tên",
                                modifier = Modifier.size(20.dp)
                            )
                        },
                        value = inputPhoneNumber,
                        onValueChange = { inputPhoneNumber = it },
                        label = { Text("Số điện thoại") },
                        placeholder = { Text("Nhập số điện thoại") },
                        modifier = Modifier.padding(10.dp)
                    )
                }
            }
        )
    }
}

@Composable
fun HeadBar() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .height(91.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.top_background),
            contentDescription = "Nền trên cùng",
            modifier = Modifier
                .fillMaxWidth()
                .height(91.dp)
        )

        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.phone),
                contentDescription = "Điện thoại",
                modifier = Modifier.size(30.dp)
            )
            Text(
                text = "LIÊN HỆ KHẨN CẤP",
                fontSize = 25.sp,
                color = Color.Red,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(start = 12.dp)
            )
        }
    }
}