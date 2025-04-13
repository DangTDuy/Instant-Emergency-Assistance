package com.example.resqnow.Data.Api_and_Firebase.FireBase.FirebaseFacebook



import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.facebook.*
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

import java.util.*
class FacebookAuthManager(
    private val activity: Activity,
    private val viewModel: FacebookLoginViewModel
) {
    private lateinit var callbackManager: CallbackManager

    fun initFacebookLogin(): CallbackManager {
        callbackManager = CallbackManager.Factory.create()

        LoginManager.getInstance().registerCallback(callbackManager,
            object : FacebookCallback<LoginResult> {
                override fun onSuccess(result: LoginResult) {
                    Log.d("FBLogin", "Success: ${result.accessToken}")
                    // Đăng nhập Firebase
                    handleFacebookAccessToken(result.accessToken)
                }

                override fun onCancel() {
                    Log.d("FBLogin", "Login cancelled")
                }

                override fun onError(error: FacebookException) {
                    Log.e("FBLogin", "Error: $error")
                }
            })

        return callbackManager
    }

    fun loginWithFacebook() {
        LoginManager.getInstance().logInWithReadPermissions(
            activity,
            listOf("public_profile", "email")
        )
    }

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager.onActivityResult(requestCode, resultCode, data)
    }

    // Sửa lại hàm handleFacebookAccessToken trong FacebookAuthManager
    private fun handleFacebookAccessToken(accessToken: AccessToken) {
        val credential = FacebookAuthProvider.getCredential(accessToken.token)

        // Đăng nhập vào Firebase với Facebook Auth
        FirebaseAuth.getInstance().signInWithCredential(credential)
            .addOnCompleteListener(activity) { task ->
                if (task.isSuccessful) {
                    // Đăng nhập thành công vào Firebase
                    val user = FirebaseAuth.getInstance().currentUser
                    Log.d("FBLogin", "Firebase auth success: $user")

                    // Gọi Graph API để lấy thông tin người dùng
                    val request = GraphRequest.newMeRequest(accessToken) { _, response ->
                        // Kiểm tra xem response có null không trước khi truy cập
                        val name = response?.jsonObject?.optString("name") ?: "Unknown"
                        Log.d("FBLogin", "Facebook User Name: $name")
                        // Bạn có thể cập nhật UI hoặc gửi tên này về ViewModel để hiển thị
                        viewModel.onFacebookLoginSuccess(name) // Truyền tên người dùng cho ViewModel
                    }

                    val parameters = Bundle()
                    parameters.putString("fields", "name,email") // Chỉ lấy tên và email
                    request.parameters = parameters
                    request.executeAsync()

                } else {
                    // Nếu có lỗi trong quá trình đăng nhập Firebase
                    Log.e("FBLogin", "Firebase auth failed", task.exception)
                }
            }
    }

}
