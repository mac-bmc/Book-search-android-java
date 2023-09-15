package com.example.book_search_kotlin


class SearchResponse {
    private val docs: List<BookModel>? = null
    fun getBooks(): List<BookModel>? {
        return docs
    }
}