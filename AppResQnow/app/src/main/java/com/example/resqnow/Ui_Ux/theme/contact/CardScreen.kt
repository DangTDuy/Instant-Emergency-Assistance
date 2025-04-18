package com.example.resqnow.Ui_Ux.theme.contact


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.resqnow.viewModel.ContactViewModel
import com.example.resqnow.R


@Composable
fun CardScreen(
    navController: NavController,
    viewModel: ContactViewModel,
    contactId: Int // Chuyển từ ContactEntity sang contactId
) {
    val showDialog = remember { mutableStateOf(false) }
    val contact by viewModel.selectedContact.collectAsState() // Collect thông tin liên hệ theo ID

    LaunchedEffect(contactId) {
        viewModel.loadContactById(contactId) // Load liên hệ theo ID
    }

    if (contact != null) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(6.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    AsyncImage(
                        model = contact!!.urlImage,
                        contentDescription = "Contact Image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(120.dp)
                            .clip(CircleShape)
                            .align(Alignment.CenterHorizontally)
                    )

                    DetailRow(label = "ID", value = contact!!.id.toString())
                    DetailRow(label = "Họ và Tên", value = contact!!.name)
                    DetailRow(label = "Số điện thoại", value = contact!!.phoneNumber)
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                ActionButton(
                    iconRes = R.drawable.trash,
                    label = "Xóa",
                    onClick = { showDialog.value = true }
                )

                ActionButton(
                    iconRes = R.drawable.trash,
                    label = "Chỉnh Sửa",
                    onClick = {
                        // TODO: Viết logic chuyển đến màn hình cập nhật
                    }
                )
            }
        }

        if (showDialog.value) {
            AlertDialog(
                onDismissRequest = { showDialog.value = false },
                title = {
                    Text(
                        text = "Xóa Liên Hệ",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                },
                text = {
                    Text(
                        text = "Bạn có chắc muốn xóa người này khỏi danh bạ?",
                        fontSize = 16.sp
                    )
                },
                confirmButton = {
                    Button(
                        onClick = {
                            viewModel.deleteContact(contact!!) // Sử dụng contact đã load
                            showDialog.value = false
                            navController.popBackStack()
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Red,
                            contentColor = Color.White
                        )
                    ) {
                        Text("Xóa")
                    }
                },
                dismissButton = {

                    Text("No", modifier = Modifier.
                    clickable { showDialog.value = false}
                        .clickable{navController.navigate("ContactScreen")}
                        .padding(top = 20.dp)
                    )

                }
            )
        }
    } else {
        // Placeholder khi chưa có dữ liệu
        Text(text = "Loading...")
    }
}

@Composable
fun DetailRow(label: String, value: String?) {
    Row(
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "$label:",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            modifier = Modifier.width(180.dp)
        )
        Text(
            text = value.orEmpty(),
            fontSize = 18.sp
        )
    }
}

@Composable
fun ActionButton(iconRes: Int, label: String, onClick: () -> Unit) {
    OutlinedIconButton(
        onClick = onClick,
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .size(width = 140.dp, height = 100.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Icon(
                painter = painterResource(id = iconRes),
                contentDescription = label,
                modifier = Modifier.size(32.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = label, fontSize = 14.sp)
        }
    }
}