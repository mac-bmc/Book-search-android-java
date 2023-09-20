package com.example.book_search_kotlin

class FetchData(private val bookModel: BookModel) {

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