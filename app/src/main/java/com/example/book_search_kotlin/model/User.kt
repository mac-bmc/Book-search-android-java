package com.example.book_search_kotlin.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "UserDetails")
data class User(
    @PrimaryKey(autoGenerate = true)
    val userId: Int,
    val userName: String,
    val userPswd: String,
)