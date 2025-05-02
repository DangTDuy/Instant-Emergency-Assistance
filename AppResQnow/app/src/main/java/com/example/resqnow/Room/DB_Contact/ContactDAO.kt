package com.example.resqnow.Room.DB_Contact


import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ContactDao {
    @Insert
    suspend fun addContact(contactEntity: ContactEntity)

    @Delete
    suspend fun deleteContact(contactEntity: ContactEntity)

    @Query("SELECT * FROM emergency_Contact WHERE id = :id")
    suspend fun getContactById(id: Int): ContactEntity?

    @Query("SELECT * FROM emergency_Contact")
    fun getAllContact(): Flow<List<ContactEntity>> // Flow trả về

    @Query("SELECT * FROM emergency_Contact WHERE userId = :userId")
    fun getContactsByUser(userId: String): Flow<List<ContactEntity>>


}
