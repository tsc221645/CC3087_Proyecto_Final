package com.uvg.ana.booktribev2.explore

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.uvg.ana.booktribev2.network.BookItem
import com.uvg.ana.booktribev2.viewmodel.BooksViewModel
import java.net.URLEncoder

@Composable
fun ExploreScreen(
    navController: NavController,
    onCategoryClick: (String) -> Unit
) {
    val booksViewModel: BooksViewModel = viewModel()
    val books by booksViewModel.books.collectAsState()
    val loading by booksViewModel.loading.collectAsState()

    val categories = listOf("Adventure", "Fantasy", "Science", "Mystery", "Romance") // Categories

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Categories Section
        Text(
            text = "Categories",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        LazyColumn(
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            items(categories) { category ->
                Button(
                    onClick = { onCategoryClick(category) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                ) {
                    Text(text = category)
                }
            }
        }

        // Books Section
        Text(
            text = "Books",
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

            books.isEmpty() -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "No books available for this category",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
            else -> {
                if (books.isEmpty()) {
                    Text(
                        text = "No books found",
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                } else {
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        modifier = Modifier.fillMaxSize()
                    ) {
                        items(books) { book ->
                            BookCard(
                                book = book,
                                onClick = {
                                    navController.navigate("bookDetails/${book.id}")
                                }
                            )
                        }
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
        // Thumbnail
        if (!imageUrl.isNullOrEmpty()) {
            Image(
                painter = rememberImagePainter(data = imageUrl),
                contentDescription = "Book Thumbnail",
                modifier = Modifier.size(64.dp)
            )
        } else {
            Box(
                modifier = Modifier
                    .size(64.dp)
                    .background(Color.Gray),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "No Image",
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }

        Spacer(modifier = Modifier.width(8.dp))

        // Title and Author
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
