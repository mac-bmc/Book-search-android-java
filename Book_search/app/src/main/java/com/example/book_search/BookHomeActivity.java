package com.example.book_search;

import android.os.Bundle;

import androidx.annotation.NonNull;
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
    List<Book> books;
    private BookAdapter bookAdapter;
    private ApiInterface openLibraryApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_home_activity);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        bookAdapter = new BookAdapter(this);
        recyclerView.setAdapter(bookAdapter);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        openLibraryApi = retrofit.create(ApiInterface.class);
        ImageButton search =findViewById(R.id.search);
        TextView searchResult =findViewById(R.id.searchres);
        SearchView searchView = findViewById(R.id.searchView);
        search.setOnClickListener(view -> searchView.setVisibility(View.VISIBLE));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchResult.setText(query);
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
            public void onResponse(@NonNull Call<SearchResponse> call, @NonNull Response<SearchResponse> response) {
                if (response.isSuccessful()) {
                    books = response.body() != null ? response.body().getBooks() : null;
                    bookAdapter.setBooks(books);
                } else {
                    Toast.makeText(BookHomeActivity.this, "API failed",
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<SearchResponse> call, @NonNull Throwable t) {
                Toast.makeText(BookHomeActivity.this, "API failed",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

}
