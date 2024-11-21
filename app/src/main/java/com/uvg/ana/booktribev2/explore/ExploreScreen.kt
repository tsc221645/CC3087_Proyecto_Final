package com.uvg.ana.booktribev2.explore

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
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
import com.uvg.ana.booktribev2.viewmodel.BooksViewModel
import java.net.URLEncoder

@Composable
fun ExploreScreen(
    navController: NavController,
    booksViewModel: BooksViewModel = viewModel(),
    onBookClick: (String) -> Unit,
    onCategoryClick: (String) -> Unit
) {
    val books by booksViewModel.books.collectAsState()
    val loading by booksViewModel.loading.collectAsState()
    val categories = listOf(
        "Most Searched",
        "Adventure",
        "Comedy",
        "Fiction",
        "Romance",
        "Science",
        "Fantasy",
        "History",
        "Horror",
        "Mystery"
    )

    // Estado para la categoría seleccionada
    var selectedCategory by remember { mutableStateOf(categories[0]) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Explore",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Categorías
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(categories.size) { index ->
                val category = categories[index]
                Button(
                    onClick = {
                        selectedCategory = category
                        val encodedCategory = URLEncoder.encode(category, "UTF-8")
                        onCategoryClick(encodedCategory)
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (selectedCategory == category) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface,
                        contentColor = if (selectedCategory == category) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface
                    ),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text(
                        text = category,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Libros
        if (loading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
        } else if (books.isEmpty()) {
            Text("No books available for this category", style = MaterialTheme.typography.bodyLarge)
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
                            navController.navigate("bookDetails/${book.id}")
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun BookCard(
    title: String,
    author: String,
    thumbnail: String?,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = rememberImagePainter(data = thumbnail),
            contentDescription = null,
            modifier = Modifier.size(64.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text(text = title, style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Bold)
            Text(text = author, style = MaterialTheme.typography.bodySmall)
        }
    }
}
