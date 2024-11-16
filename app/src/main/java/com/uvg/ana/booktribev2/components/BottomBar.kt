package com.uvg.ana.booktribev2.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomBar(navController: NavController) {
    val items = listOf(

        BottomNavItem.Explore,
        BottomNavItem.Search,
        BottomNavItem.Home,    // New Home option
        BottomNavItem.Saved
    )

    NavigationBar {
        val currentRoute = navController.currentBackStackEntryAsState()?.value?.destination?.route
        items.forEach { item ->
            NavigationBarItem(
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.startDestinationId) { inclusive = false }
                        launchSingleTop = true
                    }
                },
                icon = { Icon(imageVector = item.icon, contentDescription = item.label) },
                label = { Text(text = item.label) }
            )
        }
    }
}

sealed class BottomNavItem(val route: String, val icon: ImageVector, val label: String) {

    object Explore : BottomNavItem("explore", Icons.Default.Explore, "Explore")
    object Search : BottomNavItem("search", Icons.Default.Search, "Search")
    object Home : BottomNavItem("home", Icons.Default.Home, "Home")    // New Home option
    object Saved : BottomNavItem("saved", Icons.Default.Bookmark, "Saved")
}
