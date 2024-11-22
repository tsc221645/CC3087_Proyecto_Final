package com.uvg.ana.booktribev2.database.dataUser

import androidx.room.Entity

@Entity
data class LoginUser(
    val email: String,
    val passWord: String,
    val name: String

)
