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


    fun searchBooks(query: String) {
        viewModelScope.launch {
            _loading.value = true
            try {
                if (query.isNotBlank()) {
                    val apiKey = BuildConfig.GOOGLE_BOOKS_API_KEY
                    println("Searching for books with query: $query and API key: $apiKey")
                    val response = repository.searchBooks(query, apiKey)
                    _books.value = response.items
                } else {
                    println("Query is empty")
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

}
