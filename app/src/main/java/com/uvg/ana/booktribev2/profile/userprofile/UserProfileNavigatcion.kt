package com.uvg.ana.booktribev2.userprofile



import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.uvg.ana.booktribev2.settings.SettingNavigation
import com.uvg.ana.booktribev2.settings.SettingsScreen

@Composable
fun UserProfileNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "userProfile") {
        // Pantalla principal de perfil de usuario
        composable("userProfile") {
            UserProfileScreen(
                onEditProfileClick = { navController.navigate("editUserProfile") },
                onSettingsClick = { navController.navigate("settings") }
            )
        }

        // Pantalla de edición de perfil
        composable("editUserProfile") {
            EditUserProfileScreen(
                onSaveClick = { navController.popBackStack() }, // Vuelve al perfil después de guardar
                onCancelClick = { navController.popBackStack() } // Vuelve al perfil sin cambios
            )
        }

        // Pantalla de configuraciones
        composable("settings") {
            // Aquí llama a SettingsNavigation si tienes un gráfico separado para configuración
            SettingNavigation()
        }
    }
}
