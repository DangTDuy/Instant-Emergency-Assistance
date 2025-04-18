package com.example.resqnow.Repository

import com.example.resqnow.Room.ContactDao
import com.example.resqnow.Room.ContactEntity
import com.example.resqnow.Room.ResqNowDatabase
import kotlinx.coroutines.flow.Flow


class Repository(private val db: ResqNowDatabase) {

    // Sử dụng Flow để lấy tất cả các liên hệ
    fun getAllContact(): Flow<List<ContactEntity>> {
        return db.contactDao().getAllContact()
    }

    // Lấy liên hệ theo ID
    suspend fun getContactById(id: Int): ContactEntity? {
        return db.contactDao().getContactById(id)
    }

    // Thêm liên hệ
    suspend fun addContact(contact: ContactEntity) {
        db.contactDao().addContact(contact)
    }

    // Xóa liên hệ
    suspend fun deleteContact(contact: ContactEntity) {
        db.contactDao().deleteContact(contact)
    }
    fun getContactsByUserId(userId: String): Flow<List<ContactEntity>> {
        return db.contactDao().getContactsByUser(userId)
    }


}