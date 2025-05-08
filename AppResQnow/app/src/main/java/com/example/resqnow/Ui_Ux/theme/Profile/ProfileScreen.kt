    package com.example.resqnow.Ui_Ux.theme.Profile


    import androidx.compose.material3.TextField
    import android.widget.Toast
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
    import androidx.compose.foundation.layout.navigationBarsIgnoringVisibility
    import androidx.compose.foundation.layout.navigationBarsPadding
    import androidx.compose.foundation.layout.padding
    import androidx.compose.foundation.layout.safeContentPadding
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
    import androidx.compose.material3.Scaffold
    import androidx.compose.ui.Alignment
    import androidx.navigation.NavController
    import com.example.resqnow.Data.Api_and_Firebase.DataStore.readUserData
    import com.example.resqnow.Data.Api_and_Firebase.DataStore.saveUserData
    import com.example.resqnow.Ui_Ux.theme.Router.Screen
    import kotlinx.coroutines.CoroutineScope
    import kotlinx.coroutines.Dispatchers

    import kotlinx.coroutines.flow.collectLatest
    import kotlinx.coroutines.withContext


    @Composable
    fun ProfileScreen(
        userData: AppUserData?,
        googleAuthUiClient: GoogleAuthUiClient,
        onSignedOut: () -> Unit
        ,navController: NavController

    ) {


        //keyboard
        val keyboardController = LocalSoftwareKeyboardController.current
        //Textfield
        var name by remember { mutableStateOf("") }
        var number_phone by remember { mutableStateOf("") }
        var sex by remember { mutableStateOf("") }
        var age by remember { mutableStateOf("") }
        //Kiểm tra tuổi lỗi
        var ageError by remember { mutableStateOf("") }
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

        Scaffold(
            bottomBar = {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .navigationBarsPadding()
                        .fillMaxWidth()
                        .height(110.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.new_nav_bar),
                        contentDescription = "Logo",
                        modifier = Modifier
                            .fillMaxSize()
                            .align(Alignment.BottomCenter)
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically

                    ) {
                        IconButton(
                            onClick = { navController.navigate(Screen.HomePage1.route) },
                            modifier = Modifier
                                .padding(top = 40.dp)
                                .size(60.dp)
                        ) {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.home),
                                    contentDescription = "Logo",
                                    modifier = Modifier.size(41.dp, 39.dp)
                                )
                            }
                        }
                        IconButton(onClick = { navController.navigate(Screen.ContactScreen.route) },
                            modifier = Modifier
                                .padding(top = 40.dp)
                                .size(60.dp)
                        ) {

                            Image(
                                painter = painterResource(id = R.drawable.a),
                                contentDescription = "Logo",
                                alignment = Alignment.Center,
                                modifier = Modifier
                                    .size(width = 37.dp, height = 32.dp)
                            )
                        }
                        IconButton(onClick = {navController.navigate("Maps")}, modifier = Modifier
                            .padding(top = 40.dp)
                            .size(60.dp)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.hospital),
                                contentDescription = "Logo",
                                alignment = Alignment.Center,
                                modifier = Modifier
                                    .size(width = 35.dp, height = 35.dp)
                            )
                        }
                        IconButton(onClick = { Screen.ProfileScreen.route }, modifier = Modifier

                            .size(60.dp)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.c),
                                contentDescription = "Logo",
                                alignment = Alignment.Center,
                                modifier = Modifier
                                    .size(width = 35.dp, height = 35.dp)
                            )
                        }
                    }

                }
            }

        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.White)
                    .padding(paddingValues)
                    .padding(top = 2.dp)
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
                                    .clickable { navController.navigate(Screen.HomePage1.route) }
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
                            .size(width = 250.dp, height = 70.dp)
                            .border(
                                width = 2.dp,
                                color = Color.Red,
                                shape = RoundedCornerShape(15.dp)
                            )
                        , contentAlignment = Alignment.Center
                    ) {
                        Button(
                            onClick = {},
                            colors = ButtonDefaults.buttonColors(LightPink),
                            modifier = Modifier

                                .height (70.dp)
                                .align(Alignment.Center)
                                .border(
                                    width = 2.dp,
                                    color = Color.Cyan,
                                    shape = RoundedCornerShape(15.dp)
                                )
                                .background(color = LightPink, shape = RoundedCornerShape(15.dp))
                        ) {
                            Row(modifier = Modifier.fillMaxSize()
                            , verticalAlignment = Alignment.CenterVertically
                            ) {
                                userData?.profilePictureUrl?.let { url ->
                                    AsyncImage(
                                        model = userData?.profilePictureUrl ?: R.drawable.logo_resqnowpng,
                                        contentDescription = "Avatar",
                                        modifier = Modifier
                                            .size(60.dp)
                                            .weight(1f)
                                            .clip(RoundedCornerShape(30.dp))
                                            .background(
                                                color = Color.White,
                                                shape = RoundedCornerShape(30.dp)
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
                                        text = userData?.name ?: "Xin Chào Bạn!!",
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
                        .padding(start = 5.dp, top = 2.dp)
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
                            .height(350.dp)
                            .fillMaxWidth()
                            .padding(horizontal = 60.dp)
                            .padding(top = 2.dp)
                            .border(
                                width = 3.dp,
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
                            fun InputField(
                                label: String,
                                value: String,
                                onChange: (String) -> Unit,
                                hint: String
                            ) {
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
                                            Text(
                                                text = hint,
                                                fontSize = 11.sp,
                                                color = Color.Gray,
                                                modifier = Modifier.align(Alignment.Start)
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
                            InputField(
                                "Số di động",
                                number_phone,
                                { number_phone = it },
                                "Nhập số điện thoại"
                            )
                            InputField("Giới tính", sex, { sex = it }, "Nam / Nữ")
                            InputField(
                                label = "Tuổi",
                                value = age,
                                onChange = {
                                    age = it
                                    // Kiểm tra nếu là số hợp lệ
                                    val parsed = it.toIntOrNull()
                                    ageError = when {
                                        parsed == null -> "Vui lòng nhập số"
                                        parsed < 0 -> "Tuổi không được âm"
                                        else -> ""
                                    }
                                },
                                hint = "Nhập tuổi của bạn"
                            )
                        }
                    }
                }

                //Đăng xuất
                Row(
                    modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center
                ) {
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
                            .padding(top = 17.dp)
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


                    TextButton(
                        onClick = {
                            CoroutineScope(Dispatchers.IO).launch {
                                val parsedAge = age.toIntOrNull()
                                if (parsedAge == null) {
                                    withContext(Dispatchers.Main) {
                                        ageError = "Vui lòng nhập số"
                                        Toast.makeText(context, "Vui lòng nhập đủ thông tin", Toast.LENGTH_SHORT).show()
                                    }
                                    return@launch
                                }
                                if (parsedAge < 0) {
                                    withContext(Dispatchers.Main) {
                                        ageError = "Tuổi không được âm"
                                        Toast.makeText(context, "Tuổi của bạn không chính xác", Toast.LENGTH_SHORT).show()
                                    }
                                    return@launch
                                }

                                ageError = ""
                                saveUserData(context, name, number_phone, sex, age)
                                withContext(Dispatchers.Main) {
                                    Toast.makeText(context, "Lưu thành công", Toast.LENGTH_SHORT).show()
                                }
                            }
                        },
                        modifier = Modifier.padding(start = 12.dp, top = 30.dp)
                    ) {
                        Text(
                            text = "Xác Nhận",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.ExtraBold,
                        )
                    }
                }

            }
        }
    }