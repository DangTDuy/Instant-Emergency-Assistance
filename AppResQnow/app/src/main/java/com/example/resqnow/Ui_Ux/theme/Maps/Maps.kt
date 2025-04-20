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

// Data class để lưu thông tin bệnh viện
data class Hospital(
    val position: LatLng,
    val name: String,
    val placeId: String,
    val vicinity: String = ""
)

// Enum class cho trạng thái tải
enum class LoadingState {
    LOADING, SUCCESS, ERROR, LOCATION_ERROR
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun Maps(navController: NavHostController) {
    val locationPermissionState = rememberPermissionState(android.Manifest.permission.ACCESS_FINE_LOCATION)
    var permissionRequested by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        if (!locationPermissionState.status.isGranted && !permissionRequested) {
            locationPermissionState.launchPermissionRequest()
            permissionRequested = true
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        if (locationPermissionState.status.isGranted) {
            MapsContent(navController)
        } else {
            Column(
                Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Ứng dụng cần quyền truy cập vị trí để hiển thị bệnh viện gần bạn",
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
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@SuppressLint("MissingPermission")
@Composable
fun MapsContent(navController: NavHostController) {
    val context = LocalContext.current
    val hospitals = remember { mutableStateListOf<Hospital>() }
    val filteredHospitals = remember { mutableStateListOf<Hospital>() }
    val cameraPositionState = rememberCameraPositionState()
    var loadingState by remember { mutableStateOf(LoadingState.LOADING) }
    var showNoHospitalsDialog by remember { mutableStateOf(false) }
    var selectedHospital by remember { mutableStateOf<Hospital?>(null) }
    var currentLocation by remember { mutableStateOf<LatLng?>(null) }
    var errorMessage by remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()
    
    // Trạng thái cho thanh tìm kiếm
    var searchQuery by remember { mutableStateOf("") }
    var isSearching by remember { mutableStateOf(false) }
    val keyboardController = LocalSoftwareKeyboardController.current
    
    // Thêm trạng thái cho việc tìm kiếm theo Places API
    var searchingPlaces by remember { mutableStateOf(false) }
    
    LaunchedEffect(Unit) {
        try {
            val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
            
            try {
                val location = fusedLocationClient.getCurrentLocation(Priority.PRIORITY_HIGH_ACCURACY, null).await()
                if (location != null) {
                    val currentLatLng = LatLng(location.latitude, location.longitude)
                    currentLocation = currentLatLng
                    cameraPositionState.position = CameraPosition.fromLatLngZoom(currentLatLng, 14f)
                    
                    fetchNearbyHospitals(
                        context = context, 
                        location = currentLatLng,
                        onSuccess = { nearbyHospitals ->
                            hospitals.clear()
                            hospitals.addAll(nearbyHospitals)
                            filteredHospitals.clear()
                            filteredHospitals.addAll(nearbyHospitals)
                            loadingState = if (hospitals.isEmpty()) {
                                showNoHospitalsDialog = true
                                LoadingState.SUCCESS
                            } else {
                                LoadingState.SUCCESS
                            }
                        },
                        onError = { error ->
                            errorMessage = error
                            loadingState = LoadingState.ERROR
                        }
                    )
                } else {
                    loadingState = LoadingState.LOCATION_ERROR
                    errorMessage = "Không thể lấy vị trí của bạn. Vui lòng kiểm tra GPS đã bật."
                }
            } catch (e: Exception) {
                loadingState = LoadingState.LOCATION_ERROR
                errorMessage = "Không thể truy cập vị trí: ${e.localizedMessage}"
            }
        } catch (e: Exception) {
            loadingState = LoadingState.ERROR
            errorMessage = "Đã xảy ra lỗi: ${e.localizedMessage}"
        }
    }
    
    // Hiệu ứng khi thay đổi truy vấn tìm kiếm
    LaunchedEffect(searchQuery) {
        if (searchQuery.isEmpty()) {
            // Nếu truy vấn trống, hiển thị tất cả bệnh viện
            filteredHospitals.clear()
            filteredHospitals.addAll(hospitals)
        } else {
            // Lọc dựa trên tên hoặc địa chỉ
            filteredHospitals.clear()
            filteredHospitals.addAll(hospitals.filter { hospital ->
                hospital.name.contains(searchQuery, ignoreCase = true) ||
                hospital.vicinity.contains(searchQuery, ignoreCase = true)
            })
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        // Content area - Chiếm toàn bộ màn hình, nhưng có padding ở dưới để tránh che khuất bởi thanh điều hướng
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 80.dp)  // Lưu ý: Phải có padding bottom cho thanh điều hướng
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
                    // Google Maps
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
                                    selectedHospital = hospital
                                    true
                                }
                            )
                        }
                    }
                    
                    // Thanh tìm kiếm ở phía trên cùng
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
                                onValueChange = { searchQuery = it },
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
                                        if (searchQuery.isNotEmpty()) {
                                            scope.launch {
                                                searchingPlaces = true
                                                searchHospitalsByQuery(
                                                    context = context,
                                                    query = searchQuery,
                                                    currentLocation = currentLocation,
                                                    onSuccess = { results ->
                                                        searchingPlaces = false
                                                        if (results.isNotEmpty()) {
                                                            // Nếu có kết quả từ Places API, thêm vào danh sách và hiển thị
                                                            filteredHospitals.clear()
                                                            filteredHospitals.addAll(results)
                                                            
                                                            // Di chuyển camera đến kết quả đầu tiên
                                                            cameraPositionState.position = CameraPosition.fromLatLngZoom(
                                                                results[0].position,
                                                                14f
                                                            )
                                                        } else {
                                                            // Hiển thị thông báo không tìm thấy
                                                            showNoHospitalsDialog = true
                                                        }
                                                    },
                                                    onError = { error ->
                                                        searchingPlaces = false
                                                        errorMessage = error
                                                    }
                                                )
                                            }
                                        }
                                    }
                                )
                            )
                            
                            if (searchQuery.isNotEmpty()) {
                                IconButton(
                                    onClick = {
                                        searchQuery = ""
                                        // Hiển thị lại tất cả bệnh viện gần đó
                                        filteredHospitals.clear()
                                        filteredHospitals.addAll(hospitals)
                                    }
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Close,
                                        contentDescription = "Xóa"
                                    )
                                }
                            }
                        }
                    }
                    
                    // Hiển thị thông tin về số lượng bệnh viện tìm thấy
                    if (filteredHospitals.isNotEmpty()) {
                        Box(
                            modifier = Modifier
                                .padding(horizontal = 16.dp, vertical = 72.dp)
                                .background(Color.White.copy(alpha = 0.8f), RoundedCornerShape(4.dp))
                                .padding(8.dp)
                                .align(Alignment.TopCenter)
                                .clickable {
                                    if (filteredHospitals.size == 1) {
                                        // Nếu chỉ có 1 kết quả, tự động chọn
                                        selectedHospital = filteredHospitals[0]
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
                        
                        Button(onClick = {
                            loadingState = LoadingState.LOADING
                            scope.launch {
                                val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
                                try {
                                    val location = fusedLocationClient.getCurrentLocation(Priority.PRIORITY_HIGH_ACCURACY, null).await()
                                    if (location != null) {
                                        currentLocation = LatLng(location.latitude, location.longitude)
                                        cameraPositionState.position = CameraPosition.fromLatLngZoom(currentLocation!!, 14f)
                                        
                                        fetchNearbyHospitals(
                                            context = context,
                                            location = currentLocation!!,
                                            onSuccess = { nearbyHospitals ->
                                                hospitals.clear()
                                                hospitals.addAll(nearbyHospitals)
                                                filteredHospitals.clear()
                                                filteredHospitals.addAll(nearbyHospitals)
                                                loadingState = LoadingState.SUCCESS
                                            },
                                            onError = { error ->
                                                errorMessage = error
                                                loadingState = LoadingState.ERROR
                                            }
                                        )
                                    } else {
                                        loadingState = LoadingState.LOCATION_ERROR
                                    }
                                } catch (e: Exception) {
                                    loadingState = LoadingState.LOCATION_ERROR
                                }
                            }
                        }) {
                            Text("Thử lại")
                        }
                    }
                }
            }
        }
        
        // Navigation Bar - Định vị ở phía dưới màn hình
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            verticalArrangement = Arrangement.Bottom
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .navigationBarsPadding()
                    .height(80.dp),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    var showDialog by remember { mutableStateOf(false) }

                    Image(
                        painter = painterResource(R.drawable.trangchu),
                        contentDescription = "Home Page",
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .size(140.dp)
                            .clickable { navController.popBackStack() }
                    )

                    Spacer(modifier = Modifier.width(10.dp))
                    Image(
                        painter = painterResource(R.drawable.a),
                        contentDescription = "Contact",
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .size(50.dp)
                            .clickable {
                                if (navController.graph.findNode("ContactScreen") != null) {
                                    navController.navigate("ContactScreen")
                                }
                            }
                    )

                    Spacer(modifier = Modifier.width(10.dp))
                    Image(
                        painter = painterResource(R.drawable.hospital),
                        contentDescription = "Maps",
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .size(40.dp)
                            .clickable {
                                showDialog = true
                            }
                    )
                    
                    if (showDialog) {
                        AlertDialog(
                            onDismissRequest = { showDialog = false },
                            title = { Text("Thông báo") },
                            text = { Text("Bạn đang ở trang Bản đồ.") },
                            confirmButton = {
                                TextButton(onClick = { showDialog = false }) {
                                    Text("Đóng")
                                }
                            }
                        )
                    }

                    Spacer(modifier = Modifier.width(10.dp))
                    Image(
                        painter = painterResource(R.drawable.c),
                        contentDescription = "Profile",
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .size(40.dp)
                            .clickable {
                                if (navController.graph.findNode("ProfileScreen") != null) {
                                    navController.navigate("ProfileScreen")
                                }
                            }
                    )
                }
            }
        }
    }

    // Dialog chọn hành động khi click vào bệnh viện
    selectedHospital?.let { hospital ->
        AlertDialog(
            onDismissRequest = { selectedHospital = null },
            title = { Text(hospital.name, fontWeight = FontWeight.Bold) },
            text = { 
                Column {
                    if (hospital.vicinity.isNotEmpty()) {
                        Text("Địa chỉ: ${hospital.vicinity}")
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                    Text("Bạn muốn tìm đường đến bệnh viện này?") 
                }
            },
            confirmButton = {
                Button(onClick = {
                    currentLocation?.let { current ->
                        // Mở Google Maps với hướng dẫn đường đi
                        val uri = Uri.parse(
                            "google.navigation:q=${hospital.position.latitude},${hospital.position.longitude}&mode=d"
                        )
                        val intent = Intent(Intent.ACTION_VIEW, uri)
                        intent.setPackage("com.google.android.apps.maps")
                        try {
                            context.startActivity(intent)
                        } catch (e: Exception) {
                            // Fallback nếu không có Google Maps
                            val browserUri = Uri.parse(
                                "https://www.google.com/maps/dir/?api=1&destination=${hospital.position.latitude},${hospital.position.longitude}"
                            )
                            val browserIntent = Intent(Intent.ACTION_VIEW, browserUri)
                            context.startActivity(browserIntent)
                        }
                    }
                    selectedHospital = null
                }) {
                    Text("Tìm đường")
                }
            },
            dismissButton = {
                Button(onClick = { selectedHospital = null }) {
                    Text("Hủy")
                }
            }
        )
    }

    if (showNoHospitalsDialog) {
        AlertDialog(
            onDismissRequest = { showNoHospitalsDialog = false },
            title = { Text("Không có bệnh viện gần đây") },
            text = { Text("Chúng tôi không thể tìm thấy bệnh viện phù hợp với yêu cầu tìm kiếm của bạn.") },
            confirmButton = {
                Button(onClick = { showNoHospitalsDialog = false }) {
                    Text("Đóng")
                }
            }
        )
    }
}

// Hàm tách riêng để lấy dữ liệu bệnh viện gần đó
private fun fetchNearbyHospitals(
    context: android.content.Context,
    location: LatLng,
    onSuccess: (List<Hospital>) -> Unit,
    onError: (String) -> Unit
) {
    // Sử dụng API key cố định thay vì BuildConfig
    val apiKey = "AIzaSyAsL_GBsnfBifXu9CKSVGMxPKHq8sbiJek"
    
    val url = "https://maps.googleapis.com/maps/api/place/nearbysearch/json" +
            "?location=${location.latitude},${location.longitude}" +
            "&radius=3000" +
            "&type=hospital" +
            "&key=$apiKey"

    val queue = Volley.newRequestQueue(context)
    val hospitalsList = mutableListOf<Hospital>()
    
    val request = object : StringRequest(
        Request.Method.GET, url,
        Response.Listener { response ->
            try {
                val json = JSONObject(response)
                val results = json.getJSONArray("results")
                
                for (i in 0 until results.length()) {
                    val place = results.getJSONObject(i)
                    val loc = place.getJSONObject("geometry").getJSONObject("location")
                    val lat = loc.getDouble("lat")
                    val lng = loc.getDouble("lng")
                    val name = place.getString("name")
                    val placeId = place.getString("place_id")
                    val vicinity = if (place.has("vicinity")) place.getString("vicinity") else ""
                    
                    hospitalsList.add(Hospital(LatLng(lat, lng), name, placeId, vicinity))
                }
                
                onSuccess(hospitalsList)
            } catch (e: Exception) {
                onError("Không thể xử lý dữ liệu: ${e.localizedMessage}")
                Log.e("JSON_ERROR", "Error parsing JSON: ${e.message}")
            }
        },
        Response.ErrorListener { error ->
            onError("Lỗi kết nối: ${error.message ?: "Không thể kết nối đến máy chủ"}")
            Log.e("API_ERROR", error.toString())
        }
    ) {
        override fun getHeaders(): MutableMap<String, String> {
            val headers = HashMap<String, String>()
            headers["User-Agent"] = "ResQnow Android App"
            return headers
        }
    }
    
    request.setShouldCache(false)
    queue.add(request)
}

// Hàm mới để tìm kiếm bệnh viện bằng query
private fun searchHospitalsByQuery(
    context: android.content.Context,
    query: String,
    currentLocation: LatLng?,
    onSuccess: (List<Hospital>) -> Unit,
    onError: (String) -> Unit
) {
    val apiKey = "AIzaSyAsL_GBsnfBifXu9CKSVGMxPKHq8sbiJek"
    
    // Tạo URL cho tìm kiếm text
    val locationParam = if (currentLocation != null) 
        "&location=${currentLocation.latitude},${currentLocation.longitude}&radius=10000"
    else 
        ""
    
    val url = "https://maps.googleapis.com/maps/api/place/textsearch/json" +
            "?query=hospital+$query" +
            locationParam +
            "&type=hospital" +
            "&key=$apiKey"
    
    val queue = Volley.newRequestQueue(context)
    val hospitalsList = mutableListOf<Hospital>()
    
    val request = object : StringRequest(
        Request.Method.GET, url,
        Response.Listener { response ->
            try {
                val json = JSONObject(response)
                val results = json.getJSONArray("results")
                
                for (i in 0 until results.length()) {
                    val place = results.getJSONObject(i)
                    val loc = place.getJSONObject("geometry").getJSONObject("location")
                    val lat = loc.getDouble("lat")
                    val lng = loc.getDouble("lng")
                    val name = place.getString("name")
                    val placeId = place.getString("place_id")
                    val vicinity = if (place.has("formatted_address")) 
                                    place.getString("formatted_address") 
                                  else if (place.has("vicinity"))
                                    place.getString("vicinity")
                                  else ""
                    
                    hospitalsList.add(Hospital(LatLng(lat, lng), name, placeId, vicinity))
                }
                
                onSuccess(hospitalsList)
            } catch (e: Exception) {
                onError("Không thể xử lý dữ liệu tìm kiếm: ${e.localizedMessage}")
                Log.e("JSON_ERROR", "Error parsing JSON search: ${e.message}")
            }
        },
        Response.ErrorListener { error ->
            onError("Lỗi kết nối tìm kiếm: ${error.message ?: "Không thể kết nối đến máy chủ"}")
            Log.e("API_ERROR", "Search error: ${error.toString()}")
        }
    ) {
        override fun getHeaders(): MutableMap<String, String> {
            val headers = HashMap<String, String>()
            headers["User-Agent"] = "ResQnow Android App"
            return headers
        }
    }
    
    request.setShouldCache(false)
    queue.add(request)
}