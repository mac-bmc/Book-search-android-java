package com.example.book_search_kotlin.model

import com.google.gson.annotations.SerializedName

data class BookModel(
    val title: String? = null,

    @SerializedName("number_of_pages_median")
    val pageCountMedian: String? = null,

    val publisher: List<String>? = null,

    @SerializedName("author_name")
    val authorName: List<String>? = null,

    @SerializedName("id_goodreads")
    val idGoodreads: List<String>? = null,

    @SerializedName("id_librarything")
    val idLibrarything: List<String>? = null,

    @SerializedName("edition_key")
    val editionKey: List<String>? = null,
    val isbn: List<String>? = null,

    @SerializedName("cover_edition_key")
    val coverEditionKey: String? = null
)