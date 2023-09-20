package com.example.book_search_kotlin.controller

import android.content.Context
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import com.example.book_search_kotlin.model.BookModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient(private val context: Context) {

    private val baseUrl: String = "https://openlibrary.org/"
    private lateinit var openLibraryApi: ApiInterface


    fun performBookSearch(query: String, bookAdapter: BookAdapter, progressBar: ProgressBar) {
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
                    val books: List<BookModel>? =
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