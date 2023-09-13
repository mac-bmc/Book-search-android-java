package com.example.book_search;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;


public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder> {

    private List<Book> books;
    private final Context context;
    Book clickedItem;


    public BookAdapter(Context context) {
        this.context = context;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setBooks(List<Book> books) {
        this.books = books;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View bookView = inflater.inflate(R.layout.recycler_view, parent, false);
        return new ViewHolder(bookView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Book book = books.get(position);
        holder.titleTextView.setText(book.getTitle());
        holder.authorTextView.setText(book.getAuthorName());

        // Load book cover image using Glide
        Glide.with(holder.coverImageView.getContext())
                .load(book.getCoverImageUrl())
                .placeholder(R.drawable.placeholder_image)
                .error(R.drawable.error)
                .into(holder.coverImageView);
    }

    @Override
    public int getItemCount() {
        return books != null ? books.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView titleTextView;

        public TextView authorTextView;
        public ImageView coverImageView;

        public ViewHolder(View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            authorTextView = itemView.findViewById(R.id.authorTextView);
            coverImageView = itemView.findViewById(R.id.bookCoverImageView);
            itemView.setOnClickListener(view -> {
                int position = getAbsoluteAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    clickedItem = books.get(position);

                }
                Intent intent = new Intent(context, Book_details.class);
                intent.putExtra("title", clickedItem.getTitle());
                intent.putExtra("author", clickedItem.getAuthorName());
                intent.putExtra("imageUrl", clickedItem.getCoverImageUrl());
                intent.putExtra("publisher", clickedItem.getPublisher());
                intent.putExtra("pageNo", clickedItem.getPageCountMedian());
                context.startActivity(intent);

            });

        }
    }
}


