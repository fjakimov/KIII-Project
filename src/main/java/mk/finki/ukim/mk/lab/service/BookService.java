package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.Author;
import mk.finki.ukim.mk.lab.model.Book;
import mk.finki.ukim.mk.lab.model.BookStore;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> listBooks();
    Author addAuthorToBook(Long authorId, String isbn);
    Book findBookByIsbn(String isbn);
//    List<String> listGenres();
    List<Book> findBooksByGenres(String genre);
    void deleteBookById(Long id);
    Optional<Book> save(String title, String isbn, String genre, Integer year, Long bookStore);
    Optional<Book> update(Long id, String title, String isbn, String genre, Integer year, Long bookStore);
    Optional<Book> findBookById(Long bookId);
}

