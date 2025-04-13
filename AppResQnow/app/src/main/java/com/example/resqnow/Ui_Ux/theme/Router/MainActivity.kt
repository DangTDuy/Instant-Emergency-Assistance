package com.example.resqnow.Ui_Ux.theme.Router // nới chứa các Navigation

<<<<<<< HEAD
=======
import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.Intent
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.facebook.FacebookSdk
import com.facebook.appevents.AppEventsLogger
>>>>>>> 7dd2d1af6625a33cb3715e0ff8e67ff028c5aa7d

import android.os.Bundle

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
<<<<<<< HEAD
=======
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.compose.collectAsStateWithLifecycle
>>>>>>> 7dd2d1af6625a33cb3715e0ff8e67ff028c5aa7d
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.android.gms.auth.api.identity.Identity
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.resqnow.Data.Api_and_Firebase.FireBase.FirebaseFacebook.FacebookAuthManager
import com.example.resqnow.Data.Api_and_Firebase.FireBase.FirebaseFacebook.FacebookLoginViewModel
import com.example.resqnow.Data.Api_and_Firebase.FireBaseGoogle.GoogleAuthUiClient
<<<<<<< HEAD
=======

import com.example.resqnow.Data.Api_and_Firebase.FireBaseGoogle.SignInViewModel
>>>>>>> 7dd2d1af6625a33cb3715e0ff8e67ff028c5aa7d
import com.example.resqnow.Data.Api_and_Firebase.FireBaseGoogle.UserViewModel
import com.example.resqnow.Ui_Ux.theme.EmergencyInstructions.EmergencyInstructions
import com.example.resqnow.Ui_Ux.theme.Homepage.HomePage1
import com.example.resqnow.Ui_Ux.theme.IntroductionGuide.IntroductionGuide
import com.example.resqnow.Ui_Ux.theme.Login.LoginScreen
import com.example.resqnow.Ui_Ux.theme.Login.LoginScreenSuccess
<<<<<<< HEAD
import com.example.resqnow.Ui_Ux.theme.Maps.Maps
import com.example.resqnow.Ui_Ux.theme.ResQnowTheme
=======


import com.example.resqnow.Ui_Ux.theme.Profile.ProfileScreen
import com.example.resqnow.Ui_Ux.theme.Profile.ProfileScreenWithoutAccount
import com.example.resqnow.Ui_Ux.theme.ResQnowTheme
import com.example.resqnow.Ui_Ux.theme.Router.Screen.LoginSuccess
import com.example.resqnow.Ui_Ux.theme.SignIn.SignInScreen

>>>>>>> 7dd2d1af6625a33cb3715e0ff8e67ff028c5aa7d

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
import com.facebook.CallbackManager


class MainActivity : ComponentActivity() {
    private lateinit var authManager: FacebookAuthManager
    private lateinit var viewModel: FacebookLoginViewModel


    private val googleAuthUiClient by lazy {
        GoogleAuthUiClient(
            context = this,
            oneTapClient = Identity.getSignInClient(this)
        )
    }

    
    private lateinit var callbackManager: CallbackManager // Khai báo callbackManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        callbackManager = CallbackManager.Factory.create() // Khởi tạo callbackManager
        FacebookSdk.sdkInitialize(applicationContext)
        viewModel = ViewModelProvider(this)[FacebookLoginViewModel::class.java]
        authManager = FacebookAuthManager(this, viewModel)

        enableEdgeToEdge()
        setContent {
            ResQnowTheme {
                // Truyền callbackManager vào hàm Navigation
                Navigation(googleAuthUiClient, callbackManager)
            }
        }
        enableEdgeToEdge() // Cho phép edge-to-edge UI
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        callbackManager.onActivityResult(requestCode, resultCode, data) // Xử lý kết quả Facebook login
    }
}

// Tạo object lưu màn hình
sealed class Screen(val route: String) {
    // Intro
    object ScreenIntro1 : Screen("IntroAppScreen1")
    object ScreenIntro2 : Screen("IntroAppScreen2")
    object ScreenIntro3 : Screen("IntroAppScreen3")
    object ScreenIntro4 : Screen("IntroAppScreen4")
    // Outro
    object ScreenOutro1 : Screen("outroAppScreen1")
    object ScreenOutro2 : Screen("outroAppScreen2")
    object ScreenOutro3 : Screen("outroAppScreen3")
    // Homepage
    object HomePage1 : Screen("HomeScreen1")

    // Login
    object LoginScreen : Screen("LoginScreen")
    object LoginSuccess : Screen("LoginSuccess")
    // SignIn
    object SignInScreen : Screen("SignInScreen")
    object SignInSuccess : Screen("SignInSuccess")
    // Profile
    object ProfileScreen : Screen("ProfileScreen")
    object ProfileScreenWithoutAccount : Screen("ProfileScreenWithoutAccount")
}

// Hàm Controller màn hình
@Composable
fun Navigation(googleAuthUiClient: GoogleAuthUiClient, callbackManager: CallbackManager) {

    val navController = rememberNavController()
    val userViewModel: UserViewModel = viewModel()

    NavHost(navController = navController, startDestination = Screen.ScreenIntro1.route) {
        // Flow Intro
        composable("IntroAppScreen1") { IntroScreen1(navController = navController) }
        composable("IntroAppScreen2") { IntroScreen2(navController = navController) }
        composable("IntroAppScreen3") { IntroScreen3(navController = navController) }
        composable("IntroAppScreen4") {
            IntroScreen4(navController = navController)
        }
        // Flow Intro to Outro

        // Flow Outro
        composable("OutroAppScreen1") { OutroScreen1(navController = navController) }
        composable("OutroAppScreen2") { OutroScreen2(navController = navController) }
        composable("OutroAppScreen3") {
            OutroScreen3(navController = navController)
        }

        // SignIn
        composable(Screen.SignInScreen.route) {
            SignInScreen(
                navController = navController,
                googleAuthUiClient = googleAuthUiClient,
//                callbackManager = callbackManager, // Truyền callbackManager
                userViewModel = userViewModel
            )
        }

        composable(Screen.SignInSuccess.route) {
            SignInSuccessScreen(
                navController = navController,
                googleAuthUiClient = googleAuthUiClient
            )
        }

        // Login
        composable("LoginScreen") { LoginScreen(navController = navController) }
        composable("LoginSuccess") { LoginScreenSuccess(navController = navController) }

        // Profile
        composable(Screen.ProfileScreen.route) {
            if (googleAuthUiClient.getSignedInUser() != null) {
                ProfileScreen(
                    userData = googleAuthUiClient.getSignedInUser(),
                    googleAuthUiClient = googleAuthUiClient,
                    onSignedOut = {
                        navController.navigate(Screen.SignInScreen.route) {
                            popUpTo(Screen.ProfileScreen.route) { inclusive = true }
                        }
                    }
                )
            } else {
                ProfileScreenWithoutAccount(navController = navController)
            }
        }

        // Flow Homepage
        composable("HomeScreen1") { HomePage1(navController = navController, googleAuthUiClient = googleAuthUiClient) }

        // Introduction Guide
        composable("IntroductionGuide") { IntroductionGuide(navController = navController) }

        // Learn First Aid
        composable("LearnFirstAid") { LearnFirstAid(navController = navController) }

        // Personalization
        composable("Personalization") { Personalization(navController = navController) }

        // Emergency Instructions
        composable("EmergencyInstructions") { EmergencyInstructions(navController = navController) }

        //Maps
        composable("Maps") { Maps(navController = navController) }
    }
}
