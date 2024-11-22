package com.uvg.ana.booktribev2.userprofile



import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.uvg.ana.booktribev2.SupabaseAuthViewModel
import com.uvg.ana.booktribev2.settings.SettingNavigation
import com.uvg.ana.booktribev2.settings.SettingsScreen

@Composable
fun UserProfileNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "userProfile") {
        composable("userProfile") {
            UserProfileScreen(
                onEditProfileClick = { navController.navigate("editUserProfile") },
                onSettingsClick = { navController.navigate("settings") },
                onLogoutClick = {
                    // Simplemente navega al login y limpia el back stack
                    navController.navigate("login") {
                        // Limpiamos todo el back stack para que no se pueda regresar a esta pantalla
                        popUpTo("userProfile") { inclusive = true }
                        launchSingleTop = true // Evitar duplicados de la pantalla login
                    }
                },
                navController = navController // Pasamos el navController
            )
        }

        composable("editUserProfile") {
            EditUserProfileScreen(
                onSaveClick = { navController.popBackStack() },
                onCancelClick = { navController.popBackStack() }
            )
        }

        composable("settings") {
            SettingNavigation()
        }
    }
}
