package com.example.book_search_kotlin.model

import android.util.Log
import androidx.lifecycle.LiveData

class BookRepository(private val userDao: UserDao) {

    val readAllData: LiveData<List<User>> = userDao.readAllData()

    suspend fun addBook(user: User)
    {
        userDao.insertBook(user)
    }
    suspend fun updateBook(user: User)
    {
        val title = user.bookTitle
        val authorName = user.authorName
        val pageNo=user.nofPages
        val id = user.bookId
        Log.d("updated id","$id")
        Log.d("updated title", title)
        Log.d("updated pageNo", pageNo)
        Log.d("updated author", authorName)
        userDao.updatebyId(title, authorName,pageNo,id)
    }

    suspend fun deleteBook(user: User)
    {
        val id = user.bookId
        userDao.deleteById(id)
    }

    suspend fun deleteAll()
    {
        userDao.deleteAll()
    }


}