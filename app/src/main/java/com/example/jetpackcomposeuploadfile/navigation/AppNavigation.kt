package com.example.jetpackcomposeuploadfile.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jetpackcomposeuploadfile.ui.presentation.screens.FakeData
import com.example.jetpackcomposeuploadfile.ui.presentation.screens.ListFilesFake
import com.example.jetpackcomposeuploadfile.ui.presentation.screens.LoginScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "login"
    ) {
        composable("login") {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate("listFiles")
                }
            )
        }

        composable("listFiles") {
            ListFilesFake(FakeData.fileItems)
        }
    }
}
