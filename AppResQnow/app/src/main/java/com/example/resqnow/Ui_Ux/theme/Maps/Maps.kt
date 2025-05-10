package com.example.resqnow.Ui_Ux.theme.Maps

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Surface
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.resqnow.R
import com.google.accompanist.permissions.*
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import org.json.JSONObject
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.resqnow.Ui_Ux.theme.Router.Screen

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun Maps(navController: NavHostController) {
    val locationPermissionState = rememberPermissionState(android.Manifest.permission.ACCESS_FINE_LOCATION)
    var permissionRequested by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val viewModel: MapsViewModel = viewModel(factory = object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MapsViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return MapsViewModel(context) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    })
    // Thêm trạng thái để kiểm soát dialog thông báo Google Maps
    var showMapsDialog by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        if (!locationPermissionState.status.isGranted && !permissionRequested) {
            locationPermissionState.launchPermissionRequest()
            permissionRequested = true
        } else if (locationPermissionState.status.isGranted) {
            viewModel.fetchLocationAndHospitals()
        } else {
            viewModel.loadHospitalsFromFixedList() // Tải danh sách cố định nếu không có quyền
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        if (locationPermissionState.status.isGranted) {
            MapsContent(navController, viewModel)
        } else {
            Column(
                Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Ứng dụng cần quyền truy cập vị trí để hiển thị bệnh viện gần bạn. Hiện đang hiển thị danh sách bệnh viện cố định.",
                    modifier = Modifier.padding(16.dp),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Medium
                )
                Button(onClick = {
                    if (!locationPermissionState.status.isGranted) {
                        locationPermissionState.launchPermissionRequest()
                    }
                }) {
                    Text("Cấp quyền vị trí")
                }
            }
        }

        // Thêm AlertDialog để thông báo về ứng dụng Google Maps
        if (showMapsDialog) {
            AlertDialog(
                onDismissRequest = { showMapsDialog = false },
                title = { Text("Thông báo") },
                text = { Text("Bạn cần tải ứng dụng Maps để sử dụng trọn vẹn tính năng!") },
                confirmButton = {
                    TextButton(onClick = { showMapsDialog = false }) {
                        Text("Đóng")
                    }
                }
            )
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@SuppressLint("MissingPermission")
@Composable
fun MapsContent(navController: NavHostController, viewModel: MapsViewModel) {
    val context = LocalContext.current
    val hospitals by viewModel.hospitals.collectAsState()
    val filteredHospitals by viewModel.filteredHospitals.collectAsState()
    val loadingState by viewModel.loadingState.collectAsState()
    val showNoHospitalsDialog by viewModel.showNoHospitalsDialog.collectAsState()
    val selectedHospital by viewModel.selectedHospital.collectAsState()
    val currentLocation by viewModel.currentLocation.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()
    val searchingPlaces by viewModel.searchingPlaces.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState()
    val cameraPositionState = rememberCameraPositionState()
    val keyboardController = LocalSoftwareKeyboardController.current

    // Khi có vị trí mới, cập nhật camera
    LaunchedEffect(currentLocation) {
        currentLocation?.let {
            cameraPositionState.position = CameraPosition.fromLatLngZoom(it, 14f)
        } ?: run {
            // Nếu không có vị trí, đặt camera mặc định tại trung tâm TP.HCM
            cameraPositionState.position = CameraPosition.fromLatLngZoom(LatLng(10.7769, 106.7009), 12f)
        }
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(top = 60.dp)
    ) {
        Row(
            modifier = Modifier.padding(start = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "ResQnow",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.weight(1f))
        }
        Spacer(modifier = Modifier.height(5.dp))
        Box(modifier = Modifier
            .weight(1f)
            .fillMaxWidth()
        ) {
            when (loadingState) {
                LoadingState.LOADING -> {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.White.copy(alpha = 0.8f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
                            Spacer(modifier = Modifier.height(16.dp))
                            Text("Đang tìm kiếm bệnh viện gần bạn...", fontWeight = FontWeight.Medium)
                        }
                    }
                }
                LoadingState.SUCCESS -> {
                    GoogleMap(
                        modifier = Modifier.fillMaxSize(),
                        cameraPositionState = cameraPositionState,
                        properties = MapProperties(isMyLocationEnabled = true)
                    ) {
                        filteredHospitals.forEach { hospital ->
                            Marker(
                                state = MarkerState(position = hospital.position),
                                title = hospital.name,
                                snippet = "Nhấn để tìm đường",
                                onClick = {
                                    viewModel.setSelectedHospital(hospital)
                                    true
                                }
                            )
                        }
                    }
                    Surface(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                            .align(Alignment.TopCenter),
                        color = Color.White,
                        shape = RoundedCornerShape(8.dp),
                        shadowElevation = 4.dp
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            if (searchingPlaces) {
                                CircularProgressIndicator(
                                    modifier = Modifier
                                        .size(24.dp)
                                        .padding(end = 8.dp),
                                    strokeWidth = 2.dp
                                )
                            } else {
                                Icon(
                                    imageVector = Icons.Default.Search,
                                    contentDescription = "Tìm kiếm",
                                    modifier = Modifier.padding(end = 8.dp)
                                )
                            }
                            OutlinedTextField(
                                value = searchQuery,
                                onValueChange = { viewModel.setSearchQuery(it) },
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(vertical = 4.dp),
                                placeholder = { Text("Tìm bệnh viện...") },
                                singleLine = true,
                                colors = androidx.compose.material3.TextFieldDefaults.colors(
                                    unfocusedContainerColor = Color.Transparent,
                                    focusedContainerColor = Color.Transparent,
                                    unfocusedIndicatorColor = Color.Transparent,
                                    focusedIndicatorColor = Color.Transparent
                                ),
                                keyboardOptions = KeyboardOptions(
                                    imeAction = ImeAction.Search
                                ),
                                keyboardActions = KeyboardActions(
                                    onSearch = {
                                        keyboardController?.hide()
                                        //if (searchQuery.isNotEmpty()) {
                                          //  viewModel.searchHospitalsByQuery(searchQuery)
                                        //}
                                    }
                                )
                            )
                            if (searchQuery.isNotEmpty()) {
                                IconButton(
                                    onClick = { viewModel.setSearchQuery("") }
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Close,
                                        contentDescription = "Xóa"
                                    )
                                }
                            }
                        }
                    }
                    if (filteredHospitals.isNotEmpty()) {
                        Box(
                            modifier = Modifier
                                .padding(horizontal = 16.dp, vertical = 72.dp)
                                .background(Color.White.copy(alpha = 0.8f), RoundedCornerShape(4.dp))
                                .padding(8.dp)
                                .align(Alignment.TopCenter)
                                .clickable {
                                    if (filteredHospitals.size == 1) {
                                        viewModel.setSelectedHospital(filteredHospitals[0])
                                    }
                                }
                        ) {
                            Text(
                                text = "Đã tìm thấy ${filteredHospitals.size} bệnh viện",
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }
                }
                LoadingState.ERROR, LoadingState.LOCATION_ERROR -> {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = errorMessage,
                            textAlign = TextAlign.Center,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(onClick = { viewModel.fetchLocationAndHospitals() }) {
                            Text("Thử lại")
                        }
                    }
                }
            }
        }
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .navigationBarsPadding()
                .height(100.dp)
                .background(Color.White)
        ) {
            Image(
                painter = painterResource(id = R.drawable.bg111),
                contentDescription = "",
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
                        .offset(y = 18.dp)
                        .padding(end =25.dp, bottom = 30.dp)
                        .size(width = 39.dp, height = 35.dp)


                )
                Image(
                    painter = painterResource(id = R.drawable.hospital),
                    contentDescription = "Bệnh viện",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .offset(y = -15.dp, x = 7.dp)
                        .size(width = 35.dp, height = 35.dp)
                        .clickable{
                            navController.navigate("Maps")
                        }
                )
                Image(
                    painter = painterResource(id = R.drawable.c),
                    contentDescription = "Tài khoản",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .offset(x = 5.dp)
                        .size(width = 35.dp, height = 35.dp)
                        .clickable{navController.navigate(Screen.ProfileScreen.route)}
                )
            }
        }
    }
    // Dialog chọn hành động khi click vào bệnh viện
    selectedHospital?.let { hospital ->
        AlertDialog(
            onDismissRequest = { viewModel.setSelectedHospital(null) },
            title = { Text(hospital.name, fontWeight = FontWeight.Bold) },
            text = {
                Column {
                    if (hospital.vicinity.isNotEmpty()) {
                        Text("Địa chỉ: ${hospital.vicinity}")
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                    hospital.phoneNumber?.let { Text("Số điện thoại: $it") }
                    Spacer(modifier = Modifier.height(8.dp))
                    hospital.specialties?.let { Text("Chuyên khoa: $it") }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Bạn muốn tìm đường đến bệnh viện này?")
                }
            },
            confirmButton = {
                Button(onClick = {
                    currentLocation?.let { current ->
                        val uri = Uri.parse(
                            "google.navigation:q=${hospital.position.latitude},${hospital.position.longitude}&mode=d"
                        )
                        val intent = Intent(Intent.ACTION_VIEW, uri)
                        intent.setPackage("com.google.android.apps.maps")
                        try {
                            context.startActivity(intent)
                        } catch (e: Exception) {
                            val browserUri = Uri.parse(
                                "https://www.google.com/maps/dir/?api=1&destination=${hospital.position.latitude},${hospital.position.longitude}"
                            )
                            val browserIntent = Intent(Intent.ACTION_VIEW, browserUri)
                            context.startActivity(browserIntent)
                        }
                    }
                    viewModel.setSelectedHospital(null)
                }) {
                    Text("Tìm đường")
                }
            },
            dismissButton = {
                Button(onClick = { viewModel.setSelectedHospital(null) }) {
                    Text("Hủy")
                }
            }
        )
    }
    if (showNoHospitalsDialog) {
        AlertDialog(
            onDismissRequest = { viewModel.setShowNoHospitalsDialog(false) },
            title = { Text("Không có bệnh viện gần đây") },
            text = { Text("Chúng tôi không thể tìm thấy bệnh viện phù hợp với yêu cầu tìm kiếm của bạn.") },
            confirmButton = {
                Button(onClick = { viewModel.setShowNoHospitalsDialog(false) }) {
                    Text("Đóng")
                }
            }
        )
    }
}