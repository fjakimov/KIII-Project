//package mk.finki.ukim.mk.lab.repository;
//import mk.finki.ukim.mk.lab.DataHolder.DataBooks;
//import mk.finki.ukim.mk.lab.DataHolder.DataGenres;
//import mk.finki.ukim.mk.lab.model.Author;
//import mk.finki.ukim.mk.lab.model.Book;
//import mk.finki.ukim.mk.lab.model.BookStore;
//import org.springframework.stereotype.Repository;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//@Repository
//public class InMemoryBookRepository {
//    public List<Book> findAll(){
//        return DataBooks.books;
//    }
//    public Book findByIsbn(String isbn){
//        return DataBooks.books
//                .stream().filter(book -> book.getIsbn()
//                        .equals(isbn)).findFirst().orElseThrow();
//    }
//    public Author addAuthorToBook(Author author, Book book){
//        book.addAuthor(author);
//        return author;
//    }
//    public List<String> listGenres(){
//        return DataGenres.genres;
//    }
//    public List<Book> findBookByGenre(String genre){
//        return DataBooks.books.stream().filter(book -> book.getGenre().equals(genre)).collect(Collectors.toList());
//    }
//    public Optional<Book> save(String title, String isbn, String genre, Integer year, BookStore bookStore){
//        List<Author> authorList = new ArrayList<Author>();
//        Book book = new Book(title, isbn, genre, year, authorList, bookStore);
//        DataBooks.books.add(book);
//        return Optional.of(book);
//    }
//
//    public Optional<Book> update(Long id, String title, String isbn, String genre, Integer year, BookStore bookStore){
//        Book b = findById(id);
//        if(b != null){
//            b.setTitle(title);
//            b.setIsbn(isbn);
//            b.setGenre(genre);
//            b.setYear(year);
//            b.setBookStore(bookStore);
//            return Optional.of(b);
//        }
//        return Optional.empty();
//    }
//
//    public Book findById(Long bookId){
//        return DataBooks.books
//                .stream().filter(book -> book.getId()
//                        .equals(bookId)).findFirst().orElse(null);
//    }
//    public void deleteBookById(Long id){
//        DataBooks.books.removeIf(book -> book.getId().equals(id));
//    }
//}
