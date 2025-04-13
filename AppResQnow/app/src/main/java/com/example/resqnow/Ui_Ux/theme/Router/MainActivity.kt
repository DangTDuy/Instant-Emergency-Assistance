package com.example.resqnow.Ui_Ux.theme.Router

import android.app.Activity
import android.content.Intent
<<<<<<< Updated upstream
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
=======
<<<<<<< HEAD
import com.facebook.FacebookSdk
import com.facebook.appevents.AppEventsLogger
import android.os.Bundle
import com.google.android.gms.auth.api.identity.Identity
import androidx.activity.ComponentActivity
import androidx.activity.compose.LocalActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.resqnow.Data.Api_and_Firebase.FireBase.FirebaseFacebook.FacebookAuthUiClient
import com.example.resqnow.Data.Api_and_Firebase.FireBaseGoogle.GoogleAuthUiClient
import com.example.resqnow.Data.Api_and_Firebase.FireBaseGoogle.UserViewModel
import com.example.resqnow.Ui_Ux.theme.ResQnowTheme
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
=======
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
>>>>>>> Stashed changes
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.resqnow.Data.Api_and_Firebase.FireBase.FirebaseFacebook.FacebookAuthManager
import com.example.resqnow.Data.Api_and_Firebase.FireBase.FirebaseFacebook.FacebookLoginViewModel
import com.example.resqnow.Data.Api_and_Firebase.FireBaseGoogle.GoogleAuthUiClient
import com.example.resqnow.Data.Api_and_Firebase.FireBaseGoogle.SignInViewModel
import com.example.resqnow.Data.Api_and_Firebase.FireBaseGoogle.UserViewModel
>>>>>>> cfcab10a0125c08aeada692313bd29ffbaaff774
import com.example.resqnow.Ui_Ux.theme.EmergencyInstructions.EmergencyInstructions
import com.example.resqnow.Ui_Ux.theme.SignIn.SignInScreen
import com.example.resqnow.Ui_Ux.theme.SignIn.SignInSuccessScreen
import com.example.resqnow.Ui_Ux.theme.Login.LoginScreen
<<<<<<< HEAD
import com.example.resqnow.Ui_Ux.theme.Profile.ProfileScreen
import com.example.resqnow.Ui_Ux.theme.Profile.ProfileScreenWithoutAccount
import com.example.resqnow.Ui_Ux.theme.Homepage.HomePage1
import com.example.resqnow.Ui_Ux.theme.IntroductionGuide.IntroductionGuide
import com.example.resqnow.Ui_Ux.theme.Login.LoginScreenSuccess
<<<<<<< Updated upstream
=======

=======
import com.example.resqnow.Ui_Ux.theme.Login.LoginScreenSuccess
>>>>>>> Stashed changes
import com.example.resqnow.Ui_Ux.theme.Maps.Maps
import com.example.resqnow.Ui_Ux.theme.Profile.ProfileScreen
import com.example.resqnow.Ui_Ux.theme.Profile.ProfileScreenWithoutAccount
import com.example.resqnow.Ui_Ux.theme.ResQnowTheme
import com.example.resqnow.Ui_Ux.theme.SignIn.SignInScreen
import com.example.resqnow.Ui_Ux.theme.SignIn.SignInSuccessScreen
import com.example.resqnow.Ui_Ux.theme.common.IntroScreen1
<<<<<<< Updated upstream
=======
>>>>>>> cfcab10a0125c08aeada692313bd29ffbaaff774
>>>>>>> Stashed changes
import com.example.resqnow.Ui_Ux.theme.introApp.IntroScreen2
import com.example.resqnow.Ui_Ux.theme.introApp.IntroScreen3
import com.example.resqnow.Ui_Ux.theme.introApp.IntroScreen4
import com.example.resqnow.Ui_Ux.theme.outroApp.OutroScreen1
import com.example.resqnow.Ui_Ux.theme.outroApp.OutroScreen2
import com.example.resqnow.Ui_Ux.theme.outroApp.OutroScreen3
import com.facebook.CallbackManager
<<<<<<< Updated upstream
import com.facebook.FacebookSdk
import com.google.android.gms.auth.api.identity.Identity
=======
<<<<<<< HEAD
import com.example.resqnow.Ui_Ux.theme.common.IntroScreen1

=======
import com.facebook.FacebookSdk
import com.google.android.gms.auth.api.identity.Identity
>>>>>>> cfcab10a0125c08aeada692313bd29ffbaaff774

import com.example.resqnow.Ui_Ux.theme.learnfirstaid.LearnFirstAid
import com.example.resqnow.Ui_Ux.theme.personalization.Personalization
>>>>>>> Stashed changes

class MainActivity : ComponentActivity() {
<<<<<<< HEAD

    lateinit var facebookAuthUiClient: FacebookAuthUiClient
=======
    private lateinit var authManager: FacebookAuthManager
    private lateinit var viewModel: FacebookLoginViewModel

<<<<<<< Updated upstream
=======
>>>>>>> cfcab10a0125c08aeada692313bd29ffbaaff774
>>>>>>> Stashed changes
    private val googleAuthUiClient by lazy {
        GoogleAuthUiClient(
            context = this,
            oneTapClient = Identity.getSignInClient(this)
        )
    }

    private lateinit var callbackManager: CallbackManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
<<<<<<< Updated upstream
=======
<<<<<<< HEAD
=======
>>>>>>> Stashed changes
        callbackManager = CallbackManager.Factory.create()
        FacebookSdk.sdkInitialize(applicationContext)
        viewModel = ViewModelProvider(this)[FacebookLoginViewModel::class.java]
        authManager = FacebookAuthManager(this, viewModel)
>>>>>>> cfcab10a0125c08aeada692313bd29ffbaaff774

        // Khởi tạo Facebook SDK
        FacebookSdk.sdkInitialize(applicationContext)
        AppEventsLogger.activateApp(application)

        // Khởi tạo các client
        facebookAuthUiClient = FacebookAuthUiClient(this)
        callbackManager = CallbackManager.Factory.create()

        // Thiết lập Edge-to-Edge cho ứng dụng
        enableEdgeToEdge()

        // Set nội dung của activity
        setContent {
            ResQnowTheme {
<<<<<<< Updated upstream
=======
<<<<<<< HEAD
                // Truyền callbackManager vào hàm Navigation
                Navigation(
                    googleAuthUiClient = googleAuthUiClient,
                    facebookAuthUiClient = facebookAuthUiClient // truyền đúng instance
                )
=======
>>>>>>> Stashed changes
                Navigation(googleAuthUiClient, callbackManager)
>>>>>>> cfcab10a0125c08aeada692313bd29ffbaaff774
            }
        }
    }

    // Hàm xử lý kết quả từ Facebook login
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
<<<<<<< Updated upstream
        callbackManager.onActivityResult(requestCode, resultCode, data)
=======
<<<<<<< HEAD
        facebookAuthUiClient.onActivityResult(requestCode, resultCode, data)
=======
        callbackManager.onActivityResult(requestCode, resultCode, data)
>>>>>>> cfcab10a0125c08aeada692313bd29ffbaaff774
>>>>>>> Stashed changes
    }
}

sealed class Screen(val route: String) {
<<<<<<< Updated upstream
=======
<<<<<<< HEAD
    // Định nghĩa các màn hình
=======
>>>>>>> cfcab10a0125c08aeada692313bd29ffbaaff774
>>>>>>> Stashed changes
    object ScreenIntro1 : Screen("IntroAppScreen1")
    object ScreenIntro2 : Screen("IntroAppScreen2")
    object ScreenIntro3 : Screen("IntroAppScreen3")
    object ScreenIntro4 : Screen("IntroAppScreen4")
<<<<<<< Updated upstream
    object ScreenOutro1 : Screen("OutroAppScreen1")
    object ScreenOutro2 : Screen("OutroAppScreen2")
    object ScreenOutro3 : Screen("OutroAppScreen3")
=======
<<<<<<< HEAD
    object ScreenOutro1 : Screen("outroAppScreen1")
    object ScreenOutro2 : Screen("outroAppScreen2")
    object ScreenOutro3 : Screen("outroAppScreen3")
=======
    object ScreenOutro1 : Screen("OutroAppScreen1")
    object ScreenOutro2 : Screen("OutroAppScreen2")
    object ScreenOutro3 : Screen("OutroAppScreen3")
>>>>>>> cfcab10a0125c08aeada692313bd29ffbaaff774
>>>>>>> Stashed changes
    object HomePage1 : Screen("HomeScreen1")
    object LoginScreen : Screen("LoginScreen")
    object LoginSuccess : Screen("LoginSuccess")
    object SignInScreen : Screen("SignInScreen")
    object SignInSuccess : Screen("SignInSuccess")
    object ProfileScreen : Screen("ProfileScreen")
    object ProfileScreenWithoutAccount : Screen("ProfileScreenWithoutAccount")
}

@Composable
<<<<<<< Updated upstream
=======
<<<<<<< HEAD
fun Navigation(
    googleAuthUiClient: GoogleAuthUiClient,
    facebookAuthUiClient: FacebookAuthUiClient // thêm dòng này
) {
    val activity = LocalActivity.current
    val navController = rememberNavController()
    val userViewModel: UserViewModel = viewModel()

    // Kiểm tra null cho activity và truyền vào FacebookAuthUiClient
    if (activity != null) {
        val facebookAuthUiClient = FacebookAuthUiClient(activity)

        NavHost(navController = navController, startDestination = Screen.ScreenIntro1.route) {
            // Flow Intro
            composable("IntroAppScreen1") { IntroScreen1(navController = navController) }
            composable("IntroAppScreen2") { IntroScreen2(navController = navController) }
            composable("IntroAppScreen3") { IntroScreen3(navController = navController) }
            composable("IntroAppScreen4") { IntroScreen4(navController = navController) }

            // Flow Outro
            composable("OutroAppScreen1") { OutroScreen1(navController = navController) }
            composable("OutroAppScreen2") { OutroScreen2(navController = navController) }
            composable("OutroAppScreen3") { OutroScreen3(navController = navController) }

            // SignIn
            composable(Screen.SignInScreen.route) {
                SignInScreen(
                    navController = navController,
=======
>>>>>>> Stashed changes
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
>>>>>>> cfcab10a0125c08aeada692313bd29ffbaaff774
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
                    userViewModel = userViewModel)
            }
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
        }
<<<<<<< HEAD
    } else {
        // Handle case where activity is null (could be used for error logging or fallback)
=======

        // Homepage
        composable(Screen.HomePage1.route) {
            HomePage1(navController = navController, googleAuthUiClient = googleAuthUiClient)
        }

<<<<<<< Updated upstream
        // Homepage
        composable(Screen.HomePage1.route) {
            HomePage1(navController = navController, googleAuthUiClient = googleAuthUiClient)
        }

=======
>>>>>>> Stashed changes
        // Other Screens
        composable("IntroductionGuide") { IntroductionGuide(navController = navController) }
        composable("LearnFirstAid") { LearnFirstAid(navController = navController) }
        composable("Personalization") { Personalization(navController = navController) }
        composable("EmergencyInstructions") { EmergencyInstructions(navController = navController) }
        composable("Maps") { Maps(navController = navController) }
<<<<<<< Updated upstream
=======
>>>>>>> cfcab10a0125c08aeada692313bd29ffbaaff774
>>>>>>> Stashed changes
    }
}
