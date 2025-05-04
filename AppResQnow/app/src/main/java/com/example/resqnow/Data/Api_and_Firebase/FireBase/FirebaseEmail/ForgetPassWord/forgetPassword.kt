package com.example.resqnow.Data.Api_and_Firebase.FireBase.FirebaseEmail.ForgetPassWord

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth


fun sendPasswordResetEmail(
    context: Context,
    email: String,
    onSuccess: () -> Unit
) {
    FirebaseAuth.getInstance().sendPasswordResetEmail(email)
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Log.d("ResetPassword", "Email đặt lại mật khẩu đã được gửi.")
                onSuccess() // Gọi callback để hiển thị AlertDialog
            } else {
                Log.e("ResetPassword", "Lỗi gửi email: ${task.exception?.message}")
                Toast.makeText(
                    context,
                    "Không thể gửi email. Vui lòng kiểm tra lại địa chỉ.",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
}
