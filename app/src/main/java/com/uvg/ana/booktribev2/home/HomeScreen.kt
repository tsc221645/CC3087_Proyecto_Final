package com.uvg.ana.booktribev2.home


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.uvg.ana.booktribev2.components.BottomBar
import com.uvg.ana.booktribev2.components.TopBar



@Composable
fun HomeRoute(navController: NavController) {
    MainScreen() // Las barras se gestionan en SetupNavigation, no aquí
}

@Composable
fun MainScreen() {
    MaterialTheme(colorScheme = darkColorScheme()) {
        // Contenido principal de la pantalla
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Welcome to the Home Screen",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}

