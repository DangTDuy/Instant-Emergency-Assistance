package com.example.resqnow.Data.Api_and_Firebase.FireBase.FirebaseEmail

import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await

class AuthRepository(private val auth: FirebaseAuth) {

    suspend fun login(email: String, password: String): Resource<Unit> {
        return try {
            auth.signInWithEmailAndPassword(email, password).await()
            Resource.Success(Unit)
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Unknown error")
        }
    }

    fun logout() {
        auth.signOut()
    }

    fun isUserLoggedIn(): Boolean {
        return auth.currentUser != null
    }

    suspend fun register(email: String, password: String): Resource<Unit> {
        return try {
            auth.createUserWithEmailAndPassword(email, password).await()
            Resource.Success(Unit)
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Unknown error")
        }
    }

    suspend fun isEmailAlreadyRegistered(email: String): Boolean {
        return try {
            val result = auth.fetchSignInMethodsForEmail(email).await()
            result.signInMethods?.isNotEmpty() ?: false
        } catch (e: Exception) {
            false
        }
    }
}
