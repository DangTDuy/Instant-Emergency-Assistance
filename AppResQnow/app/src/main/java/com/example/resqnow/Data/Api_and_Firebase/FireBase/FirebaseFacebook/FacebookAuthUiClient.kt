package com.example.resqnow.Data.Api_and_Firebase.FireBase.FirebaseFacebook

import android.app.Activity
import android.content.Intent
import android.util.Log
import com.example.resqnow.Data.Api_and_Firebase.FireBaseGoogle.AppUserData
import com.example.resqnow.Data.Api_and_Firebase.FireBaseGoogle.SignInResult
import com.facebook.*
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.firebase.auth.*
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.tasks.await

class FacebookAuthUiClient(private val activity: Activity) {

    private val callbackManager = CallbackManager.Factory.create()
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    suspend fun signInWithFacebook(): SignInResult {
        val result = CompletableDeferred<SignInResult>()

        // Gọi đăng nhập Facebook
        LoginManager.getInstance().logInWithReadPermissions(activity, listOf("email", "public_profile"))

        // Đăng ký callback xử lý kết quả đăng nhập
        LoginManager.getInstance().registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(loginResult: LoginResult) {
                val accessToken = loginResult.accessToken
                Log.d("FacebookAuth", "AccessToken: ${accessToken?.token}")

                val credential = FacebookAuthProvider.getCredential(accessToken.token)

                auth.signInWithCredential(credential)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            val user = task.result?.user
                            Log.d("FacebookAuth", "Firebase sign-in thành công với UID: ${user?.uid}")
                            result.complete(
                                SignInResult(
                                    data = user?.let { mapFirebaseUserToAppUserData(it) },
                                    errorMessage = null
                                )
                            )
                        } else {
                            val exception = task.exception
                            val errorMessage = when (exception) {
                                is FirebaseAuthUserCollisionException -> "Tài khoản này đã được liên kết với phương thức đăng nhập khác"
                                is FirebaseAuthInvalidCredentialsException -> "Thông tin đăng nhập Facebook không hợp lệ"
                                else -> exception?.localizedMessage ?: "Lỗi không xác định"
                            }
                            Log.e("FacebookAuth", "Firebase sign-in failed", exception)
                            result.complete(SignInResult(null, errorMessage))
                        }
                    }
            }

            override fun onCancel() {
                Log.w("FacebookAuth", "Đăng nhập Facebook bị hủy bởi người dùng.")
                result.complete(SignInResult(null, "Đăng nhập Facebook bị hủy"))
            }

            override fun onError(exception: FacebookException) {
                Log.e("FacebookAuth", "Lỗi trong quá trình đăng nhập Facebook", exception)
                result.complete(SignInResult(null, exception.localizedMessage))
            }
        })

        return result.await()
    }

    // Gọi từ MainActivity để xử lý callback Facebook
    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager.onActivityResult(requestCode, resultCode, data)
    }

    // Đăng xuất Facebook và Firebase
    fun signOut() {
        LoginManager.getInstance().logOut()
        auth.signOut()
    }

    // Lấy thông tin người dùng đã đăng nhập (nếu có)
    fun getSignedInUser(): AppUserData? {
        return auth.currentUser?.let { mapFirebaseUserToAppUserData(it) }
    }

    // Mapping FirebaseUser sang AppUserData của ứng dụng
    fun mapFirebaseUserToAppUserData(firebaseUser: FirebaseUser): AppUserData {
        return AppUserData(
            userId = firebaseUser.uid,
            name = firebaseUser.displayName,
            profilePictureUrl = firebaseUser.photoUrl?.toString()
        )
    }
}
