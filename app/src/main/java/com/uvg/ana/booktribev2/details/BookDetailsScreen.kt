package com.uvg.ana.booktribev2.details

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.uvg.ana.booktribev2.viewmodel.BooksViewModel

@Composable
fun BookDetailsScreen(bookId: String, booksViewModel: BooksViewModel = viewModel()) {
    // Observe the selected book and loading state
    val isBookmarked = remember { mutableStateOf(false) }
    val bookState by booksViewModel.selectedBook.collectAsState()
    val loading by booksViewModel.loading.collectAsState()

    // Fetch book details when the screen loads
    LaunchedEffect(bookId) {
        booksViewModel.fetchBookDetails(bookId)
    }

    if (loading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        val book = bookState // Safely use bookState locally
        if (book == null) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Book details not available")
            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text(
                        text = book.volumeInfo.title,
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.weight(1f)
                    )

                    IconButton(
                        onClick = { isBookmarked.value = !isBookmarked.value }
                    ) {
                        Icon(
                            imageVector = if (isBookmarked.value) Icons.Filled.Bookmark else Icons.Filled.BookmarkBorder,
                            contentDescription = if (isBookmarked.value) "Remove from saved" else "Save book",
                            tint = if (isBookmarked.value) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
                        )
                    }

                }

                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Author(s): ${book.volumeInfo.authors?.joinToString(", ") ?: "Unknown"}",
                    style = MaterialTheme.typography.bodyLarge
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = book.volumeInfo.description ?: "No description available.",
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Published Date: ${book.volumeInfo.publishedDate ?: "Unknown"}",
                    style = MaterialTheme.typography.bodySmall
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Page Count: ${book.volumeInfo.pageCount ?: "Unknown"}",
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}


