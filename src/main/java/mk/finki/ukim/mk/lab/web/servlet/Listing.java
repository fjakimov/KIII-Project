package mk.finki.ukim.mk.lab.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.finki.ukim.mk.lab.model.Book;
import mk.finki.ukim.mk.lab.service.AuthorService;
import mk.finki.ukim.mk.lab.service.BookService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.util.List;

@WebServlet(name="listingMoviesFromGenre", urlPatterns = "/listing")
public class Listing  extends HttpServlet {
    private final SpringTemplateEngine springTemplateEngine;
    private final AuthorService authorService;
    private final BookService bookService;

    public Listing(SpringTemplateEngine springTemplateEngine, AuthorService authorService, BookService bookService) {
        this.springTemplateEngine = springTemplateEngine;
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange iWebExchange = JakartaServletWebApplication.buildApplication(getServletContext()).buildExchange(req, resp);
        WebContext context = new WebContext(iWebExchange);
        String getGenre = req.getParameter("genre");
        List<Book> var = bookService.findBooksByGenres(getGenre);
        context.setVariable("booksByGenre", var);
        springTemplateEngine.process("listBooksByGenres.html", context, resp.getWriter());
    }
}
