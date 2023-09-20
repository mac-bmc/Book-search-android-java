package com.example.book_search_kotlin.controller

import com.example.book_search_kotlin.model.BookModel


class SearchResponse {
    private val docs: List<BookModel>? = null
    fun getBooks(): List<BookModel>? {
        return docs
    }
}