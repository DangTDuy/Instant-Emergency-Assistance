package com.example.resqnow.Ui_Ux.theme.contact


import android.content.Context
import android.content.Intent
import android.net.Uri

fun makePhoneCall(context: Context, phoneNumber: String) {

    val intent = Intent(Intent.ACTION_DIAL) //  Sử dụng ACTION_DIAL để mở màn gọi mà không cần quyền
    intent.data = Uri.parse("tel:$phoneNumber")
    context.startActivity(intent)


    //GỌI NGAY LẬP TỨC ĐẾN SỐ ĐIỆN THOẠI
//    val intent = Intent(Intent.ACTION_CALL)
//    intent.data = Uri.parse("tel:$phoneNumber")
//    if (ContextCompat.checkSelfPermission(context, android.Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
//        context.startActivity(intent)
//    } else {
//        Toast.makeText(context, "Bạn chưa cấp quyền gọi điện!", Toast.LENGTH_SHORT).show()
//    }
}