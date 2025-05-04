package com.example.resqnow.Ui_Ux.theme.contact


import android.R.attr.onClick
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
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
import com.example.resqnow.Ui_Ux.theme.Router.Screen


@Composable
fun CardScreen(
    navController: NavController,
    viewModel: ContactViewModel,
    contactId: Int // Chuyển từ ContactEntity sang contactId
) {
    val showDialog = remember { mutableStateOf(false) }
    val updateDialog = remember { mutableStateOf(false) }

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
                    iconRes = R.drawable.baseline_restore_from_trash_24,
                    label = "Xóa",
                    onClick = { showDialog.value = true }
                )

                ActionButton(
                    iconRes = R.drawable.baseline_update_24,
                    label = "Chỉnh Sửa",
                    onClick = {
                        updateDialog.value = true
                    }
                )
            }
            Spacer(modifier = Modifier.height(15.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 40.dp),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Button(
                    onClick = {}
                    ,shape = RoundedCornerShape(12.dp)
                    ,colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = Color.Black
                    )
                    ,modifier = Modifier
                        .padding(0.dp)
                        .border(width = 1.dp, color = Color.Black, shape = RoundedCornerShape(12.dp) )
                ){
                    Image(painter = painterResource(id = R.drawable.exit),contentDescription = null,
                        modifier = Modifier
                            .size(22.dp)

                        )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "Quay lại",fontSize = 17.sp)

                }
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

                    Text("No", modifier = Modifier.clickable { showDialog.value = false }
                        .clickable { navController.navigate(Screen.ContactScreen.route) }
                        .padding(top = 20.dp)
                    )

                }
            )
        }
        if (updateDialog.value) {
            // State cho các trường nhập
            var inputUrl by remember { mutableStateOf(contact?.urlImage ?: "") }
            var inputName by remember { mutableStateOf(contact?.name ?: "") }
            var inputPhone by remember { mutableStateOf(contact?.phoneNumber ?: "") }

            AlertDialog(
                onDismissRequest = { updateDialog.value = false },
                title = {
                    Text(
                        text = "Chỉnh sửa liên hệ",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                },
                text = {
                    Column {
                        // Chọn hình ảnh
                        ImagePickerSample { selectedUrl ->
                            inputUrl = selectedUrl
                        }

                        // Nhập tên
                        OutlinedTextField(
                            value = inputName,
                            onValueChange = { inputName = it },
                            label = { Text("Họ và Tên") },
                            placeholder = { Text("Nhập họ và tên") },
                            modifier = Modifier.padding(vertical = 8.dp)
                        )

                        // Nhập số điện thoại
                        OutlinedTextField(
                            value = inputPhone,
                            onValueChange = { inputPhone = it },
                            label = { Text("Số điện thoại") },
                            placeholder = { Text("Nhập số điện thoại") },
                            modifier = Modifier.padding(vertical = 8.dp)
                        )
                    }
                },
                confirmButton = {
                    Button(onClick = {
                        val updatedContact = contact!!.copy(
                            name = inputName,
                            phoneNumber = inputPhone,
                            urlImage = inputUrl
                        )
                        viewModel.updateContact(updatedContact)
                        updateDialog.value = false
                        navController.popBackStack()
                    }) {
                        Text("Lưu thay đổi")
                    }
                },
                dismissButton = {
                    OutlinedButton(onClick = { updateDialog.value = false }) {
                        Text("Hủy")
                    }
                }
            )
        }
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