package com.example.book_search_kotlin.controller

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.book_search_kotlin.R
import com.example.book_search_kotlin.model.BookModel
import com.example.book_search_kotlin.view.BookDetailsActivity
import com.example.book_search_kotlin.viewmodel.BookViewModel

class BookAdapter(bookViewModel: BookViewModel) : RecyclerView.Adapter<BookAdapter.ViewHolder>() {


    var booksList: List<BookModel>? = null
    private var bookModel: BookModel? = null
    private val viewModel=bookViewModel

    @SuppressLint("NotifyDataSetChanged")
    fun setBooks(books: List<BookModel>?) {
        this.booksList = books
        notifyDataSetChanged()

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context: Context = parent.context
        val inflater = LayoutInflater.from(context)
        val bookView: View = inflater.inflate(R.layout.recyclerview, parent, false)
        return ViewHolder(bookView, context,viewModel)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        bookModel = booksList?.get(position)
        val fetchData = bookModel?.let {viewModel.FetchData(it) }
        holder.titleTextView.text = bookModel?.title
        holder.authorTextView.text = bookModel?.authorName?.get(0)


        Glide.with(holder.coverImageView.context)
            .load(fetchData?.getCoverImage())
            .error(R.drawable.error_image)
            .placeholder(R.drawable.placeholder_image)
            .into(holder.coverImageView)
    }

    override fun getItemCount(): Int {
        return if (booksList != null) booksList!!.size else 0
    }


    inner class ViewHolder(itemView: View, context: Context,viewModel: BookViewModel) : RecyclerView.ViewHolder(itemView) {
        var titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        var authorTextView: TextView = itemView.findViewById(R.id.authorTextView)
        var coverImageView: ImageView = itemView.findViewById(R.id.bookCoverImageView)
        private lateinit var clickedItem: BookModel

        init {
            itemView.setOnClickListener {
                val position: Int = absoluteAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    clickedItem = booksList!![position]
                }
                //Log.d("ClickedItem","Item ${clickedItem.getCoverImage()} clicked")
                val intent = Intent(context, BookDetailsActivity::class.java)

                intent.putExtra("title", clickedItem.title)
                intent.putExtra("author", clickedItem.authorName?.get(0))
                intent.putExtra("imageUrl", viewModel.FetchData(clickedItem).getCoverImage())
                intent.putExtra("publisher", clickedItem.publisher?.get(0))
                intent.putExtra("pageNo", clickedItem.pageCountMedian)
                context.startActivity(intent)

            }
        }

    }


}






