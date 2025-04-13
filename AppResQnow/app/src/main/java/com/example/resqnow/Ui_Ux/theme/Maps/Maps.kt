package com.example.resqnow.Ui_Ux.theme.Maps

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.AlertDialog
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.accompanist.permissions.*
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*
import kotlinx.coroutines.tasks.await
import org.json.JSONObject

// Data class để lưu thông tin bệnh viện
data class Hospital(
    val position: LatLng,
    val name: String,
    val placeId: String
)

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun Maps(navController: NavHostController) {
    val locationPermissionState = rememberPermissionState(android.Manifest.permission.ACCESS_FINE_LOCATION)

    LaunchedEffect(Unit) {
        if (!locationPermissionState.status.isGranted) {
            locationPermissionState.launchPermissionRequest()
        }
    }

    if (locationPermissionState.status.isGranted) {
        MapsContent(navController)
    } else {
        Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
            Text(
                text = "Ứng dụng cần quyền truy cập vị trí để hoạt động",
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

@SuppressLint("MissingPermission")
@Composable
fun MapsContent(navController: NavHostController) {
    val context = LocalContext.current
    val hospitals = remember { mutableStateListOf<Hospital>() }
    val cameraPositionState = rememberCameraPositionState()
    val apiKey = "AIzaSyAsL_GBsnfBifXu9CKSVGMxPKHq8sbiJek"
    var showNoHospitalsDialog by remember { mutableStateOf(false) }
    var selectedHospital by remember { mutableStateOf<Hospital?>(null) }
    var currentLocation by remember { mutableStateOf<LatLng?>(null) }

    LaunchedEffect(Unit) {
        val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
        val location = fusedLocationClient.getCurrentLocation(Priority.PRIORITY_HIGH_ACCURACY, null).await()
        val currentLatLng = LatLng(location.latitude, location.longitude)
        currentLocation = currentLatLng

        cameraPositionState.position = CameraPosition.fromLatLngZoom(currentLatLng, 14f)

        val url =
            "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=${location.latitude},${location.longitude}&radius=3000&type=hospital&key=$apiKey"

        val queue = Volley.newRequestQueue(context)
        val request = StringRequest(Request.Method.GET, url,
            { response ->
                val json = JSONObject(response)
                val results = json.getJSONArray("results")
                if (results.length() == 0) {
                    showNoHospitalsDialog = true
                }
                for (i in 0 until results.length()) {
                    val place = results.getJSONObject(i)
                    val loc = place.getJSONObject("geometry").getJSONObject("location")
                    val lat = loc.getDouble("lat")
                    val lng = loc.getDouble("lng")
                    val name = place.getString("name")
                    val placeId = place.getString("place_id")
                    hospitals.add(Hospital(LatLng(lat, lng), name, placeId))
                }
            },
            { error ->
                Log.e("API_ERROR", error.toString())
            })
        queue.add(request)
    }

    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState,
        properties = MapProperties(isMyLocationEnabled = true)
    ) {
        hospitals.forEach { hospital ->
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

    // Dialog chọn hành động khi click vào bệnh viện
    selectedHospital?.let { hospital ->
        AlertDialog(
            onDismissRequest = { selectedHospital = null },
            title = { Text(hospital.name) },
            text = { Text("Bạn muốn tìm đường đến bệnh viện này?") },
            confirmButton = {
                Button(onClick = {
                    currentLocation?.let { current ->
                        // Mở Google Maps với hướng dẫn đường đi
                        val uri = Uri.parse(
                            "google.navigation:q=${hospital.position.latitude},${hospital.position.longitude}&mode=d"
                        )
                        val intent = Intent(Intent.ACTION_VIEW, uri)
                        intent.setPackage("com.google.android.apps.maps")
                        context.startActivity(intent)
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
            text = { Text("Chúng tôi không thể tìm thấy bệnh viện gần vị trí của bạn.") },
            confirmButton = {
                Button(onClick = { showNoHospitalsDialog = false }) {
                    Text("Đóng")
                }
            }
        )
    }
}