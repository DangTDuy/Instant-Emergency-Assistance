package com.example.resqnow.Repository

import com.example.resqnow.Room.DB_Contact.ContactEntity
import com.example.resqnow.Room.DB_Contact.ResqNowDatabase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class Repository(private val db: ResqNowDatabase) {

    //  CONTACT
    fun getAllContact(): Flow<List<ContactEntity>> {
        return db.contactDao().getAllContact()
    }

    suspend fun getContactById(id: Int): ContactEntity? {
        return db.contactDao().getContactById(id)
    }

    suspend fun addContact(contact: ContactEntity) {
        db.contactDao().addContact(contact)
    }

    suspend fun deleteContact(contact: ContactEntity) {
        db.contactDao().deleteContact(contact)
    }

    suspend fun updateContact(contact: ContactEntity) {
        db.contactDao().updateContact(contact)
    }

    fun getContactsByUserId(userId: String): Flow<List<ContactEntity>> {
        return db.contactDao().getContactsByUser(userId)
    }


}
