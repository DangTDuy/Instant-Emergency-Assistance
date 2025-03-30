package com.example.resqnow.Ui_Ux.theme.Navigation // nới chứa các Navigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.resqnow.Ui_Ux.theme.ResQnowTheme
import com.example.resqnow.Ui_Ux.theme.introApp.IntroScreen
import com.example.resqnow.Ui_Ux.theme.introApp.IntroScreen2

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ResQnowTheme {
                IntroScreen2()
            }
            }
        }
    }





@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    ResQnowTheme {

            IntroScreen2()

    }
}