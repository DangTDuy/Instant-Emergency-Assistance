

package com.example.resqnow.Data.Api_and_Firebase.FireBaseGoogle

import androidx.lifecycle.ViewModel
import com.example.resqnow.Data.Api_and_Firebase.FireBaseGoogle.AppUserData

class UserViewModel : ViewModel() {
    private var _currentUser: AppUserData? = null
    val currentUser: AppUserData? get() = _currentUser

    fun setUser(user: AppUserData?) {
        _currentUser = user
    }

    fun clearUser() {
        _currentUser = null
    }
}