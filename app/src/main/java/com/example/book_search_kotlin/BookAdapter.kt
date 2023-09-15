package com.example.book_search_kotlin

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class BookAdapter(private val context: Context) :  RecyclerView.Adapter<BookAdapter.ViewHolder>(){


    private var books: List<BookModel>?=null

    public fun setBooks(books : List<BookModel>?)
    {
        this.books=books
    }

    public override fun onCreateViewHolder (parent: ViewGroup, viewType: Int): ViewHolder
    {
        val context: Context = parent.context
        val inflater = LayoutInflater.from(context)
        val bookView : View = inflater.inflate(R.layout.recyclerview,parent,false)
        return ViewHolder(bookView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val bookModel : BookModel? = books?.get(position)
        holder.titleTextView.setText(bookModel?.getTitle())
        holder.authorTextView.setText(bookModel?.getAuthor())

        Glide.with(holder.coverImageView.context)
            .load(bookModel?.getCoverImage())
            .error(R.drawable.error_image)
            .placeholder(R.drawable.placeholder_image)
            .into(holder.coverImageView)
    }

    override fun getItemCount(): Int {
        return if (books != null) books!!.size else 0
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var titleTextView: TextView
        var authorTextView: TextView
        var coverImageView: ImageView

        init {
            titleTextView = itemView.findViewById(R.id.titleTextView)
            authorTextView = itemView.findViewById(R.id.authorTextView)
            coverImageView = itemView.findViewById(R.id.bookCoverImageView)

            itemView.setOnClickListener(){
                val position : Int = absoluteAdapterPosition

            }
        }

    }


}






