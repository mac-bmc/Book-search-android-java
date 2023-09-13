package com.example.book_search;

import com.google.gson.annotations.SerializedName;

import java.util.List;

@SuppressWarnings("unused")

public class Book {
    private String title;
    @SerializedName("number_of_pages_median")
    private String pageCountMedian;

    private List<String> publisher;
    private String author;
    @SerializedName("author_name")
    private List<String> authorName;
    @SerializedName("id_goodreads")
    private List<String> idGoodreads;
    @SerializedName("id_librarything")
    private List<String> idLibrarything;
    @SerializedName("edition_key")
    private List<String> editionKey;
    private List<String> isbn;
    @SerializedName("cover_edition_key")
    private String coverEditionKey;

    public Book() {
    }


    public String getPageCountMedian() {
        if (pageCountMedian == null) {
            return "NA";
        }
        return pageCountMedian;
    }

    public String getPublisher() {
        if (publisher == null) {
            return "NA";
        } else {

            return publisher.get(0);
        }
    }

    public String getTitle() {
        return title;
    }


    public String getAuthorName() {
        int i;
        if (authorName == null) {
            return null;
        } else {
            for (i = 0; i < authorName.size(); i++) {
                author = authorName.get(i);
            }
            return author;
        }
    }


    public String getCoverImageUrl() {

        String adder;
        String coverImageUrl;
        if (coverEditionKey != null) {
            coverImageUrl = "http://covers.openlibrary.org/b/olid/" + coverEditionKey + "-M.jpg";
        } else if (editionKey != null) {
            adder = editionKey.get(0);
            coverImageUrl = "http://covers.openlibrary.org/b/olid/" + adder + "-M.jpg";
        } else if (isbn != null) {
            adder = isbn.get(0);
            coverImageUrl = "http://covers.openlibrary.org/b/isbn/" + adder + "-M.jpg";

        } else if (idGoodreads != null) {
            adder = idGoodreads.get(0);
            coverImageUrl = "http://covers.openlibrary.org/b/isbn/" + adder + "-M.jpg";

        } else if (idLibrarything != null) {
            adder = idLibrarything.get(0);
            coverImageUrl = "http://covers.openlibrary.org/b/isbn/" + adder + "-M.jpg";

        } else {
            coverImageUrl = "R.drawable.error";
        }
        return coverImageUrl;

    }


}
