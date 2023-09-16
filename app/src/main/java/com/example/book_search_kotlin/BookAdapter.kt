package com.example.book_search_kotlin

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class BookAdapter(private val context: Context) :  RecyclerView.Adapter<BookAdapter.ViewHolder>(){


    private var books: List<BookModel>?=null
    public var bookModel : BookModel? = null

    public fun setBooks(books : List<BookModel>?)
    {
        this.books=books
        notifyDataSetChanged()

    }

    public override fun onCreateViewHolder (parent: ViewGroup, viewType: Int): ViewHolder
    {
        val context: Context = parent.context
        val inflater = LayoutInflater.from(context)
        val bookView : View = inflater.inflate(R.layout.recyclerview,parent,false)
        return ViewHolder(bookView,books)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        bookModel = books?.get(position)
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


   inner class ViewHolder(itemView: View,books:List<BookModel>?) : RecyclerView.ViewHolder(itemView) {
        var titleTextView: TextView= itemView.findViewById(R.id.titleTextView)
        var authorTextView: TextView=itemView.findViewById(R.id.authorTextView)
        var coverImageView: ImageView=itemView.findViewById(R.id.bookCoverImageView)
        lateinit var clickedItem: BookModel

        init {
            itemView.setOnClickListener(){
                val position : Int = absoluteAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    clickedItem  =books!!.get(position)
                }
                //Log.d("books","Item ${books!!.get(position).getTitle()}")
                Log.d("ClickedItem","Item ${clickedItem.getTitle()} clicked")

            }
        }

    }


}






