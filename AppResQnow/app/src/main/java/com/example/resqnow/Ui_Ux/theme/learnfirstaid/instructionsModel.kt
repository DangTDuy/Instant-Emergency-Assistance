package com.example.resqnow.Ui_Ux.theme.learnfirstaid

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

data class Instructor (
    val name: String,
    val location: String,
    val phone: String,
    val description: String,
    val email: String,
    val price: String,
    val hours: String
)


fun loadInstructorsFromAssets(context: Context): List<Instructor> {
    val json = context.assets.open("instructors.json").bufferedReader().use { it.readText() }
    val gson = Gson()
    val type = object : TypeToken<List<Instructor>>() {}.type
    return gson.fromJson(json, type)
}