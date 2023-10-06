package com.example.book_search_kotlin.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {



    @Insert()
    suspend fun insertUser(user: User)

    @Query("Select ")



}