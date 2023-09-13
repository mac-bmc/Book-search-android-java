package com.example.book_search;

import java.util.List;
@SuppressWarnings("unused")
public class SearchResponse {
    private List<Book> doc;

    public List<Book> getBooks() {
        return doc;
    }
}