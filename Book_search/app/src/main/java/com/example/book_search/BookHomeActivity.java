package com.example.book_search;

import android.content.Context;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import androidx.appcompat.widget.SearchView;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class BookHomeActivity extends AppCompatActivity {

    private static final String BASE_URL = "https://openlibrary.org/";

    private RecyclerView recyclerView;
    Context context;
    List<Book> books;
    private BookAdapter bookAdapter;
    private OpenLibraryApi openLibraryApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_home_activity);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        bookAdapter = new BookAdapter(this);
        recyclerView.setAdapter(bookAdapter);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        openLibraryApi = retrofit.create(OpenLibraryApi.class);
        ImageButton search=(ImageButton) findViewById(R.id.search);
        TextView searchres=(TextView) findViewById(R.id.searchres);
        SearchView searchView = (SearchView) findViewById(R.id.searchView);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchView.setVisibility(View.VISIBLE);
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchres.setText(query);
                performBookSearch(query);

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                return false;
            }
        });
    }

    private void performBookSearch(String query) {
        Call<SearchResponse> call = openLibraryApi.searchBooks(query);
        call.enqueue(new Callback<SearchResponse>() {
            @Override
            public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
                if (response.isSuccessful()) {
                    books = response.body().getBooks();
                    bookAdapter.setBooks(books);
                } else {
                    // Handle API errors here
                }
            }

            @Override
            public void onFailure(Call<SearchResponse> call, Throwable t) {
                // Handle network or unexpected errors here
            }
        });
    }
    public void onclick(Book book)
    {
        Toast.makeText(BookHomeActivity.this,"item clicked",
                Toast.LENGTH_SHORT).show();

    }
}
