package com.uvg.ana.booktribev2.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.uvg.ana.booktribev2.components.BottomBar
import com.uvg.ana.booktribev2.components.TopBar
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

data class PostData(val name: String, val username: String, val time: String, val postText: String)

@Composable
fun HomeRoute(navController: NavController) {
    MainScreen()
}

@Composable
fun MainScreen() {
    // Obtén el ViewModel para gestionar los posts
    val postsViewModel: PostsViewModel = viewModel() // Usa viewModel() para obtener el ViewModel
    val posts = postsViewModel.posts.value // Obtiene la lista de posts

    var showDialog by remember { mutableStateOf(false) }
    var name by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var postText by remember { mutableStateOf("") }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { showDialog = true },
                modifier = Modifier.padding(16.dp)
            ) {
                Text("+", fontSize = 24.sp)
            }
        },
    ) { paddingValues ->
        Box(modifier = Modifier.fillMaxSize().padding(paddingValues)) {
            Column {
                LazyColumn(
                    modifier = Modifier.fillMaxSize().padding(16.dp)
                ) {
                    items(posts) { post ->
                        PostCardWithSearch(post)
                    }
                }
            }

            // Mostrar el diálogo para agregar un nuevo post
            if (showDialog) {
                AddPostDialog(
                    onDismiss = { showDialog = false },
                    onConfirm = {
                        val currentTime = getCurrentTime()

                        // Usamos el ViewModel para agregar el nuevo post
                        postsViewModel.addPost(PostData(name, username, currentTime, postText))

                        // Limpiamos los campos
                        name = ""
                        username = ""
                        postText = ""

                        showDialog = false
                    },
                    name = name,
                    onNameChange = { name = it },
                    username = username,
                    onUsernameChange = { username = it },
                    postText = postText,
                    onPostTextChange = { postText = it }
                )
            }
        }
    }
}

@Composable
fun PostCardWithSearch(post: PostData) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .border(2.dp, Color.Gray, RoundedCornerShape(16.dp)),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .background(MaterialTheme.colorScheme.primary, shape = CircleShape)
                        .padding(end = 8.dp)
                )
                Column {
                    Text(text = post.name, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSurface)
                    Text(text = post.username, color = MaterialTheme.colorScheme.onSurfaceVariant, fontSize = 12.sp)
                }
                Spacer(modifier = Modifier.weight(1f))
                Text(text = post.time, color = MaterialTheme.colorScheme.onSurfaceVariant, fontSize = 12.sp)
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = post.postText, color = MaterialTheme.colorScheme.onSurface)
        }
    }
}

@Composable
fun AddPostDialog(
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
    name: String,
    onNameChange: (String) -> Unit,
    username: String,
    onUsernameChange: (String) -> Unit,
    postText: String,
    onPostTextChange: (String) -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Add New Post") },
        text = {
            Column {
                TextField(
                    value = name,
                    onValueChange = onNameChange,
                    label = { Text("Name") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = username,
                    onValueChange = onUsernameChange,
                    label = { Text("Username") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = postText,
                    onValueChange = onPostTextChange,
                    label = { Text("Post Text") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirm() // Confirma la acción y agrega la tarjeta
                }
            ) {
                Text("Add")
            }
        },
        dismissButton = {
            TextButton(
                onClick = onDismiss // Solo cierra el diálogo
            ) {
                Text("Cancel")
            }
        }
    )
}

fun getCurrentTime(): String {
    // Obtiene la fecha y hora actual
    val currentDate = Date()

    // Define el formato de la fecha y hora
    val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())

    // Formatea la fecha y hora actual al formato especificado
    return sdf.format(currentDate)
}