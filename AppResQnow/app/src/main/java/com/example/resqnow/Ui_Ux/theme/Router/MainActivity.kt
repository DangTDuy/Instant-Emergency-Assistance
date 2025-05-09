package com.example.resqnow.Ui_Ux.theme.Router

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.resqnow.Data.Api_and_Firebase.FireBase.FirebaseFacebook.FacebookAuthUiClient
import com.example.resqnow.Data.Api_and_Firebase.FireBaseGoogle.GoogleAuthUiClient
import com.example.resqnow.Data.Api_and_Firebase.FireBaseGoogle.UserViewModel
import com.example.resqnow.Ui_Ux.theme.*
import com.example.resqnow.Ui_Ux.theme.common.IntroScreen1
import com.example.resqnow.Ui_Ux.theme.introApp.*
import com.example.resqnow.Ui_Ux.theme.outroApp.*
import com.example.resqnow.Ui_Ux.theme.Login.*
import com.example.resqnow.Ui_Ux.theme.Profile.*
import com.example.resqnow.Ui_Ux.theme.Homepage.HomePage1
import com.example.resqnow.Ui_Ux.theme.IntroductionGuide.IntroductionGuide

import com.example.resqnow.Ui_Ux.theme.personalization.Personalization
import com.example.resqnow.Ui_Ux.theme.SignIn.*

import com.facebook.CallbackManager
import com.facebook.FacebookSdk
import com.facebook.appevents.AppEventsLogger
import com.google.android.gms.auth.api.identity.Identity
import androidx.navigation.compose.*

import com.example.resqnow.Repository.Repository
import com.example.resqnow.Room.DB_Contact.ResqNowDatabase
import com.example.resqnow.Ui_Ux.theme.FirstAidGuide.Food_poisoning.poisoning
import com.example.resqnow.Ui_Ux.theme.FirstAidGuide.Nosebleed.noseBleed
import com.example.resqnow.Ui_Ux.theme.FirstAidGuide.ViewMenu.MenuFirstAidGuide.FirstAidGuideScreen
import com.example.resqnow.Ui_Ux.theme.FirstAidGuide.ViewMenu.MenuFirstAidGuide.LC_FirstAids
import com.example.resqnow.Ui_Ux.theme.FirstAidGuide.Severed_finger.severed_finger
import com.example.resqnow.Ui_Ux.theme.FirstAidGuide.brokenArm.brokenArm1
import com.example.resqnow.Ui_Ux.theme.FirstAidGuide.deepCut.deepCut
import com.example.resqnow.Ui_Ux.theme.FirstAidGuide.nonVenomous_snake.nonVenomus_Snake
import com.example.resqnow.Ui_Ux.theme.FirstAidGuide.open_fracture.open_fracture
import com.example.resqnow.Ui_Ux.theme.FirstAidGuide.puncture_wound.puncture
import com.example.resqnow.Ui_Ux.theme.FirstAidGuide.stroke.Stroke
import com.example.resqnow.Ui_Ux.theme.FirstAidGuide.stroke.signsOfStroke
import com.example.resqnow.Ui_Ux.theme.FirstAidGuide.tendon_muscle_bruise.bruise
import com.example.resqnow.Ui_Ux.theme.FirstAidGuide.thermal_burn.burn
import com.example.resqnow.Ui_Ux.theme.FirstAidGuide.venomous_snake.venomus_Snake
import com.example.resqnow.Ui_Ux.theme.Maps.Maps


import com.example.resqnow.Ui_Ux.theme.contact.CardScreen
import com.example.resqnow.Ui_Ux.theme.contact.ContactScreen
import com.example.resqnow.Ui_Ux.theme.learnfirstaid.FilterInstructor
import com.example.resqnow.Ui_Ux.theme.personalization.Child.ChildPathologyScreen
import com.example.resqnow.Ui_Ux.theme.personalization.Child.ChildPreventivemeasures
import com.example.resqnow.Ui_Ux.theme.personalization.Child.ChildScreen
import com.example.resqnow.Ui_Ux.theme.personalization.MiddleAge.MiddleAgeScreen
import com.example.resqnow.Ui_Ux.theme.personalization.MiddleAge.MiddlePathology
import com.example.resqnow.Ui_Ux.theme.personalization.MiddleAge.MiddlePreventivemeasures
import com.example.resqnow.Ui_Ux.theme.personalization.Senior.SeniorPathology
import com.example.resqnow.Ui_Ux.theme.personalization.Senior.SeniorPreventivemeasures
import com.example.resqnow.Ui_Ux.theme.personalization.Senior.SeniorScreen
import com.example.resqnow.Ui_Ux.theme.personalization.YoungAdult.YoungAdultScreen
import com.example.resqnow.Ui_Ux.theme.personalization.YoungAdult.YoungPathology
import com.example.resqnow.Ui_Ux.theme.personalization.YoungAdult.YoungPreventivemeasures

import com.example.resqnow.viewModel.ContactViewModel

sealed class Screen(val route: String) {
    //Intro
    object ScreenIntro1 : Screen("IntroAppScreen1")
    object ScreenIntro2 : Screen("IntroAppScreen2")
    object ScreenIntro3 : Screen("IntroAppScreen3")
    object ScreenIntro4 : Screen("IntroAppScreen4")
    //Outro
    object ScreenOutro1 : Screen("OutroAppScreen1")
    object ScreenOutro2 : Screen("OutroAppScreen2")
    object ScreenOutro3 : Screen("OutroAppScreen3")
    //HomePage
    object HomePage1 : Screen("HomeScreen1")
    //Login
    object LoginScreen : Screen("LoginScreen")
    object LoginSuccess : Screen("LoginSuccess")
    //SignIn
    object SignInScreen : Screen("SignInScreen")
    object SignInSuccess : Screen("SignInSuccess")
    //ForgotPassword
    object ForgotPassword : Screen("ForgotPassword")
    //Profile
    object ProfileScreen : Screen("ProfileScreen")
    object ProfileScreenWithoutAccount : Screen("ProfileScreenWithoutAccount")
    //FirstAidGuide
    object FirstAidGuideScreen : Screen("FirstAidGuideScreen")
    object LC_FirstAids : Screen("LC_FirstAids")
    //Contact
    object ContactScreen : Screen("ContactScreen")
    //Case FOr FirstAIdGUide
    object brokenArmFirstAid1 : Screen("brokenArm1")
    object signsOfStroke : Screen("signsOfStroke")
    object Stroke : Screen("Stroke")
    object venomous_Snake : Screen("venomus_Snake")
    object burn : Screen("burn")
    object poisoning : Screen("poisoning")
    object nonVenomus_Snake : Screen("nonVenomus_Snake")
    object bruise : Screen("bruise")
    object open_fracture : Screen("open_fracture")
    object deepCut : Screen("deepCut")
    object puncture : Screen("punction_wound")
    object severed_finger : Screen("severed_finger")
    object noseBleed : Screen("noseBleed")
    //LearnFirstAid
    object LearnFirstAidScreen : Screen("LearnFirstAidScreen")


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
        val context = LocalContext.current
        val repository = Repository(ResqNowDatabase.getInstance(context))
        val contactViewModel: ContactViewModel = viewModel(factory = ContactViewModel.ContactViewModelFactory(repository))

        val showDialog = remember { mutableStateOf(false) }

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

            composable("Maps") { Maps(navController) }
            //login
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
                        },
                        navController
                    )
                } else {
                    ProfileScreenWithoutAccount(navController)
                }
            }

            // Homepage
            composable(Screen.HomePage1.route) {
                HomePage1(navController, googleAuthUiClient)
            }
            //================FirstAidGuide=================
            composable(Screen.FirstAidGuideScreen.route) {
                FirstAidGuideScreen(navController, viewModel = viewModel())
            }
            composable(Screen.LC_FirstAids.route) {
                LC_FirstAids(viewModel = viewModel(), searchText = "",navController)
            }

            //brokenArm
            composable(Screen.brokenArmFirstAid1.route){ brokenArm1(navController) }
            //stroke

                composable(Screen.signsOfStroke.route) { signsOfStroke(navController) }
                composable(Screen.Stroke.route) { Stroke(navController) }

            //Food_posoning
            composable(Screen.poisoning.route) { poisoning(navController) }
            //venomous_Snake
            composable(Screen.venomous_Snake.route) { venomus_Snake(navController) }
            //nonvenomous_Snake
            composable(Screen.nonVenomus_Snake.route) { nonVenomus_Snake(navController) }
            //bủn
            composable(Screen.burn.route) { burn(navController) }
            //bruise
            composable(Screen.bruise.route) { bruise(navController) }
            //open_fracture
            composable(Screen.open_fracture.route) { open_fracture(navController) }
            //deep_cut
            composable(Screen.deepCut.route) { deepCut(navController) }
            //punction_wound
            composable(Screen.puncture.route) { puncture(navController) }
            //severed_finger
            composable(Screen.severed_finger.route) { severed_finger(navController) }
            //noseBleed
            composable(Screen.noseBleed.route) { noseBleed(navController) }



            // ContactScreen
            composable(Screen.ContactScreen.route) {
                ContactScreen(contactViewModel, showDialog, navController)
            }

            // CardScreen
            composable("cardScreen/{contactId}") { backStackEntry ->
                val contactId = backStackEntry.arguments?.getString("contactId")?.toInt()
                contactId?.let {
                    CardScreen(navController = navController, viewModel = contactViewModel, contactId = it)
                }
            }
            //LearnFirstAid
            composable(Screen.LearnFirstAidScreen.route) {
                FilterInstructor(navController,context)
            }



            // Các màn hình khác
            composable("IntroductionGuide") { IntroductionGuide(navController) }

            composable("Personalization") {
                Personalization(navController)
            }
            composable("ChildPathologyScreen")  { ChildPathologyScreen(navController) }
            composable("ChildPreventivemeasures") { ChildPreventivemeasures(navController) }
            composable("ChildScreen") { ChildScreen(navController) }
            composable("YoungPathology") { YoungPathology(navController) }
            composable("YoungPreventivemeasures") { YoungPreventivemeasures(navController) }
            composable("YoungAdultScreen") { YoungAdultScreen(navController) }
            composable("MiddlePathology") { MiddlePathology(navController) }
            composable("MiddleAgeScreen") { MiddleAgeScreen(navController) }
            composable("MiddlePreventivemeasures") { MiddlePreventivemeasures(navController) }
            composable("SeniorPathology") { SeniorPathology(navController) }
            composable("SeniorPreventivemeasures") { SeniorPreventivemeasures(navController) }
            composable("SeniorScreen") { SeniorScreen(navController) }

        }


    }

}
