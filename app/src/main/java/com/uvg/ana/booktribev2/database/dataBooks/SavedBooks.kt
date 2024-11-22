package com.uvg.ana.booktribev2.database.dataBooks

import androidx.room.Entity
import androidx.room.PrimaryKey





@Entity(tableName = "saved_books")
data class SavedBook(
    @PrimaryKey val id: String,
    val title: String,
    val author: String
)