package com.uvg.ana.booktribev2.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uvg.ana.booktribev2.network.BookItem
import com.uvg.ana.booktribev2.repository.BooksRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import com.uvg.ana.booktribev2.BuildConfig

class BooksViewModel(private val repository: BooksRepository = BooksRepository()) : ViewModel() {
    private val _books = MutableStateFlow<List<BookItem>>(emptyList())
    val books: StateFlow<List<BookItem>> = _books

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    private val _selectedBook = MutableStateFlow<BookItem?>(null)
    val selectedBook: StateFlow<BookItem?> = _selectedBook

    init {
        searchBooks("bestsellers") // Fetch default books for the Explore page
    }

    fun searchBooks(query: String) {
        viewModelScope.launch {
            _loading.value = true
            try {
                if (query.isNotBlank()) {
                    val apiKey = BuildConfig.GOOGLE_BOOKS_API_KEY
                    val response = repository.searchBooks(query, apiKey)
                    println("Books fetched: ${response.items.size} items")
                    _books.value = response.items
                } else {
                    println("Empty query")
                    _books.value = emptyList()
                }
            } catch (e: Exception) {
                println("Error fetching books: ${e.message}")
                _books.value = emptyList()
            } finally {
                _loading.value = false
            }
        }
    }

    fun getBooksByCategory(category: String) {
        viewModelScope.launch {
            _loading.value = true
            try {
                val apiKey = BuildConfig.GOOGLE_BOOKS_API_KEY
                val response = repository.searchBooks("subject:$category", apiKey) // Include category in query
                println("Books fetched for category '$category': ${response.items.size} items")
                _books.value = response.items // Update books list
            } catch (e: Exception) {
                println("Error fetching books for category '$category': ${e.message}")
                _books.value = emptyList() // Set empty list on error
            } finally {
                _loading.value = false
            }
        }
    }

    fun fetchBookDetails(bookId: String) {
        viewModelScope.launch {
            _loading.value = true
            try {
                val response = repository.getBookDetails(bookId)
                println("Fetched Book Details: $response")
                println("Thumbnail URL: ${response.volumeInfo.imageLinks?.thumbnail}")
                _selectedBook.value = response
            } catch (e: Exception) {
                println("Error fetching book details: ${e.message}")
                _selectedBook.value = null
            } finally {
                _loading.value = false
            }
        }
    }


    fun getBookDetails(bookId: String): BookItem? {
        return books.value.find { it.id == bookId }
    }



}

