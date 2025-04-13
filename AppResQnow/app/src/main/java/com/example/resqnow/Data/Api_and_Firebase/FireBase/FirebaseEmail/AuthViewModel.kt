package com.example.resqnow.Data.Api_and_Firebase.FireBase.FirebaseEmail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AuthViewModel(private val repo: AuthRepository) : ViewModel() {

    private val _loginState = MutableStateFlow<Resource<Unit>>(Resource.Success(Unit))
    val loginState: StateFlow<Resource<Unit>> = _loginState

    fun login(email: String, password: String, onResult: (Boolean, String?) -> Unit) {
        viewModelScope.launch {
            val result = repo.login(email, password) // gọi từ repository
            if (result is Resource.Success) {
                onResult(true, null)
            } else if (result is Resource.Error) {
                onResult(false, result.message)
            }
        }
    }

    fun logout() {
        repo.logout()
    }

    fun isUserLoggedIn(): Boolean {
        return repo.isUserLoggedIn()
    }

    fun checkIfEmailExists(email: String, callback: (Boolean) -> Unit) {
        viewModelScope.launch {
            val exists = repo.isEmailAlreadyRegistered(email)
            callback(exists)
        }
    }

    fun registerWithEmail(email: String, password: String, onResult: (Boolean, String?) -> Unit) {
        checkIfEmailExists(email) { emailExists ->
            if (emailExists) {
                onResult(false, "Email này đã được sử dụng!")
            } else {
                viewModelScope.launch {
                    val result = repo.register(email, password)
                    when (result) {
                        is Resource.Success -> onResult(true, null)
                        is Resource.Error -> onResult(false, result.message)
                        else -> onResult(false, "Đăng ký thất bại")
                    }
                }
            }
        }
    }
}
