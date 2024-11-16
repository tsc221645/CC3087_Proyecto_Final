package com.uvg.ana.booktribev2.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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
import coil.compose.rememberImagePainter
import com.uvg.ana.booktribev2.viewmodel.BooksViewModel

@Composable
fun SearchScreen(booksViewModel: BooksViewModel = viewModel()) {
    val books by booksViewModel.books.collectAsState()
    val loading by booksViewModel.loading.collectAsState()
    var query by remember { mutableStateOf(TextFieldValue("")) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top
    ) {
        // Input field for search query
        BasicTextField(
            value = query,
            onValueChange = { query = it },
            textStyle = TextStyle(
                color = Color.White, // Set the text color to white
                fontSize = MaterialTheme.typography.bodyLarge.fontSize
            ),
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = MaterialTheme.colorScheme.surface,
                    shape = MaterialTheme.shapes.small
                )
                .padding(8.dp)
                .height(56.dp),
            decorationBox = { innerTextField ->
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.CenterStart
                ) {
                    if (query.text.isEmpty()) {
                        Text(
                            text = "Search books",
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                    innerTextField()
                }
            }
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Search button
        Button(
            onClick = {
                println("Search button clicked with query: ${query.text}")
                booksViewModel.searchBooks(query.text)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Search")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Show a loading indicator or results
        if (loading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
        } else {
            books.forEach { book ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = rememberImagePainter(data = book.volumeInfo.imageLinks?.thumbnail),
                        contentDescription = null,
                        modifier = Modifier.size(64.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Column {
                        Text(
                            text = book.volumeInfo.title,
                            style = MaterialTheme.typography.bodyLarge
                        )
                        Text(
                            text = book.volumeInfo.authors?.joinToString(", ") ?: "Unknown Author",
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
            }
        }
    }
}
