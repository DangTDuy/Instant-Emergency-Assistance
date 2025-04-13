package com.example.resqnow.Ui_Ux.theme.Router // nới chứa các Navigation


import android.os.Bundle

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.android.gms.auth.api.identity.Identity
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.resqnow.Data.Api_and_Firebase.FireBaseGoogle.GoogleAuthUiClient
import com.example.resqnow.Data.Api_and_Firebase.FireBaseGoogle.UserViewModel
import com.example.resqnow.Ui_Ux.theme.EmergencyInstructions.EmergencyInstructions
import com.example.resqnow.Ui_Ux.theme.Homepage.HomePage1
import com.example.resqnow.Ui_Ux.theme.IntroductionGuide.IntroductionGuide
import com.example.resqnow.Ui_Ux.theme.Login.LoginScreen
import com.example.resqnow.Ui_Ux.theme.Login.LoginScreenSuccess
import com.example.resqnow.Ui_Ux.theme.Maps.Maps
import com.example.resqnow.Ui_Ux.theme.ResQnowTheme

import com.example.resqnow.Ui_Ux.theme.SignIn.SignInSuccessScreen
import com.example.resqnow.Ui_Ux.theme.common.IntroScreen1

import com.example.resqnow.Ui_Ux.theme.introApp.IntroScreen2
import com.example.resqnow.Ui_Ux.theme.introApp.IntroScreen3
import com.example.resqnow.Ui_Ux.theme.introApp.IntroScreen4
import com.example.resqnow.Ui_Ux.theme.learnfirstaid.LearnFirstAid
import com.example.resqnow.Ui_Ux.theme.outroApp.OutroScreen1
import com.example.resqnow.Ui_Ux.theme.outroApp.OutroScreen2
import com.example.resqnow.Ui_Ux.theme.outroApp.OutroScreen3
import com.example.resqnow.Ui_Ux.theme.personalization.Personalization



class MainActivity : ComponentActivity() {
    private val googleAuthUiClient by lazy {
        GoogleAuthUiClient(
            context = this,
            oneTapClient = Identity.getSignInClient(this)
        )
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ResQnowTheme {
                Navigation(googleAuthUiClient)
            }
            }
        }
    }

//tạo object lưu màn hình
sealed class Screen(val route: String) {
    //intro
    object ScreenIntro1 : Screen("IntroAppScreen1")
    object ScreenIntro2 : Screen("IntroAppScreen2")
    object ScreenIntro3 : Screen("IntroAppScreen3")
    object ScreenIntro4 : Screen("IntroAppScreen4")
    //outro
    object ScreenOutro1 : Screen("outroAppScreen1")
    object ScreenOutro2 : Screen("outroAppScreen2")
    object ScreenOutro3 : Screen("outroAppScreen3")
    //Homepage
    object HomePage1 : Screen("HomeScreen1")

    //Login
    object LoginScreen : Screen("LoginScreen")
    object LoginSuccess : Screen("LoginSuccess")
    //SignIn
    object SignInScreen : Screen("SignInScreen")
    object SignInSuccess : Screen("SignInSuccess")

}
//Hàm Controller màn hình
@Composable
fun Navigation(googleAuthUiClient: GoogleAuthUiClient) {

    val navController = rememberNavController()
    val userViewModel: UserViewModel = viewModel()

    val startDestination = if (googleAuthUiClient.getSignedInUser() != null) {
        "HomeScreen1"
    } else {
        "IntroScreen1"
    }
    NavHost(navController = navController, startDestination = "IntroAppScreen1") {
        //flow Intro
        composable("IntroAppScreen1") { IntroScreen1(navController = navController) }
        composable("IntroAppScreen2") { IntroScreen2(navController = navController) }
        composable("IntroAppScreen3") { IntroScreen3(navController = navController) }
        composable("IntroAppScreen4") {
            IntroScreen4(navController = navController)
        }
        //flow intro to outro

        //flow Outro
        composable("OutroAppScreen1") { OutroScreen1(navController = navController) }
        composable("OutroAppScreen2") { OutroScreen2(navController = navController) }
        composable("OutroAppScreen3") {
            OutroScreen3(navController = navController)

        }

        //Signin
        composable("SignInScreen") {
            com.example.resqnow.Ui_Ux.theme.Login.SignInScreen(navController = navController,
                googleAuthUiClient = googleAuthUiClient,
                userViewModel = userViewModel
                )
        }
        composable("SignInSuccess") {
            SignInSuccessScreen(
                navController = navController,
                googleAuthUiClient = googleAuthUiClient

            )
        }
        //Login
        composable("LoginScreen") {LoginScreen(navController=navController)}
        composable("LoginSuccess") {LoginScreenSuccess(navController=navController)}
        //flow Homepage
        composable("HomeScreen1") { HomePage1(navController = navController, googleAuthUiClient = googleAuthUiClient) }

        //IntroductionGuide
        composable("IntroductionGuide") { IntroductionGuide(navController = navController) }




        //learn first aid
        composable("LearnFirstAid") { LearnFirstAid(navController = navController) }

        //personalization
        composable("Personalization") { Personalization(navController = navController) }

        //Emergency Instructions
        composable("EmergencyInstructions") { EmergencyInstructions(navController = navController) }

        //Maps
        composable("Maps") { Maps(navController = navController) }
    }
}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    ResQnowTheme {

//            IntroScreen2(navController = rememberNavController())

    }
}