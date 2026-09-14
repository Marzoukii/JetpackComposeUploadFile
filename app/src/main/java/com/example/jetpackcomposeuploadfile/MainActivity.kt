package com.example.jetpackcomposeuploadfile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jetpackcomposeuploadfile.ui.presentation.screens.FakeData
import com.example.jetpackcomposeuploadfile.ui.presentation.screens.ListFilesFake
import com.example.jetpackcomposeuploadfile.ui.presentation.screens.LoginScreen
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

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    
    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate("list_files") {
                        // Supprime l'écran de login de la pile pour ne pas y revenir avec "Retour"
                        popUpTo("login") { inclusive = true }
                    }
                }
            )
        }
        composable("list_files") {
            ListFilesFake(FakeData.fileItems)
        }
    }
}
