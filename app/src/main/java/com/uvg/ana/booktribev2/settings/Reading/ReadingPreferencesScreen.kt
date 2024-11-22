package com.uvg.ana.booktribev2.settings.Reading

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController

import com.uvg.ana.booktribev2.components.TopBar

class ReadingPreferencesActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ReadingPreferencesScreen()
        }
    }
}

@Composable
fun ReadingPreferencesScreen() {
    val navController = rememberNavController()
    var selectedItem by remember { mutableStateOf(0) }

    Scaffold(
       
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                ReadingPreferenceOption("Font Size", "Allow users to adjust the font size for comfort.")
                ReadingPreferenceOption("Font Type", "Option to change the font style.")
                ReadingPreferenceOption("Reading Mode", "Options to switch between day, night, and sepia modes.")
                ReadingPreferenceOption("Margins and Spacing", "Adjust margins and line spacing.")
                ReadingPreferenceOption("Reading Orientation", "Switch between horizontal and vertical reading.")
            }
        }
    )
}

@Composable
fun ReadingPreferenceOption(title: String, description: String) {
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

@Preview(showBackground = true)
@Composable
fun ReadingPreferencesScreenPreview() {
    ReadingPreferencesScreen()
}