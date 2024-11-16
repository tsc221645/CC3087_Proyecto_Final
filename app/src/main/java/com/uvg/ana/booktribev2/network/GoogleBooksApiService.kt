package com.uvg.ana.booktribev2.network


import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

data class BookResponse(
    val items: List<BookItem>
)

data class BookItem(
    val id: String,
    val volumeInfo: VolumeInfo
)

data class VolumeInfo(
    val title: String,
    val authors: List<String>?,
    val description: String?,
    val publishedDate: String?,
    val pageCount: Int?,
    val categories: List<String>?,
    val imageLinks: ImageLinks?
)

data class ImageLinks(
    val thumbnail: String
)
interface GoogleBooksApiService {
    @GET("volumes")
    suspend fun searchBooks(
        @Query("q") query: String,
        @Query("key") apiKey: String
    ): BookResponse

    @GET("volumes/{id}")
    suspend fun getBookDetails(
        @Path("id") id: String,
        @Query("key") apiKey: String
    ): BookItem

    companion object {
        fun create(): GoogleBooksApiService {
            return RetrofitInstance.retrofit.create(GoogleBooksApiService::class.java)
        }
    }
}