package com.uvg.ana.booktribev2.settings.Library



import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckBox
import androidx.compose.material3.*
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController

import com.uvg.ana.booktribev2.settings.SettingOptionWithCheck
import com.uvg.ana.booktribev2.settings.SettingsScreenTemplate

class LibrarySettingsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LibrarySettingsScreen()
        }
    }
}

@Composable
fun LibrarySettingsScreen() {
    SettingsScreenTemplate("Library Settings") {
        SettingOptionWithCheck("Sort Books", "By author, title, date added, or popularity.")
        SettingOptionWithCheck("Filters", "Filter by genre, author, series, etc.")
        SettingOptionWithCheck("Synchronization", "Options to sync the library across devices.")
    }
}

@Preview(showBackground = true)
@Composable
fun LibrarySettingsScreenPreview() {
    LibrarySettingsScreen()
}

