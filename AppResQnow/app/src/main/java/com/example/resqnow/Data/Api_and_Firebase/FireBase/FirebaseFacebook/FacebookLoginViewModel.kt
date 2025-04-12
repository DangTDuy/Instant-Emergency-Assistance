package com.example.resqnow.Data.Api_and_Firebase.FireBase.FirebaseFacebook


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.facebook.AccessToken
import com.facebook.login.LoginManager
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FacebookLoginViewModel : ViewModel() {
    private val _isLoggedIn = MutableStateFlow(false)
    val isLoggedIn: StateFlow<Boolean> get() = _isLoggedIn

    private val _userName = MutableStateFlow<String?>(null)
    val userName: StateFlow<String?> get() = _userName

    // Kiểm tra token Facebook đã tồn tại chưa
    fun checkAccessToken() {
        // Kiểm tra xem người dùng đã đăng nhập chưa
        _isLoggedIn.value = FirebaseAuth.getInstance().currentUser != null
    }

    // Lưu tên người dùng khi đăng nhập thành công
    fun onFacebookLoginSuccess(name: String? = null) {
        _isLoggedIn.value = true
        _userName.value = name
    }

    // Đăng xuất
    fun logoutFacebook() {
        FirebaseAuth.getInstance().signOut()
        _isLoggedIn.value = false
    }
}