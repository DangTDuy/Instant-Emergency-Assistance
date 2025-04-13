package com.example.resqnow.Ui_Ux.theme.Router

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.resqnow.Data.Api_and_Firebase.FireBase.FirebaseFacebook.FacebookAuthUiClient
import com.example.resqnow.Data.Api_and_Firebase.FireBaseGoogle.GoogleAuthUiClient
import com.example.resqnow.Data.Api_and_Firebase.FireBaseGoogle.UserViewModel
import com.example.resqnow.Ui_Ux.theme.*
import com.example.resqnow.Ui_Ux.theme.common.IntroScreen1
import com.example.resqnow.Ui_Ux.theme.introApp.*
import com.example.resqnow.Ui_Ux.theme.outroApp.*
import com.example.resqnow.Ui_Ux.theme.EmergencyInstructions.EmergencyInstructions
import com.example.resqnow.Ui_Ux.theme.Login.*
import com.example.resqnow.Ui_Ux.theme.Profile.*
import com.example.resqnow.Ui_Ux.theme.Homepage.HomePage1
import com.example.resqnow.Ui_Ux.theme.IntroductionGuide.IntroductionGuide
import com.example.resqnow.Ui_Ux.theme.learnfirstaid.LearnFirstAid
import com.example.resqnow.Ui_Ux.theme.personalization.Personalization
import com.example.resqnow.Ui_Ux.theme.SignIn.*

import com.facebook.CallbackManager
import com.facebook.FacebookSdk
import com.facebook.appevents.AppEventsLogger
import com.google.android.gms.auth.api.identity.Identity
import androidx.navigation.compose.*
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
class MainActivity : ComponentActivity() {

    private lateinit var facebookAuthUiClient: FacebookAuthUiClient
    private val googleAuthUiClient by lazy {
        GoogleAuthUiClient(
            context = this,
            oneTapClient = Identity.getSignInClient(this)
        )
    }

    private lateinit var callbackManager: CallbackManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        FacebookSdk.sdkInitialize(applicationContext)
        AppEventsLogger.activateApp(application)

        facebookAuthUiClient = FacebookAuthUiClient(this)
        callbackManager = CallbackManager.Factory.create()

        enableEdgeToEdge()

        setContent {
            ResQnowTheme {
                Navigation(
                    googleAuthUiClient = googleAuthUiClient,
                    facebookAuthUiClient = facebookAuthUiClient
                )
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        facebookAuthUiClient.onActivityResult(requestCode, resultCode, data)
    }
    @Composable
    fun Navigation(
        googleAuthUiClient: GoogleAuthUiClient,
        facebookAuthUiClient: FacebookAuthUiClient
    ) {
        val navController = rememberNavController()
        val userViewModel: UserViewModel = viewModel()

        NavHost(navController = navController, startDestination = Screen.ScreenIntro1.route) {
            // Intro Screens
            composable(Screen.ScreenIntro1.route) { IntroScreen1(navController) }
            composable(Screen.ScreenIntro2.route) { IntroScreen2(navController) }
            composable(Screen.ScreenIntro3.route) { IntroScreen3(navController) }
            composable(Screen.ScreenIntro4.route) { IntroScreen4(navController) }

            // Outro Screens
            composable(Screen.ScreenOutro1.route) { OutroScreen1(navController) }
            composable(Screen.ScreenOutro2.route) { OutroScreen2(navController) }
            composable(Screen.ScreenOutro3.route) { OutroScreen3(navController) }

            // Sign In
            composable(Screen.SignInScreen.route) {
                SignInScreen(
                    navController = navController,
                    googleAuthUiClient = googleAuthUiClient,
                    facebookAuthUiClient = facebookAuthUiClient,
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
            composable(Screen.LoginScreen.route) {
                LoginScreen(
                    navController = navController,
                    googleAuthUiClient = googleAuthUiClient,
                    facebookAuthUiClient = facebookAuthUiClient,
                    userViewModel = userViewModel
                )
            }

            composable(Screen.LoginSuccess.route) {
                LoginScreenSuccess(navController)
            }

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
                    ProfileScreenWithoutAccount(navController)
                }
            }

            // Homepage
            composable("HomeScreen1") {
                HomePage1(navController, googleAuthUiClient)
            }

            // Các màn hình khác
            composable("IntroductionGuide") { IntroductionGuide(navController) }
            composable("LearnFirstAid") { LearnFirstAid(navController) }
            composable("Personalization") { Personalization(navController) }
            composable("EmergencyInstructions") { EmergencyInstructions(navController) }
        }
    }

}
