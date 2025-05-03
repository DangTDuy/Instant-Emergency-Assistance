package com.example.resqnow.Ui_Ux.theme.Maps

import androidx.lifecycle.ViewModel
import android.content.Context
import androidx.lifecycle.viewModelScope
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
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

class MapsViewModel(private val context: Context) : ViewModel() {
    private val _hospitals = MutableStateFlow<List<Hospital>>(emptyList())
    val hospitals: StateFlow<List<Hospital>> = _hospitals.asStateFlow()

    private val _filteredHospitals = MutableStateFlow<List<Hospital>>(emptyList())
    val filteredHospitals: StateFlow<List<Hospital>> = _filteredHospitals.asStateFlow()

    private val _loadingState = MutableStateFlow(LoadingState.LOADING)
    val loadingState: StateFlow<LoadingState> = _loadingState.asStateFlow()

    private val _currentLocation = MutableStateFlow<LatLng?>(null)
    val currentLocation: StateFlow<LatLng?> = _currentLocation.asStateFlow()

    private val _errorMessage = MutableStateFlow("")
    val errorMessage: StateFlow<String> = _errorMessage.asStateFlow()

    private val _showNoHospitalsDialog = MutableStateFlow(false)
    val showNoHospitalsDialog: StateFlow<Boolean> = _showNoHospitalsDialog.asStateFlow()

    private val _selectedHospital = MutableStateFlow<Hospital?>(null)
    val selectedHospital: StateFlow<Hospital?> = _selectedHospital.asStateFlow()

    private val _searchingPlaces = MutableStateFlow(false)
    val searchingPlaces: StateFlow<Boolean> = _searchingPlaces.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    fun setSearchQuery(query: String) {
        _searchQuery.value = query
        filterHospitals(query)
    }

    fun setSelectedHospital(hospital: Hospital?) {
        _selectedHospital.value = hospital
    }

    fun setShowNoHospitalsDialog(show: Boolean) {
        _showNoHospitalsDialog.value = show
    }

    fun fetchLocationAndHospitals() {
        viewModelScope.launch {
            _loadingState.value = LoadingState.LOADING
            try {
                val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
                val location = fusedLocationClient.getCurrentLocation(Priority.PRIORITY_HIGH_ACCURACY, null).await()
                if (location != null) {
                    val currentLatLng = LatLng(location.latitude, location.longitude)
                    _currentLocation.value = currentLatLng
                    fetchNearbyHospitals(currentLatLng)
                } else {
                    _loadingState.value = LoadingState.LOCATION_ERROR
                    _errorMessage.value = "Không thể lấy vị trí của bạn. Vui lòng kiểm tra GPS đã bật."
                }
            } catch (e: Exception) {
                _loadingState.value = LoadingState.LOCATION_ERROR
                _errorMessage.value = "Không thể truy cập vị trí: ${e.localizedMessage}"
            }
        }
    }

    private fun filterHospitals(query: String) {
        if (query.isEmpty()) {
            _filteredHospitals.value = _hospitals.value
        } else {
            _filteredHospitals.value = _hospitals.value.filter {
                it.name.contains(query, ignoreCase = true) ||
                        it.vicinity.contains(query, ignoreCase = true)
            }
        }
    }

    private fun fetchNearbyHospitals(location: LatLng) {
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
                    _hospitals.value = hospitalsList
                    _filteredHospitals.value = hospitalsList
                    _loadingState.value = LoadingState.SUCCESS
                    if (hospitalsList.isEmpty()) {
                        _showNoHospitalsDialog.value = true
                    }
                } catch (e: Exception) {
                    _loadingState.value = LoadingState.ERROR
                    _errorMessage.value = "Không thể xử lý dữ liệu: ${e.localizedMessage}"
                }
            },
            Response.ErrorListener { error ->
                _loadingState.value = LoadingState.ERROR
                _errorMessage.value = "Lỗi kết nối: ${error.message ?: "Không thể kết nối đến máy chủ"}"
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

    fun searchHospitalsByQuery(query: String) {
        val currentLocation = _currentLocation.value
        _searchingPlaces.value = true
        val apiKey = "AIzaSyAsL_GBsnfBifXu9CKSVGMxPKHq8sbiJek"
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
                    _filteredHospitals.value = hospitalsList
                    _searchingPlaces.value = false
                    if (hospitalsList.isEmpty()) {
                        _showNoHospitalsDialog.value = true
                    }
                } catch (e: Exception) {
                    _searchingPlaces.value = false
                    _errorMessage.value = "Không thể xử lý dữ liệu tìm kiếm: ${e.localizedMessage}"
                }
            },
            Response.ErrorListener { error ->
                _searchingPlaces.value = false
                _errorMessage.value = "Lỗi kết nối tìm kiếm: ${error.message ?: "Không thể kết nối đến máy chủ"}"
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
}