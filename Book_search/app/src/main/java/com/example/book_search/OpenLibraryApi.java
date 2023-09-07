package com.example.book_search;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OpenLibraryApi {
    @GET("search.json")
    Call<SearchResponse> searchBooks(@Query("q") String query);
}
