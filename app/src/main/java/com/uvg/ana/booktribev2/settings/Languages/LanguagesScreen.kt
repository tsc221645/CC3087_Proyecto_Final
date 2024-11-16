package com.uvg.ana.booktribev2.settings.Languages

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.uvg.ana.booktribev2.settings.Reading.ReadingPreferencesScreen




import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.uvg.ana.booktribev2.settings.Library.LibrarySettingsScreen
import com.uvg.ana.booktribev2.settings.Notifications.NotificationsScreen
import com.uvg.ana.booktribev2.settings.SettingOption
import com.uvg.ana.booktribev2.settings.SettingOption2
import com.uvg.ana.booktribev2.settings.SettingOptionWithCheck
import com.uvg.ana.booktribev2.settings.SettingsScreenTemplate


class LanguagesAndRegionalizationActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LanguagesAndRegionalizationScreen()
        }
    }
}

@Composable
fun LanguagesAndRegionalizationScreen() {
    SettingsScreenTemplate("Languages and Regionalization") {
        SettingOption2("App Language", "Select the language of the interface.")
        SettingOptionWithCheck("Book Languages", "Filters to display books in certain languages.")
        SettingOptionWithCheck("Localized Content", "Settings to show content based on the user's region.")
    }
}


@Preview(showBackground = true)
@Composable
fun LibrarySettingsScreenPreview() {
    LanguagesAndRegionalizationScreen()
}
