package com.uvg.ana.booktribev2.settings.Accounts




import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.uvg.ana.booktribev2.settings.Languages.LanguagesAndRegionalizationScreen
import com.uvg.ana.booktribev2.settings.Library.LibrarySettingsScreen
import com.uvg.ana.booktribev2.settings.Notifications.NotificationsScreen
import com.uvg.ana.booktribev2.settings.SettingOption
import com.uvg.ana.booktribev2.settings.SettingOption2
import com.uvg.ana.booktribev2.settings.SettingOptionWithCheck
import com.uvg.ana.booktribev2.settings.SettingsScreenTemplate


class AccountsAndPrivacyActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AccountsAndPrivacyScreen()
        }
    }
}



@Composable
fun AccountsAndPrivacyScreen() {
    SettingsScreenTemplate("Accounts and Privacy") {
        SettingOptionWithCheck("Profile Management", "Create and manage reading profiles for different users.")
        SettingOption2("Privacy Settings", "Control who can see the user's reading activity.")
        SettingOption2("Security", "Set up a PIN or biometric authentication to protect the account.")
    }
}

@Preview(showBackground = true)
@Composable
fun LibrarySettingsScreenPreview() {
    AccountsAndPrivacyScreen()
}