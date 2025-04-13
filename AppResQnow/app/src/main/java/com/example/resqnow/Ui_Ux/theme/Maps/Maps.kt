package com.example.resqnow.Ui_Ux.theme.Maps

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
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

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun Maps(navController: NavHostController) {
    val locationPermissionState = rememberPermissionState(android.Manifest.permission.ACCESS_FINE_LOCATION)

    // Xin quyền vị trí nếu chưa có
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
    val hospitals = remember { mutableStateListOf<LatLng>() }
    val cameraPositionState = rememberCameraPositionState()
    val apiKey = "AIzaSyBwPmK0eNnFTtGJnbw5qon0g8tZOJprwtk" // ✅ Thay bằng API Key thật

    LaunchedEffect(Unit) {
        val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
        val location = fusedLocationClient.getCurrentLocation(Priority.PRIORITY_HIGH_ACCURACY, null).await()
        val currentLatLng = LatLng(location.latitude, location.longitude)

        // Di chuyển camera đến vị trí hiện tại
        cameraPositionState.position = CameraPosition.fromLatLngZoom(currentLatLng, 14f)

        // Gọi Google Places API để tìm bệnh viện
        val url =
            "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=${location.latitude},${location.longitude}&radius=3000&type=hospital&key=$apiKey"

        val queue = Volley.newRequestQueue(context)
        val request = StringRequest(Request.Method.GET, url,
            { response ->
                val json = JSONObject(response)
                val results = json.getJSONArray("results")
                for (i in 0 until results.length()) {
                    val place = results.getJSONObject(i)
                    val loc = place.getJSONObject("geometry").getJSONObject("location")
                    val lat = loc.getDouble("lat")
                    val lng = loc.getDouble("lng")
                    hospitals.add(LatLng(lat, lng))
                }
            },
            { error ->
                Log.e("API_ERROR", error.toString())
            })
        queue.add(request)
    }

    // Hiển thị Google Map với marker bệnh viện
    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState,
        properties = MapProperties(isMyLocationEnabled = true)
    ) {
        hospitals.forEach {
            Marker(
                state = MarkerState(position = it),
                title = "Bệnh viện gần bạn"
            )
        }
    }
}
