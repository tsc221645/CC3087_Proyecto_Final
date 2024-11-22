package com.uvg.ana.booktribev2.home


import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State

class PostsViewModel : ViewModel() {
    private val _posts = mutableStateOf<List<PostData>>(listOf()) // Lista de posts
    val posts: State<List<PostData>> = _posts

    // Función para agregar un nuevo post
    fun addPost(post: PostData) {
        _posts.value = _posts.value + post
    }
}

