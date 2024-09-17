package mk.finki.ukim.mk.lab.web.servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import mk.finki.ukim.mk.lab.model.Author;
import mk.finki.ukim.mk.lab.model.Book;
import mk.finki.ukim.mk.lab.service.AuthorService;
import mk.finki.ukim.mk.lab.service.BookService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.util.Optional;

@WebServlet(name = "AuthorServlet", value = "/author")
public class AuthorServlet extends HttpServlet {
    private final SpringTemplateEngine springTemplateEngine;
    private final AuthorService authorService;
    private final BookService bookService;

    public AuthorServlet(SpringTemplateEngine springTemplateEngine, AuthorService authorService, BookService bookService) {
        this.springTemplateEngine = springTemplateEngine;
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        IWebExchange iWebExchange = JakartaServletWebApplication.buildApplication(getServletContext()).buildExchange(request, response);
        WebContext context = new WebContext(iWebExchange);
        context.setVariable("authors", authorService.listAuthors());
        String isbn = request.getParameter("isbn");
        context.setVariable("bookIsbn", isbn);
        springTemplateEngine.process("authorList.html", context, response.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long id = Long.parseLong(request.getParameter("authorId"));
        String isbn = request.getParameter("isbn");
//            book.getAuthors().removeIf(author -> author.getId().equals(Long.parseLxong(id)));
        bookService.addAuthorToBook(id, isbn);
        response.sendRedirect("/bookDetails?isbn=" + isbn + "&id=" + id);
    }
}
