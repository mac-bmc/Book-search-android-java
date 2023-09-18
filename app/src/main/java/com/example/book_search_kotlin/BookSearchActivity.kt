package com.example.book_search_kotlin

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageButton
import androidx.appcompat.widget.SearchView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class BookSearchActivity : AppCompatActivity() {
    var books : List<BookModel>?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_search)

        val recyclerView : RecyclerView = findViewById(R.id.recyclerView)
        val bookAdapter : BookAdapter = BookAdapter(this)
        recyclerView.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        recyclerView.adapter=bookAdapter

        val searchView : SearchView = findViewById(R.id.searchView)
        val search : ImageButton = findViewById(R.id.search)
        val searchRes : TextView = findViewById(R.id.searchRes)

        search.setOnClickListener(){
            searchView.visibility=View.VISIBLE
        }
        searchView.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                searchRes.text = query
                val apiClient = ApiClient(this@BookSearchActivity)
                apiClient.performBookSearch(query,bookAdapter)
                Log.d("After book search","$query")

                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })





    }
}