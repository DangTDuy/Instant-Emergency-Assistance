package com.example.resqnow.Ui_Ux.theme.SignIn

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.GraphRequest
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult

object FacebookLoginHelper {
    lateinit var callbackManager: CallbackManager

    fun init(activity: ComponentActivity, onSuccess: (String) -> Unit) {
        callbackManager = CallbackManager.Factory.create()

        // Đăng ký callback
        LoginManager.getInstance().registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(result: LoginResult) {
                val accessToken = result.accessToken
                val request = GraphRequest.newMeRequest(accessToken) { obj, _ ->
                    val name = obj?.getString("name") ?: "Không rõ"
                    onSuccess(name)  // Trả về tên người dùng
                }
                val params = Bundle()
                params.putString("fields", "id,name")
                request.parameters = params
                request.executeAsync()
            }

            override fun onCancel() {
                Log.d("FB_LOGIN", "Bị huỷ")
            }

            override fun onError(error: FacebookException) {
                Log.e("FB_LOGIN", "Lỗi: ${error.message}")
            }
        })
    }

    fun login(activity: ComponentActivity) {
        LoginManager.getInstance().logInWithReadPermissions(
            activity,
            listOf("public_profile")
        )
    }

    fun handleOnActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager.onActivityResult(requestCode, resultCode, data)
    }
}
