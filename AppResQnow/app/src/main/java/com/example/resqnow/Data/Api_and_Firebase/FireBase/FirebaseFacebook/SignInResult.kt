package com.example.resqnow.Data.Api_and_Firebase.FireBase.FirebaseFacebook

import com.example.resqnow.Data.Api_and_Firebase.FireBaseGoogle.AppUserData

data class SignInResult(
    val data: AppUserData?,
    val errorMessage: String?
)
