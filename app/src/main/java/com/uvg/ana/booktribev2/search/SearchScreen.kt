package com.uvg.ana.booktribev2.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.uvg.ana.booktribev2.explore.BookCard
import com.uvg.ana.booktribev2.network.BookItem
import com.uvg.ana.booktribev2.viewmodel.BooksViewModel



@Composable
fun SearchScreen(
    navController: NavController, // NavController for navigation
    booksViewModel: BooksViewModel = viewModel()
) {
    val books by booksViewModel.books.collectAsState()
    val loading by booksViewModel.loading.collectAsState()
    var query by remember { mutableStateOf(TextFieldValue("")) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Search Input
        BasicTextField(
            value = query,
            onValueChange = { query = it },
            textStyle = TextStyle(color = MaterialTheme.colorScheme.onBackground),
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = MaterialTheme.colorScheme.surface,
                    shape = MaterialTheme.shapes.small
                )
                .padding(8.dp)
                .height(56.dp)
        ) {
            Box(
                contentAlignment = Alignment.CenterStart,
                modifier = Modifier.fillMaxSize()
            ) {
                if (query.text.isEmpty()) {
                    Text(
                        text = "Search books",
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
                it()
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Search Button
        Button(
            onClick = { booksViewModel.searchBooks(query.text) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Search")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Display Loading, Empty State, or Results
        when {
            loading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            books.isNullOrEmpty() -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "No books available",
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
                                // Navigate to BookDetailsScreen
                                navController.navigate("bookDetails/${book.id}")
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun BookCard(
    book: BookItem,
    onClick: () -> Unit
) {
    val imageUrl = book.volumeInfo.imageLinks?.thumbnail ?: book.volumeInfo.imageLinks?.smallThumbnail

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Thumbnail Image
        if (!imageUrl.isNullOrEmpty()) {
            Image(
                painter = rememberImagePainter(data = imageUrl),
                contentDescription = "Book Thumbnail",
                modifier = Modifier
                    .size(64.dp)
                    .background(color = Color.Gray, shape = RoundedCornerShape(4.dp))
            )
        } else {
            Box(
                modifier = Modifier
                    .size(64.dp)
                    .background(MaterialTheme.colorScheme.surface),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "No Image",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }

        Spacer(modifier = Modifier.width(8.dp))

        // Book Info
        Column {
            Text(
                text = book.volumeInfo.title ?: "Unknown Title",
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = book.volumeInfo.authors?.joinToString(", ") ?: "Unknown Author",
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}