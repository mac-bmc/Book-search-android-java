package com.example.book_search_kotlin

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiInterface {
    @GET("search.json")
    fun searchBooks(@Query("q") query: String): Call<SearchResponse?>
}