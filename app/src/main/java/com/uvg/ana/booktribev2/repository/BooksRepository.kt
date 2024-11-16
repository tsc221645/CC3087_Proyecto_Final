package com.uvg.ana.booktribev2.repository

import com.uvg.ana.booktribev2.network.BookItem
import com.uvg.ana.booktribev2.network.BookResponse
import com.uvg.ana.booktribev2.network.GoogleBooksApiService
import com.uvg.ana.booktribev2.network.RetrofitInstance

class BooksRepository(private val apiService: GoogleBooksApiService = RetrofitInstance.api) {
    suspend fun searchBooks(query: String, apiKey: String): BookResponse {
        println("Making API call with query: $query")
        val response = apiService.searchBooks(query, apiKey)
        println("API Response: ${response.items.size} books retrieved")
        return response
    }

    suspend fun getBookDetails(bookId: String, apiKey: String): BookItem {
        return apiService.getBookDetails(bookId, apiKey)
    }

}
