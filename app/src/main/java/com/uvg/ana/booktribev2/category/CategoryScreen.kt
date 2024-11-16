package com.uvg.ana.booktribev2.category

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.uvg.ana.booktribev2.viewmodel.BooksViewModel
import com.uvg.ana.booktribev2.explore.BookCard

import java.net.URLDecoder

@Composable
fun CategoryScreen(
    navController: NavController, // Accept navController as a parameter
    category: String,
    booksViewModel: BooksViewModel = viewModel(),
    onBookClick: (String) -> Unit
) {
    // State for loading and books
    val loading by booksViewModel.loading.collectAsState()
    val books by booksViewModel.books.collectAsState()

    // Trigger the API call when the screen loads
    LaunchedEffect(category) {
        booksViewModel.getBooksByCategory(category)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = category,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Show loading indicator or results
        if (loading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
        } else if (books.isEmpty()) {
            Text("No books available in this category", style = MaterialTheme.typography.bodyLarge)
        } else {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(books.size) { index ->
                    val book = books[index]
                    BookCard(
                        title = book.volumeInfo.title,
                        author = book.volumeInfo.authors?.joinToString(", ") ?: "Unknown Author",
                        thumbnail = book.volumeInfo.imageLinks?.thumbnail,
                        onClick = {
                            navController.navigate("bookDetails/${book.id}") // Use the book's unique ID
                        }
                    )
                }
            }


        }
    }
}
