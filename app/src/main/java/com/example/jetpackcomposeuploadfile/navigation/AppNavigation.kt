package com.example.jetpackcomposeuploadfile.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.jetpackcomposeuploadfile.domain.model.FileItemModel
import com.example.jetpackcomposeuploadfile.ui.presentation.screens.FileDetailScreen
import com.example.jetpackcomposeuploadfile.ui.presentation.screens.ListFiles
import com.example.jetpackcomposeuploadfile.ui.presentation.screens.LoginScreen
import kotlinx.serialization.Serializable

@Serializable
object Login

@Serializable
object ListFiles

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Login
    ) {

        composable<Login> {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate(ListFiles) {
                        popUpTo(Login) { inclusive = true }
                    }
                }
            )
        }

        composable<ListFiles> {
            ListFiles(
                onFileClick = { file ->

                    navController.navigate(file)
                }
            )
        }


        composable<FileItemModel> { backStackEntry ->
            val file: FileItemModel = backStackEntry.toRoute()

            FileDetailScreen(
                file = file,
                onBack = { navController.popBackStack() }
            )
        }
    }
}
