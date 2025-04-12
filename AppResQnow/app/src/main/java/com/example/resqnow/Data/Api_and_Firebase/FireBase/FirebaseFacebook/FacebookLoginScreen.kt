package com.example.resqnow.Data.Api_and_Firebase.FireBase.FirebaseFacebook



import android.app.Activity
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun FacebookLoginScreen() {
    val context = LocalContext.current
    val viewModel: FacebookLoginViewModel = viewModel()
    val isLoggedIn by viewModel.isLoggedIn.collectAsState()
    val userName by viewModel.userName.collectAsState()
    val authManager = remember { FacebookAuthManager(context as Activity, viewModel) }

    LaunchedEffect(Unit) {
        viewModel.checkAccessToken()
        authManager.initFacebookLogin()
    }

    Surface {
        Column {
            if (isLoggedIn) {
                Text("Đăng nhập thành công!")
                // Hiển thị tên người dùng Facebook
                if (userName != null) {
                    Text("Xin chào, $userName!")
                }
                Button(onClick = { viewModel.logoutFacebook() }) {
                    Text("Đăng xuất")
                }
            } else {
                Button(onClick = { authManager.loginWithFacebook() }) {
                    Text("Đăng nhập bằng Facebook")
                }
            }
        }
    }
}
