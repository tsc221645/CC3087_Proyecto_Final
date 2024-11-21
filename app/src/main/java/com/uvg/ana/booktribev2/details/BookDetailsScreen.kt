package com.uvg.ana.booktribev2.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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
import coil.compose.rememberImagePainter
import com.uvg.ana.booktribev2.viewmodel.BooksViewModel

@Composable
fun BookDetailsScreen(
    bookId: String,
    booksViewModel: BooksViewModel = viewModel()
) {
    val book = booksViewModel.getBookDetails(bookId)

    if (book == null) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text("Book details not available")
        }
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                text = book.volumeInfo.title,
                style = MaterialTheme.typography.headlineMedium
            )
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
        }
    }
}