package com.example.resqnow.Room.DB_Contact


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [ContactEntity::class], version = 4, exportSchema = false)
abstract  class ResqNowDatabase : RoomDatabase(){
    abstract fun contactDao(): ContactDao

    companion object{
        @Volatile
        private var INSTANCE: ResqNowDatabase? = null
        fun getInstance(context: Context): ResqNowDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if(instance == null ){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ResqNowDatabase::class.java,
                        "ResQNow_db"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance


            }
        }
    }
}