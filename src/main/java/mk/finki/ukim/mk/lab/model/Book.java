package mk.finki.ukim.mk.lab.model;

import jakarta.persistence.*;
import lombok.Data;
import mk.finki.ukim.mk.lab.service.ReviewService;

import java.util.ArrayList;
import java.util.List;
@Data
@Entity
public class Book {
    String isbn;
    String title;
    String genre;
    int year;
    @OneToMany(mappedBy = "book", fetch = FetchType.EAGER)
    List<Author> authors;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ManyToOne
    private BookStore bookStore;
    @OneToMany(mappedBy = "book")
    List<Review> reviews;

    public Book() {
    }

    public Book(Long id, String title, String isbn, String genre, Integer year, BookStore bookStore) {
        this.isbn = isbn;
        this.title = title;
        this.genre = genre;
        this.year = year;
        this.id = id;
        this.bookStore = bookStore;
    }

    public Book(String title, String isbn, String genre, int year, List<Author> authors, BookStore bookStore) {
        this.isbn = isbn;
        this.title = title;
        this.genre = genre;
        this.year = year;
        this.authors = authors;
        this.bookStore = bookStore;
        reviews = new ArrayList<>();
    }
    public void addAuthor(Author author){
        authors.add(author);
        author.setBook(this);
    }
    public void addReview(Review review){
        reviews.add(review);
        review.setBook(this);
    }
    public boolean hasAuthor(Author author){
        return authors.contains(author);
    }
}
