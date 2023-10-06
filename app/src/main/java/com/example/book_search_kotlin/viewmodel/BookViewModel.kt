package com.example.book_search_kotlin.viewmodel

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Environment
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.content.FileProvider
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.book_search_kotlin.controller.ApiInterface
import com.example.book_search_kotlin.controller.BookAdapter
import com.example.book_search_kotlin.controller.SearchResponse
import com.example.book_search_kotlin.model.BookModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.util.Objects

class BookViewModel : ViewModel() {

    inner class ApiClient(private val context: Context) {

        private val baseUrl: String = "https://openlibrary.org/"
        private lateinit var openLibraryApi: ApiInterface


        fun performBookSearch(query: String, bookAdapter: BookAdapter, progressBar: ProgressBar) {
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

    inner class ImageSharing(private val context: Context, private val imageView: ImageView) {
        fun getBitmapUrl(title: String) {
            val drawable = imageView.drawable
            var bitmap: Bitmap? = null
            var bitmapUri: Uri? = null
            if (drawable is BitmapDrawable) {
                bitmap = (imageView.drawable as BitmapDrawable).bitmap
            } else {
                Log.d("bitmap", "failed")
            }

            try {
                val file = File(
                    Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
                    "share_image$title.png"
                )
                Objects.requireNonNull(file.parentFile).mkdirs()
                val out = FileOutputStream(file)
                bitmap?.compress(Bitmap.CompressFormat.PNG, 90, out)
                out.close()
                bitmapUri = FileProvider
                    .getUriForFile(
                        context, context.applicationContext
                            .packageName + ".provider", file
                    )
            } catch (e: IOException) {
                e.printStackTrace()
            }
            sharingFunction(bitmapUri, title)


        }

        private fun sharingFunction(bitmapUri: Uri?, bookTitle: String) {
            if (bitmapUri != null) {
                val shareIntent = Intent()
                shareIntent.action = Intent.ACTION_SEND
                shareIntent.type = "text/plain"
                shareIntent.putExtra(
                    Intent.EXTRA_TEXT,
                    "Check out this book: $bookTitle"
                )
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, bookTitle)
                shareIntent.putExtra(Intent.EXTRA_STREAM, bitmapUri)
                shareIntent.type = "image/*"

                context.startActivity(Intent.createChooser(shareIntent, "Share Image"))
            } else {
                Log.d("bitmap uri", "failed")
            }
        }


    }

    inner class Login {
        fun loginAuth(username: String, password: String): Boolean {
            var key = false
            if (username.isNotEmpty() && password.isNotEmpty()) {
                key = true
            }
            return key
        }

    }

}