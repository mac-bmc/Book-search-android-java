package com.example.book_search_kotlin

import com.google.gson.annotations.SerializedName

class BookModel {
    private val title: String? = null

    @SerializedName("number_of_pages_median")
    private val pageCountMedian: String? = null

    private val publisher: List<String>? = null
    private val author: String? = null

    @SerializedName("author_name")
    private val authorName: List<String>? = null

    @SerializedName("id_goodreads")
    private val idGoodreads: List<String>? = null

    @SerializedName("id_librarything")
    private val idLibrarything: List<String>? = null

    @SerializedName("edition_key")
    private val editionKey: List<String>? = null
    private val isbn: List<String>? = null

    @SerializedName("cover_edition_key")
    private val coverEditionKey: String? = null


    fun getTitle(): String? {
        return title
    }

    fun getPageNumber(): String? {
        return pageCountMedian
    }

    fun getPublisher(): String? {
        return publisher?.get(0) ?: "NA"
    }

    fun getAuthor(): String? {
        return authorName?.get(0) ?: "NA"
    }

    fun getCoverImage(): String? {

        val adder: String
        val coverImageUrl: String
        if (coverEditionKey != null) {
            coverImageUrl = "http://covers.openlibrary.org/b/olid/$coverEditionKey-M.jpg"
        } else if (editionKey != null) {
            adder = editionKey[0]
            coverImageUrl = "http://covers.openlibrary.org/b/olid/$adder-M.jpg"
        } else if (isbn != null) {
            adder = isbn[0]
            coverImageUrl = "http://covers.openlibrary.org/b/isbn/$adder-M.jpg"
        } else if (idGoodreads != null) {
            adder = idGoodreads[0]
            coverImageUrl = "http://covers.openlibrary.org/b/isbn/$adder-M.jpg"
        } else if (idLibrarything != null) {
            adder = idLibrarything[0]
            coverImageUrl = "http://covers.openlibrary.org/b/isbn/$adder-M.jpg"
        } else {
            coverImageUrl = "R.drawable.error"
        }
        return coverImageUrl


    }


}