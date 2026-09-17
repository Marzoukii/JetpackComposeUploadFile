package com.example.jetpackcomposeuploadfile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.jetpackcomposeuploadfile.navigation.AppNavigation
import com.example.jetpackcomposeuploadfile.ui.theme.JetpackComposeUploadFileTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackComposeUploadFileTheme {

                AppNavigation()
            }
        }
    }
}
