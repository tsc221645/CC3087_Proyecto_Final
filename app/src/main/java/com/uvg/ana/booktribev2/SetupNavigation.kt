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
import com.uvg.ana.booktribev2.home.HomeRoute
import com.uvg.ana.booktribev2.login.LoginScreen
import com.uvg.ana.booktribev2.register.RegisterScreen
import com.uvg.ana.booktribev2.profile.ProfileScreen

@Composable
fun SetupNavigation() {
    val navController = rememberNavController()

    // Get the current route
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route

    // Determine the title based on the current route
    val title = when (currentRoute) {
        "home" -> "Home"
        "profile" -> "Profile"
        "explore" -> "Explore"
        "search" -> "Search"
        "saved" -> "Saved"
        else -> "App" // Default title
    }

    val showBars = currentRoute != "login" && currentRoute != "register"

    Scaffold(
        topBar = {
            if (showBars) {
                TopBar(title = title, navController = navController)
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
            // Login Screen
            composable("login") {
                LoginScreen(navController = navController)
            }
            // Register Screen
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
            // Home Screen
            composable("home") {
                HomeRoute(navController = navController)
            }
            // Profile Screen
            composable("profile") {
                ProfileScreen(navController = navController)
            }
            // Additional screens (explore, search, saved)
            composable("explore") {
                // Implement Explore Screen
            }
            composable("search") {
                // Implement Search Screen
            }
            composable("saved") {
                // Implement Saved Screen
            }
        }
    }
}
