package com.uvg.ana.booktribev2.category

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
    category: String,
    navController: androidx.navigation.NavController,
    booksViewModel: BooksViewModel = viewModel(),
    onBookClick: (String) -> Unit
) {
    val books by booksViewModel.books.collectAsState()
    val loading by booksViewModel.loading.collectAsState()

    // Fetch books for the selected category
    LaunchedEffect(category) {
        booksViewModel.getBooksByCategory(category)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Category: $category",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        when {
            loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            books.isNullOrEmpty() -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "No books available in this category",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }

            else -> {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(books) { book ->
                        BookCard(
                            book = book,
                            onClick = {
                                onBookClick(book.id) // Call onBookClick with book ID
                            }
                        )
                    }
                }
            }
        }
    }
}
