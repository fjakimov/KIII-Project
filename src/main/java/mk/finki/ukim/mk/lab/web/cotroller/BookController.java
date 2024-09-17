package mk.finki.ukim.mk.lab.web.cotroller;

import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import mk.finki.ukim.mk.lab.model.Author;
import mk.finki.ukim.mk.lab.model.Book;
import mk.finki.ukim.mk.lab.model.BookStore;
import mk.finki.ukim.mk.lab.model.Review;
import mk.finki.ukim.mk.lab.service.AuthorService;
import mk.finki.ukim.mk.lab.service.BookService;
import mk.finki.ukim.mk.lab.service.BookStoreService;
import mk.finki.ukim.mk.lab.service.ReviewService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@AllArgsConstructor
public class BookController {
    private final BookService bookService;
    private final AuthorService authorService;
    private final BookStoreService bookStoreService;
    private final ReviewService reviewService;

    @GetMapping("/books")
    public String getBooksPage(@RequestParam(required = false) String error, Model model){
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        List<Book> books = bookService.listBooks();
        model.addAttribute("books", books);
        return "listBooks";
    }

    @PostMapping("/books/add")
    public String saveBook(@RequestParam(required = false) Long bookId, @RequestParam String title, @RequestParam String isbn, @RequestParam String genre,@RequestParam int year,@RequestParam Long bookStoreId){
        if(bookId != null){
            this.bookService.update(bookId, title, isbn, genre, year, bookStoreId);
        }
        else{
            this.bookService.save(title, isbn, genre, year, bookStoreId);
        }
        return "redirect:/books";
    }

    @GetMapping("/books/add")
    public String showAddedBook(Model model){
        model.addAttribute("bookStore", bookStoreService.findAll());
        return "add-book";
    }

    @GetMapping("/books/edit/{bookId}")
    public String editBook(@PathVariable Long bookId, Model model){
        if (this.bookService.findBookById(bookId).isEmpty()) {
            return "redirect:/books?error=BookNotFound";
        }
        model.addAttribute("book", bookService.findBookById(bookId));
        model.addAttribute("bookStore", bookStoreService.findAll());
        return "add-book";
    }

    @GetMapping("/books/delete/{id}")
    public String deleteBook(@PathVariable Long id){
        bookService.deleteBookById(id);
        return "redirect:/books";
    }
    @GetMapping("/books/clone/{id}")
    public String cloneBook(@PathVariable Long id){
        Book originalBook = bookService.findBookById(id).orElse(null);

        if (originalBook == null) {
            return "redirect:/books?error=BookNotFound";
        }

        List<Author> originalAuthors = originalBook.getAuthors();
        BookStore bookStore = originalBook.getBookStore();

        List<Author> clonedAuthors = new ArrayList<>();
        for (Author originalAuthor : originalAuthors) {
            Author clonedAuthor = new Author(originalAuthor.getName(), originalAuthor.getSurname(), originalAuthor.getBiography());
            clonedAuthors.add(clonedAuthor);
        }

        Book clonedBook = new Book("Copy of " + originalBook.getTitle(), originalBook.getIsbn(), originalBook.getGenre(),
                originalBook.getYear(), clonedAuthors, bookStore);

        bookService.save(clonedBook.getTitle(), clonedBook.getIsbn(), clonedBook.getGenre(),
                clonedBook.getYear(), bookStore.getId());

        return "redirect:/books";
    }
    @GetMapping("books/review")
    public String showAddReview(@RequestParam Long id, Model model) {
        Book book = bookService.findBookById(id).orElse(null);

        if (book == null) {
            return "redirect:/books?error=BookNotFound";
        }

        model.addAttribute("book", book);

        model.addAttribute("review", new Review());

        return "add-review";
    }
    @PostMapping("/books/review")
    public String saveReview(@RequestParam(required = false) Long bookId, @RequestParam(required = false) Long reviewId, @RequestParam Integer grade, @RequestParam String description, @RequestParam LocalDateTime localDateTime, Model model){
        reviewService.save(bookId, reviewId, grade, description, localDateTime);
        reviewService.addReviewToBook(bookId, reviewId);
        model.addAttribute("reviews", reviewService.listAll());
        return "redirect:bookDetails";
    }
}
