package com.uvg.ana.booktribev2.login


import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.uvg.ana.booktribev2.home.MainScreen
import com.uvg.ana.booktribev2.register.RegisterScreen
// Define navigation routes
sealed class Screen(val route: String) {
    object Login : Screen("login")
    object Register : Screen("register")
    object Home : Screen("home")
}

fun NavGraphBuilder.LoginNavigation(navController: NavController) {
    composable("login") {
        LoginScreen(navController)
    }
    composable("register") {
        RegisterScreen(
            onRegisterSuccess = {
                navController.popBackStack()
                navController.navigate("login")
            },
            onLoginClick = {
                navController.popBackStack()
                navController.navigate("login")
            }
        )
    }
    composable("home") {
        MainScreen() // Replace with your actual Home composable
    }
}

