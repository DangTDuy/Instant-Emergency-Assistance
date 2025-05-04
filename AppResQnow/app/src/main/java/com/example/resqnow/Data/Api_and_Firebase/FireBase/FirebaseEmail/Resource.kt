package com.example.resqnow.Data.Api_and_Firebase.FireBase.FirebaseEmail


sealed class Resource<out T> {
    data class Success<out T>(val data: T) : Resource<T>()
    data class Error(val message: String) : Resource<Nothing>()
    object Loading : Resource<Nothing>()
}
