package mk.finki.ukim.mk.lab.web.cotroller;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import mk.finki.ukim.mk.lab.service.AuthorService;
import mk.finki.ukim.mk.lab.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class AuthorController {
    private final AuthorService authorService;
    private final BookService bookService;

    @PostMapping("/authors")
    public String showAuthors(HttpServletRequest request, Model model){
        model.addAttribute("authors", authorService.listAuthors());
        model.addAttribute("bookIsbn",request.getParameter("bookIsbn"));
        return "authorList";
    }

    @PostMapping("/book/details")
    public String getDetails(@RequestParam String bookIsbn, @RequestParam Long authorId, Model model){
        bookService.addAuthorToBook(authorId, bookIsbn);
        model.addAttribute("book", bookService.findBookByIsbn(bookIsbn));
        return "bookDetails";
    }
}
