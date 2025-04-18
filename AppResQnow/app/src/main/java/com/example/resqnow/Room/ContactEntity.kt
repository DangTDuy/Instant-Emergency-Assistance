package com.example.resqnow.Room


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "emergency_Contact")
data class ContactEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val urlImage: String,
    val name : String,
    val phoneNumber :String,
    val userId: String // dùng dễ biết Những danh bạ đi theo tài khoản nào
)