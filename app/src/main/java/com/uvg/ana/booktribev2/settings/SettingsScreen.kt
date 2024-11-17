package com.uvg.ana.booktribev2.settings

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.*
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.filled.CheckBox
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

import com.uvg.ana.booktribev2.components.TopBar

class SettingsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            SettingNavigation()
        }
    }
}


@Composable
fun SettingsScreen(navController: NavHostController) {
    var selectedItem by remember { mutableStateOf(0) }

    Scaffold(
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                SettingOption(text = "Reading Preferences") {
                    navController.navigate("readingPreferences")
                }
                SettingOption(text = "Library Settings") {
                    navController.navigate("librarySettings")
                }
                SettingOption(text = "Notifications") {
                    navController.navigate("notifications")
                }
                SettingOption(text = "Accounts and Privacy") {
                    navController.navigate("accountsPrivacy")
                }
                SettingOption(text = "Languages and Region") {
                    navController.navigate("languagesRegion")
                }
            }
        }
    )
}

@Composable
fun SettingOption(text: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = text, fontSize = 18.sp, style = MaterialTheme.typography.headlineMedium)
        Icon(
            imageVector = Icons.Default.ArrowForward,
            contentDescription = "Arrow"
        )
    }
    Divider()
}



@Composable
fun SettingOption2(title: String, description: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { /* Acción cuando se hace clic en la opción */ }
            .padding(vertical = 8.dp)
    ) {
        Text(
            text = title,
            fontSize = 18.sp,
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = description,
            fontSize = 14.sp,
            style = MaterialTheme.typography.bodyMedium
        )
    }
    Divider(modifier = Modifier.padding(vertical = 8.dp))
}

@Composable
fun SettingOptionWithCheck(title: String, description: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.CheckBox,
            contentDescription = "Check",
            modifier = Modifier.padding(end = 16.dp)
        )
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                fontSize = 18.sp,
                style = MaterialTheme.typography.headlineMedium
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = description,
                fontSize = 14.sp,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
    Divider(modifier = Modifier.padding(vertical = 8.dp))
}

@Composable
fun SettingsScreenTemplate(title: String, content: @Composable ColumnScope.() -> Unit) {
    val navController = rememberNavController()
    var selectedItem by remember { mutableStateOf(0) }

    Scaffold(


        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
                    .padding(16.dp),
                content = content
            )
        }
    )
}


@Preview(showBackground = true)
@Composable
fun SettingsScreenPreview() {
    val navController = rememberNavController()
    SettingsScreen(navController = navController)
}
