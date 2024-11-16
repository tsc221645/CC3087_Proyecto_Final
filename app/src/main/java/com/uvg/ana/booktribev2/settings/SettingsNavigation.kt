package com.uvg.ana.booktribev2.settings

import com.uvg.ana.booktribev2.settings.Accounts.AccountsAndPrivacyScreen
import com.uvg.ana.booktribev2.settings.Languages.LanguagesAndRegionalizationScreen
import com.uvg.ana.booktribev2.settings.Library.LibrarySettingsScreen
import com.uvg.ana.booktribev2.settings.Notifications.NotificationsScreen
import com.uvg.ana.booktribev2.settings.Reading.ReadingPreferencesScreen


import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


@Composable
fun SettingNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "settings") {
        composable("settings") { SettingsScreen(navController) }
        composable("readingPreferences") { ReadingPreferencesScreen() }
        composable("librarySettings") { LibrarySettingsScreen() }
        composable("notifications") { NotificationsScreen() }
        composable("accountsPrivacy") { AccountsAndPrivacyScreen() }
        composable("languagesRegion") { LanguagesAndRegionalizationScreen() }
    }
}
