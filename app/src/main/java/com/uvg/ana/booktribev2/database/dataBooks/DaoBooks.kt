package com.uvg.ana.booktribev2.database.dataBooks

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query



@Dao
interface SavedBooksDao {
    @Insert
    suspend fun insert(savedBook: SavedBook)

    @Query("SELECT * FROM saved_books")
    suspend fun getAllSavedBooks(): List<SavedBook>

    @Query("DELETE FROM saved_books WHERE id = :bookId")
    suspend fun deleteById(bookId: String)
}
