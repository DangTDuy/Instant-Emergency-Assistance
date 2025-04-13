package com.example.resqnow.Ui_Ux.theme.SignIn

import android.R.attr.text
import android.app.Activity
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.resqnow.Data.Api_and_Firebase.FireBaseGoogle.GoogleAuthUiClient
import com.example.resqnow.Data.Api_and_Firebase.FireBaseGoogle.SignInResult
import com.example.resqnow.R
import com.example.resqnow.Components.background
import com.example.resqnow.Data.Api_and_Firebase.FireBase.FirebaseEmail.AuthRepository
import com.example.resqnow.Data.Api_and_Firebase.FireBase.FirebaseEmail.AuthViewModel
import com.example.resqnow.Data.Api_and_Firebase.FireBase.FirebaseEmail.AuthViewModelFactory
import com.example.resqnow.Data.Api_and_Firebase.FireBase.FirebaseFacebook.FacebookAuthUiClient
import com.example.resqnow.Data.Api_and_Firebase.FireBaseGoogle.UserViewModel
import com.example.resqnow.Ui_Ux.theme.Router.Screen
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

@Composable
fun SignInScreen(
    navController: NavController,
    googleAuthUiClient: GoogleAuthUiClient,
    facebookAuthUiClient: FacebookAuthUiClient,
    userViewModel: UserViewModel = viewModel()
) {
    var emailError by rememberSaveable { mutableStateOf<String?>(null) }
    val authRepository = AuthRepository(FirebaseAuth.getInstance())
    val factory = AuthViewModelFactory(authRepository)
    val authViewModel: AuthViewModel = viewModel(factory = factory)
    var passwordVisible by rememberSaveable { mutableStateOf(false) }
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartIntentSenderForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            scope.launch {
                try {
                    val signInResult =
                        googleAuthUiClient.signInWithIntent(result.data ?: return@launch)
                    if (signInResult.data != null) {
                        userViewModel.setUser(signInResult.data)
                        navController.navigate(Screen.HomePage1.route) {
                            popUpTo(Screen.SignInScreen.route) { inclusive = true }
                        }
                    } else {
                        println("Sign-in failed: ${signInResult.errorMessage}")
                    }
                } catch (e: Exception) {
                    println("Error processing sign-in result: ${e.message}")
                }
            }
        }
    }


    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(background)
    ) {
        Image(
            painter = painterResource(R.drawable.top_background1),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter)
                .height(365.dp)
        )
        Row(modifier = Modifier
            .padding(start = 9.dp, top = 50.dp)
            .clickable{navController.navigate(Screen.HomePage1.route)},
        ) {
            Icon(painter = painterResource(R.drawable.home), contentDescription = null,
                tint = Color.Red ,
                modifier = Modifier
                    .size(15.dp)
                ,
            )
            Text(
                "Trang Chủ",fontSize = 20.sp, fontWeight = FontWeight.SemiBold,
                color = Color.White,
                modifier = Modifier
                    .zIndex(1f)
            )
        }

        Text(
            text = "Đăng Ký",
            fontSize = 40.sp,
            fontWeight = FontWeight.ExtraBold,
            modifier = Modifier
                .padding(top = 297.dp, start = 0.dp)
        )

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            modifier = Modifier
                .width(365.dp)
                .height(65.dp)
                .offset(x = 23.dp, y = 400.dp)
                .padding(end = 5.dp),
            leadingIcon = {
                Image(
                    painter = painterResource(R.drawable.email),
                    contentDescription = null,
                    modifier = Modifier
                        .size(24.dp)
                        .padding(start = 6.dp)
                )
            },
            label = { Text("Email", fontSize = 15.sp, fontWeight = FontWeight.SemiBold) },
            placeholder = { Text("Nhập email của bạn") },
            shape = RoundedCornerShape(15.dp),
        )

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            modifier = Modifier
                .width(365.dp)
                .height(65.dp)
                .offset(x = 23.dp, y = 470.dp)
                .padding(end = 5.dp),
            leadingIcon = {
                Image(
                    painter = painterResource(R.drawable.solar_lock_password_broken),
                    contentDescription = null,
                    modifier = Modifier
                        .size(24.dp)
                        .padding(start = 6.dp)
                )
            },
            trailingIcon = {
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(
                        imageVector = if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                        contentDescription = "Toggle password visibility"
                    )
                }
            },
            label = { Text("Mật khẩu", fontSize = 15.sp, fontWeight = FontWeight.SemiBold) },
            placeholder = { Text("Nhập mật khẩu của bạn") },
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            shape = RoundedCornerShape(15.dp),
        )

        Button(
            onClick = {
                scope.launch {
                    if (email.isBlank() || password.isBlank()) {
                        println("Vui lòng nhập đầy đủ Email và Mật khẩu")
                        Toast.makeText(context, "Vui lòng nhập đầy đủ Email và mật khẩu ", Toast.LENGTH_SHORT).show()

                        return@launch // Thoát khỏi coroutine chứ không phải return
                    }

                    if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                        println("Email không hợp lệ")
                        Toast.makeText(context, "Đăng ký không thành công : Email không hợp lệ", Toast.LENGTH_SHORT).show()
                        return@launch // Thoát khỏi coroutine chứ không phải return
                    }

                    // Kiểm tra email đã tồn tại chưa
                    authViewModel.checkIfEmailExists(email) { emailExists ->
                        if (emailExists) {
                            println("DEBUG: Email đã tồn tại, sẽ hiện Toast")
                            Toast.makeText(context, "Email này đã được sử dụng!", Toast.LENGTH_SHORT).show()
                            emailError = "Email này đã được sử dụng!"

                            return@checkIfEmailExists
                        } else {
                            authViewModel.registerWithEmail(email, password) { success, error ->
                                if (success) {
                                    navController.navigate(Screen.SignInSuccess.route) {
                                        popUpTo("SignInScreen") { inclusive = true }
                                    }
                                } else {
                                    println("Đăng ký thất bại: $error")
                                    Toast.makeText(context, "Đăng ký không thành công: Email này đã tồn tại", Toast.LENGTH_SHORT).show()
                                }
                            }
                        }
                    }
                }
            },
            modifier = Modifier
                .offset(x = 260.dp, y = 550.dp)
                .size(width = 127.dp, height = 37.dp)
                .clip(RoundedCornerShape(15.dp)),
            colors = ButtonDefaults.buttonColors(Color(0xFFEF291E))
        ) {
            Text(text = "Tiếp tục", fontSize = 17.sp, fontWeight = FontWeight.ExtraBold, color = Color.White)
        }

        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 630.dp)
        ) {
            Button(
                onClick = {
                    scope.launch {
                        val signInResult = facebookAuthUiClient.signInWithFacebook()

                        if (signInResult.data != null) {

                            userViewModel.setUser(signInResult.data)
                        navController.navigate(Screen.SignInSuccess.route) {
                                popUpTo(Screen.HomePage1.route) { inclusive = true }
                            }
                        } else {
                            println("Facebook sign-in failed: ${signInResult.errorMessage}")
                        }
                    }
                },
                modifier = Modifier
                    .size(width = 150.dp, height = 49.dp)
                    .border(3.dp, Color.Red, shape = RoundedCornerShape(15.dp)),
                colors = ButtonDefaults.buttonColors(Color(0xFFF5F5DC))
            ) {
                Row(
                    modifier = Modifier.fillMaxSize().clip(RoundedCornerShape(15.dp)),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(R.drawable.logos_facebook),
                        contentDescription = null,
                        modifier = Modifier.size(38.dp).padding(start = 0.dp)
                    )
                    Text("Facebook", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color.Black)
                }
            }
            Spacer(modifier = Modifier.width(16.dp))

            Button(
                onClick = {
                    scope.launch {
                        val signInIntentSender = googleAuthUiClient.signIn()
                        launcher.launch(
                            IntentSenderRequest.Builder(signInIntentSender ?: return@launch).build()
                        )
                    }
                },
                modifier = Modifier
                    .size(width = 150.dp, height = 49.dp)
                    .border(3.dp, Color.Red, shape = RoundedCornerShape(15.dp)),
                colors = ButtonDefaults.buttonColors(Color(0xFFF5F5DC))
            ) {
                Row(
                    modifier = Modifier.fillMaxSize().clip(RoundedCornerShape(15.dp)),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(R.drawable.logo_google),
                        contentDescription = null,
                        modifier = Modifier.size(39.dp).padding(start = 0.dp)
                    )
                    Text("Google", fontSize = 15.sp, fontWeight = FontWeight.Bold, color = Color.Black)
                }
            }
        }

        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Bạn đã có tài khoản? ",
                fontSize = 16.sp,
                modifier = Modifier.padding(top = 700.dp)
            )
            Text(
                text = "Đăng nhập",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(top = 700.dp)
                    .clickable {
                        navController.navigate("LoginScreen") // Điều hướng đến SignInScreen
                    }
            )
        }
        Row(modifier = Modifier.fillMaxSize(), horizontalArrangement = Arrangement.Center) {
            Text(
                text = "Quên mật khẩu?",
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(top = 750.dp)
            )
        }
    }
}