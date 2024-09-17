package mk.finki.ukim.mk.lab.service.Impl;
import jakarta.transaction.Transactional;
import mk.finki.ukim.mk.lab.exceptions.BookStoreNotFound;
import mk.finki.ukim.mk.lab.model.Author;
import mk.finki.ukim.mk.lab.model.Book;
import mk.finki.ukim.mk.lab.model.BookStore;
import mk.finki.ukim.mk.lab.repository.jpa.AuthorRepository;
import mk.finki.ukim.mk.lab.repository.jpa.BookRepository;
import mk.finki.ukim.mk.lab.repository.jpa.BookStoreRepository;
import mk.finki.ukim.mk.lab.service.BookService;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BookServiceImplementation implements BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final BookStoreRepository bookStoreRepository;

    public BookServiceImplementation(BookRepository bookRepository,AuthorRepository authorRepository,BookStoreRepository bookStoreRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.bookStoreRepository = bookStoreRepository;
    }

    @Override
    public List<Book> listBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Author addAuthorToBook(Long authorId, String isbn) {
        Author author = authorRepository.findById(authorId).get();
        Book book = bookRepository.findByIsbn(isbn);
        if(!book.hasAuthor(author)){
            book.addAuthor(author);
            bookRepository.save(book);
        }
        return author;
    }

    @Override
    public Book findBookByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn);
    }

//    @Override
//    public List<String> listGenres() {
//        return bookRepository.searchAllByGenre();
//    }

    @Override
    public List<Book> findBooksByGenres(String genre) {
        return bookRepository.findBookByGenre(genre);
    }

    @Override
    public void deleteBookById(Long id) {
        Book book = bookRepository.findById(id).orElse(null);
        if (book != null) {
            for (Author author : book.getAuthors()) {
                author.setBook(null); // Remove the association
                authorRepository.save(author); // Update the author
            }
            bookRepository.deleteById(id); // Delete the book
        }
    }

    @Override
    public Optional<Book> save(String title, String isbn, String genre, Integer year, Long bookStoreId) {
        BookStore bookStore = bookStoreRepository.findById(bookStoreId).orElseThrow(()->new BookStoreNotFound(bookStoreId));
        bookRepository.deleteByIsbn(isbn);
        return Optional.of(bookRepository.save(new Book(title, isbn, genre, year,new ArrayList<>(),bookStore)));
    }

    @Override
    public Optional<Book> update(Long id, String title, String isbn, String genre, Integer year, Long bookStoreId) {
        BookStore bookStore = bookStoreRepository.findById(bookStoreId).orElseThrow(()->new BookStoreNotFound(bookStoreId));
        Book book = bookRepository.findById(id).get();
        book.setId(id);
        book.setTitle(title);
        book.setIsbn(isbn);
        book.setYear(year);
        book.setGenre(genre);
        book.setBookStore(bookStore);
        return Optional.of(book);
    }

    @Override
    public Optional<Book> findBookById(Long bookId) {
        return bookRepository.findById(bookId);
    }
}
