package com.example.resqnow.Ui_Ux.theme.Router

import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.resqnow.Data.Api_and_Firebase.FireBase.FirebaseFacebook.FacebookAuthManager
import com.example.resqnow.Data.Api_and_Firebase.FireBase.FirebaseFacebook.FacebookLoginViewModel
import com.example.resqnow.Data.Api_and_Firebase.FireBaseGoogle.GoogleAuthUiClient
import com.example.resqnow.Data.Api_and_Firebase.FireBaseGoogle.SignInViewModel
import com.example.resqnow.Data.Api_and_Firebase.FireBaseGoogle.UserViewModel
import com.example.resqnow.Ui_Ux.theme.EmergencyInstructions.EmergencyInstructions
import com.example.resqnow.Ui_Ux.theme.Homepage.HomePage1
import com.example.resqnow.Ui_Ux.theme.IntroductionGuide.IntroductionGuide
import com.example.resqnow.Ui_Ux.theme.Login.LoginScreen
import com.example.resqnow.Ui_Ux.theme.Login.LoginScreenSuccess
import com.example.resqnow.Ui_Ux.theme.Maps.Maps
import com.example.resqnow.Ui_Ux.theme.Profile.ProfileScreen
import com.example.resqnow.Ui_Ux.theme.Profile.ProfileScreenWithoutAccount
import com.example.resqnow.Ui_Ux.theme.ResQnowTheme
import com.example.resqnow.Ui_Ux.theme.SignIn.SignInScreen
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
import com.facebook.FacebookSdk
import com.google.android.gms.auth.api.identity.Identity

class MainActivity : ComponentActivity() {
    private lateinit var authManager: FacebookAuthManager
    private lateinit var viewModel: FacebookLoginViewModel

    private val googleAuthUiClient by lazy {
        GoogleAuthUiClient(
            context = this,
            oneTapClient = Identity.getSignInClient(this)
        )
    }

    private lateinit var callbackManager: CallbackManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        callbackManager = CallbackManager.Factory.create()
        FacebookSdk.sdkInitialize(applicationContext)
        viewModel = ViewModelProvider(this)[FacebookLoginViewModel::class.java]
        authManager = FacebookAuthManager(this, viewModel)

        enableEdgeToEdge()
        setContent {
            ResQnowTheme {
                Navigation(googleAuthUiClient, callbackManager)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        callbackManager.onActivityResult(requestCode, resultCode, data)
    }
}

sealed class Screen(val route: String) {
    object ScreenIntro1 : Screen("IntroAppScreen1")
    object ScreenIntro2 : Screen("IntroAppScreen2")
    object ScreenIntro3 : Screen("IntroAppScreen3")
    object ScreenIntro4 : Screen("IntroAppScreen4")
    object ScreenOutro1 : Screen("OutroAppScreen1")
    object ScreenOutro2 : Screen("OutroAppScreen2")
    object ScreenOutro3 : Screen("OutroAppScreen3")
    object HomePage1 : Screen("HomeScreen1")
    object LoginScreen : Screen("LoginScreen")
    object LoginSuccess : Screen("LoginSuccess")
    object SignInScreen : Screen("SignInScreen")
    object SignInSuccess : Screen("SignInSuccess")
    object ProfileScreen : Screen("ProfileScreen")
    object ProfileScreenWithoutAccount : Screen("ProfileScreenWithoutAccount")
}

@Composable
fun Navigation(googleAuthUiClient: GoogleAuthUiClient, callbackManager: CallbackManager) {
    val navController = rememberNavController()
    val userViewModel: UserViewModel = viewModel()

    NavHost(navController = navController, startDestination = Screen.ScreenIntro1.route) {
        // Intro Screens
        composable(Screen.ScreenIntro1.route) { IntroScreen1(navController = navController) }
        composable(Screen.ScreenIntro2.route) { IntroScreen2(navController = navController) }
        composable(Screen.ScreenIntro3.route) { IntroScreen3(navController = navController) }
        composable(Screen.ScreenIntro4.route) { IntroScreen4(navController = navController) }

        // Outro Screens
        composable(Screen.ScreenOutro1.route) { OutroScreen1(navController = navController) }
        composable(Screen.ScreenOutro2.route) { OutroScreen2(navController = navController) }
        composable(Screen.ScreenOutro3.route) { OutroScreen3(navController = navController) }

        // Sign In
        composable(Screen.SignInScreen.route) {
            SignInScreen(
                navController = navController,
                googleAuthUiClient = googleAuthUiClient,
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
        composable(Screen.LoginScreen.route) { LoginScreen(navController = navController) }
        composable(Screen.LoginSuccess.route) { LoginScreenSuccess(navController = navController) }

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

        // Homepage
        composable(Screen.HomePage1.route) {
            HomePage1(navController = navController, googleAuthUiClient = googleAuthUiClient)
        }

        // Other Screens
        composable("IntroductionGuide") { IntroductionGuide(navController = navController) }
        composable("LearnFirstAid") { LearnFirstAid(navController = navController) }
        composable("Personalization") { Personalization(navController = navController) }
        composable("EmergencyInstructions") { EmergencyInstructions(navController = navController) }
        composable("Maps") { Maps(navController = navController) }
    }
}
