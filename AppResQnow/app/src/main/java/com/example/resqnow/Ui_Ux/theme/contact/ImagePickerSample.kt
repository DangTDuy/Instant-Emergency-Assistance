package com.example.resqnow.Ui_Ux.theme.contact


import android.content.Context
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.resqnow.R

import java.io.File
import java.io.FileOutputStream
@Composable
fun ImagePickerSample(onImagePicked: (String) -> Unit) {
    val context = LocalContext.current
    var imageUri by remember { mutableStateOf<Uri?>(null) }

    // Launcher để chọn ảnh từ thư viện
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        if (uri != null) {
            imageUri = uri
            val copiedPath = copyImageToInternalStorage(context, uri)
            onImagePicked(copiedPath) // Trả về đường dẫn thực sự
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
            .clickable {
                launcher.launch("image/*") // Mở thư viện chọn ảnh
            },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (imageUri != null) {
            Image(
                painter = rememberAsyncImagePainter(imageUri),
                contentDescription = "Selected Image",
                modifier = Modifier
                    .size(100.dp)
                    .clip(shape = RoundedCornerShape(12.dp))
            )
        } else {
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .background(Color(0x4D007AFF)),
                contentAlignment = Alignment.Center
            ) {
                Image(painter = painterResource(id = R.drawable.image), contentDescription = "null"
                    ,modifier = Modifier.size(50.dp)

                )
            }
        }
    }
}

//  Hàm hỗ trợ: sao chép ảnh về thư mục nội bộ app
fun copyImageToInternalStorage(context: Context, imageUri: Uri): String {
    val inputStream = context.contentResolver.openInputStream(imageUri)
    val fileName = "img_${System.currentTimeMillis()}.jpg"
    val file = File(context.filesDir, fileName)
    val outputStream = FileOutputStream(file)

    inputStream?.copyTo(outputStream)
    inputStream?.close()
    outputStream.close()

    return file.absolutePath // Trả về đường dẫn thực tế để lưu vào Room
}
