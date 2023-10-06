package com.example.book_search_kotlin.view

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.ProgressBar
import androidx.appcompat.widget.SearchView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.book_search_kotlin.BookAdapter
import com.example.book_search_kotlin.R
import com.example.book_search_kotlin.viewmodel.BookViewModel

class BookSearchActivity : AppCompatActivity(R.layout.activity_book_search) {
    private lateinit var bookViewModel: BookViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        bookViewModel = ViewModelProvider(
            this,).get(BookViewModel::class.java)
        val bookAdapter = BookAdapter(bookViewModel)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = bookAdapter

        val searchView: SearchView = findViewById(R.id.searchView)
        val search: ImageButton = findViewById(R.id.search)
        val searchRes: TextView = findViewById(R.id.searchRes)
        val progressBar: ProgressBar = findViewById(R.id.progressSearch)


        search.setOnClickListener {
            searchView.visibility = View.VISIBLE

        }
        searchView.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                searchRes.text = query
                progressBar.visibility = View.VISIBLE
                val apiClient = bookViewModel.ApiClient(this@BookSearchActivity)
                apiClient.performBookSearch(query, bookAdapter, progressBar)
                Log.d("After book search", query)

                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })


    }
}