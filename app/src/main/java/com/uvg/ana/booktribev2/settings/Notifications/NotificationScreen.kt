package com.uvg.ana.booktribev2.settings.Notifications

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent


import androidx.compose.runtime.Composable
import com.uvg.ana.booktribev2.settings.SettingOptionWithCheck
import com.uvg.ana.booktribev2.settings.SettingsScreenTemplate


class NotificationsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NotificationsScreen()
        }
    }
}


@Composable
fun NotificationsScreen() {
    SettingsScreenTemplate("Notifications") {
        SettingOptionWithCheck("New Recommendations", "Enable or disable notifications about new book recommendations.")
        SettingOptionWithCheck("Reading Progress", "Notifications about reading progress and reminders to continue reading.")
        SettingOptionWithCheck("New Releases", "Alerts for new releases in the user's favorite genres.")
    }
}
