package com.uvg.ana.booktribev2.repository

import com.uvg.ana.booktribev2.network.GoogleBooksApiService
import com.uvg.ana.booktribev2.network.RetrofitInstance

class BooksRepository(private val apiService: GoogleBooksApiService = RetrofitInstance.api) {
    suspend fun searchBooks(query: String, apiKey: String) = apiService.searchBooks(query, apiKey)
}
