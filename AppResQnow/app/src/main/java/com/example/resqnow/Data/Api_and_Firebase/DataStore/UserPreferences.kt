package com.example.resqnow.Data.Api_and_Firebase.DataStore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first

object UserPreferencesKeys {
    val NAME = stringPreferencesKey("name")
    val PHONE = stringPreferencesKey("phone")
    val SEX = stringPreferencesKey("sex")
    val AGE = intPreferencesKey("age")
}

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_prefs")

suspend fun saveUserData(context: Context, name: String, phone: String, sex: String, age: String) {
    context.dataStore.edit { preferences ->
        preferences[UserPreferencesKeys.NAME] = name
        preferences[UserPreferencesKeys.PHONE] = phone
        preferences[UserPreferencesKeys.SEX] = sex
        preferences[UserPreferencesKeys.AGE] = age.toIntOrNull() ?: 0
    }
}

suspend fun readUserData(context: Context): UserData {
    val prefs = context.dataStore.data.first()
    return UserData(
        name = prefs[UserPreferencesKeys.NAME] ?: "",
        phone = prefs[UserPreferencesKeys.PHONE] ?: "",
        sex = prefs[UserPreferencesKeys.SEX] ?: "",
        age = prefs[UserPreferencesKeys.AGE]?.toString() ?: ""
    )
}
suspend fun clearUserData(context: Context) {
    context.dataStore.edit { preferences ->
        preferences.clear()
    }
}
data class UserData(val name: String, val phone: String, val sex: String, val age: String)
