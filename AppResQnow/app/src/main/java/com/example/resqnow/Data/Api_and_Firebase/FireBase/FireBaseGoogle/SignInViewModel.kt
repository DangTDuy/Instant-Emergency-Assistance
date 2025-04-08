package com.example.resqnow.Data.Api_and_Firebase.FireBaseGoogle

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SignInViewModel : ViewModel() {
    private val _state = MutableStateFlow(SignInState())
    val state = _state.asStateFlow()

    fun onSignInResult(result: SignInResult) {
        _state.value = state.value.copy(
            isSignInSuccessful = result.data != null,
            signInError = result.errorMessage
        )

    }
    fun resetState(){
        _state.update { SignInState() }
    }
}