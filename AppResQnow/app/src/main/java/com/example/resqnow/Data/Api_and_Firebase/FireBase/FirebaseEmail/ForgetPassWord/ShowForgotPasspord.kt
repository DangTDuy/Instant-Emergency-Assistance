package com.example.resqnow.Data.Api_and_Firebase.FireBase.FirebaseEmail.ForgetPassWord

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun ForgotPasswordDialog(
    onDismiss: () -> Unit
) {
    val context = LocalContext.current
    var emailInput by remember { mutableStateOf("") }
    var showSuccessDialog by remember { mutableStateOf(false) }

    if (showSuccessDialog) {
        AlertDialog(
            onDismissRequest = { showSuccessDialog = false },
            title = { Text("Yêu cầu thành công") },
            text = { Text("Đã gửi yêu cầu thay đổi mật khẩu đến Gmail của bạn.") },
            confirmButton = {
                Button(onClick = {
                    showSuccessDialog = false
                    onDismiss() // đóng dialog chính sau khi gửi thành công
                },colors = ButtonDefaults.buttonColors(Color.Red)
                    ,shape = ButtonDefaults.elevatedShape) {
                    Text("Đóng",color = Color.White, fontWeight = FontWeight.Bold,)
                }
            }
        )
    }

    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = { Text("Quên mật khẩu") },
        text = {
            Column {
                Text("Nhập email để nhận link đặt lại mật khẩu:")
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = emailInput,
                    onValueChange = { emailInput = it },
                    label = { Text("Email") }
                )
            }
        },
        confirmButton = {
            Button(onClick = {
                sendPasswordResetEmail(
                    context = context,
                    email = emailInput,
                    onSuccess = {
                        showSuccessDialog = true
                    }
                )
            },colors = ButtonDefaults.buttonColors(Color.Red)
                ,shape = ButtonDefaults.elevatedShape
            ) {
                Text("Gửi",color = Color.White, fontWeight = FontWeight.Bold,)
            }
        },
        dismissButton = {
                    Text(
                        "Hủy", color = Color.Black, fontWeight = FontWeight.W500,
                        modifier = Modifier
                            .padding(top = 15.dp)
                            .clickable {
                                onDismiss()
                            }, textAlign = TextAlign.Center
                    )
        }
    )
}
