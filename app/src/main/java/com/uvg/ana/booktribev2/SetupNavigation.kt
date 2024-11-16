package com.uvg.ana.booktribev2

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.uvg.ana.booktribev2.components.BottomBar
import com.uvg.ana.booktribev2.components.TopBar
import com.uvg.ana.booktribev2.login.LoginScreen
import com.uvg.ana.booktribev2.register.RegisterScreen
import com.uvg.ana.booktribev2.home.HomeRoute
import com.uvg.ana.booktribev2.profile.ProfileScreen

@Composable
fun SetupNavigation() {
    val navController = rememberNavController()

    // Get the current route
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route

    val showBars = currentRoute != "login" && currentRoute != "register"

    Scaffold(
        topBar = {
            if (showBars) {
                TopBar(title = "Home", navController = navController)
            }
        },
        bottomBar = {
            if (showBars) {
                BottomBar(navController = navController)
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "login",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("login") {
                LoginScreen(navController = navController)
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
                HomeRoute(navController = navController)
            }
            composable("explore") {
                // Implement Explore Screen here
            }
            composable("search") {
                // Implement Search Screen here
            }
            composable("saved") {
                // Implement Saved Screen here
            }
            composable("profile") {
                ProfileScreen(navController = navController)
            }
        }
    }
}
