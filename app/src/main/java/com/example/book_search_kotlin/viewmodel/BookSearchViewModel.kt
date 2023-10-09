package com.example.book_search_kotlin.viewmodel

import android.content.Context
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.book_search_kotlin.model.ApiInterface
import com.example.book_search_kotlin.view.BookAdapter
import com.example.book_search_kotlin.model.SearchResponse
import com.example.book_search_kotlin.model.BookModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class BookSearchViewModel : ViewModel() {


    private val baseUrl: String = "https://openlibrary.org/"
    private lateinit var openLibraryApi: ApiInterface


    fun performBookSearch(context: Context, query: String, bookAdapter: BookAdapter, progressBar: ProgressBar) {
        var books: List<BookModel>?
        viewModelScope.launch(Dispatchers.IO)
        {
            val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            openLibraryApi = retrofit.create(ApiInterface::class.java)
            val call: Call<SearchResponse?> = openLibraryApi.searchBooks(query)
            call.enqueue(object : Callback<SearchResponse?> {
                override fun onResponse(
                    call: Call<SearchResponse?>,
                    response: Response<SearchResponse?>
                ) {
                    if (response.isSuccessful) {
                        progressBar.visibility = View.INVISIBLE
                        books =
                            if (response.body() != null) response.body()!!.getBooks() else null
                        bookAdapter.setBooks(books)
                    } else {
                        Toast.makeText(
                            context, "API Unsuccessful",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onFailure(call: Call<SearchResponse?>, t: Throwable) {
                    Toast.makeText(
                        context, "API failed",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
        }

    }


    inner class FetchData(private val bookModel: BookModel) {

        fun getCoverImage(): String {

            val adder: String
            val coverImageUrl: String
            lateinit var tempList: List<String>
            if (bookModel.coverEditionKey != null) {
                coverImageUrl =
                    "http://covers.openlibrary.org/b/olid/${bookModel.coverEditionKey}-M.jpg"
            } else if (bookModel.editionKey != null) {
                tempList = bookModel.editionKey
                adder = tempList[0]
                coverImageUrl = "http://covers.openlibrary.org/b/olid/$adder-M.jpg"
            } else if (bookModel.isbn != null) {
                tempList = bookModel.isbn
                adder = tempList[0]
                coverImageUrl = "http://covers.openlibrary.org/b/isbn/$adder-M.jpg"
            } else if (bookModel.idGoodreads != null) {
                tempList = bookModel.idGoodreads
                adder = tempList[0]
                coverImageUrl = "http://covers.openlibrary.org/b/isbn/$adder-M.jpg"
            } else if (bookModel.idLibrarything != null) {
                tempList = bookModel.idLibrarything
                adder = tempList[0]
                coverImageUrl = "http://covers.openlibrary.org/b/isbn/$adder-M.jpg"
            } else {
                coverImageUrl = "R.drawable.error"
            }
            return coverImageUrl


        }

    }




}