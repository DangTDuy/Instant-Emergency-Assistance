package com.example.resqnow.Ui_Ux.theme.Router // nới chứa các Navigation


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.resqnow.Ui_Ux.theme.EmergencyInstructions.EmergencyInstructions
import com.example.resqnow.Ui_Ux.theme.Homepage.HomePage1
import com.example.resqnow.Ui_Ux.theme.IntroductionGuide.IntroductionGuide
import com.example.resqnow.Ui_Ux.theme.Login.LoginScreen
import com.example.resqnow.Ui_Ux.theme.Login.LoginScreenSuccess
import com.example.resqnow.Ui_Ux.theme.ResQnowTheme
import com.example.resqnow.Ui_Ux.theme.Router.Screen.LoginSuccess

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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ResQnowTheme {
                Navigation()
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

}
//Hàm Controller màn hình
@Composable
fun Navigation() {
    val navController = rememberNavController()

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

        // Login
        composable("LoginScreen") { LoginScreen(navController = navController) }
        composable("LoginSuccess") { LoginScreenSuccess(navController = navController) }


        //IntroductionGuide
        composable("IntroductionGuide") { IntroductionGuide(navController = navController) }


        //flow Homepage
        composable("HomeScreen1") { HomePage1(navController = navController) }

        //learn first aid
        composable("LearnFirstAid") { LearnFirstAid(navController = navController) }

        //personalization
        composable("Personalization") { Personalization(navController = navController) }

        //Emergency Instructions
        composable("EmergencyInstructions") { EmergencyInstructions(navController = navController) }
    }
}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    ResQnowTheme {

            IntroScreen2(navController = rememberNavController())

    }
}