package com.example.book_search;
import java.util.List;
public class Book {
    private String title;
    //https://openlibrary.org/api/books?bibkeys=ISBN:1604131454&jscmd=details&format=json
    private String number_of_pages_median;
    private List<String> publisher;
    private String author, adder,publishername;
    private List<String> author_name,id_goodreads,id_librarything;
    private List<String> edition_key;
    private List<String> isbn;
    private String cover_edition_key;


    public String getNumber_of_pages_median() {
        if(number_of_pages_median==null)
        {return "NA";}
        return number_of_pages_median;
    }

    public String getPublisher() {
        if(publisher==null)
        {
            return "NA";
        }
        else {
            publishername=publisher.get(0);

            return publishername;
        }
    }

    private String first_publish_year;
    private String coverImageUrl;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor_name() {
        int i;
        if(author_name==null)
        {
            return null;
        }
        else {
        for(i=0;i<author_name.size();i++)
        {
            author=author_name.get(i);
        }
        return author;
    }}


    public String getFirst_publish_year() {
        return first_publish_year;
    }

    public void setFirst_publish_year(String first_publish_year) {
        this.first_publish_year = first_publish_year;
    }

    public String getCoverImageUrl() {

        if(cover_edition_key!=null){
        coverImageUrl="http://covers.openlibrary.org/b/olid/"+cover_edition_key+"-M.jpg";
        } else if (edition_key!=null)
        {
            adder = edition_key.get(0);
            coverImageUrl="http://covers.openlibrary.org/b/olid/"+ adder +"-M.jpg";
        }
        else if (isbn!=null)
        {
            adder = isbn.get(0);
            coverImageUrl="http://covers.openlibrary.org/b/isbn/"+ adder +"-M.jpg";

        }
        else if (id_goodreads!=null)
        {
            adder = id_goodreads.get(0);
            coverImageUrl="http://covers.openlibrary.org/b/isbn/"+ adder +"-M.jpg";

        }
        else if (id_librarything!=null)
        {
            adder = id_librarything.get(0);
            coverImageUrl="http://covers.openlibrary.org/b/isbn/"+ adder +"-M.jpg";

        }
        else {
            coverImageUrl="R.drawable.error";
        }
        return coverImageUrl;

    }

    public void setCoverImageUrl(String coverImageUrl) {
        this.coverImageUrl = coverImageUrl;
    }
}
