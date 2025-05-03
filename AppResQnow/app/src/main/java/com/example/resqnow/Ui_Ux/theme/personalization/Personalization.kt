package com.example.resqnow.Ui_Ux.theme.personalization

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.resqnow.Data.Api_and_Firebase.DataStore.readUserData
import kotlinx.coroutines.launch

// màn hình chức năng cá nhân hóa
@Composable
fun Personalization(navController: NavController) {
    val context = LocalContext.current
    val ageState = remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        coroutineScope.launch {
            val userData = readUserData(context)
            ageState.value = userData.age
        }
    }

    val userAge = ageState.value.toIntOrNull() ?: 0

    LaunchedEffect(userAge) {
        when {
            userAge in 0..17 -> navController.navigate("ChildScreen")
            userAge in 18..35 -> navController.navigate("YoungAdultScreen")
            userAge in 36..50 -> navController.navigate("MiddleAgeScreen")
            userAge > 60 -> navController.navigate("SeniorScreen")
            else -> navController.navigate("ProfileScreen")
        }
    }
}
