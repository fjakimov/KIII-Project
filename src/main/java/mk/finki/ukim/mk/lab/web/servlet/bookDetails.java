package mk.finki.ukim.mk.lab.web.servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import mk.finki.ukim.mk.lab.service.AuthorService;
import mk.finki.ukim.mk.lab.service.BookService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet(name = "bookDetails", value = "/bookDetails")
public class bookDetails extends HttpServlet {
    private final SpringTemplateEngine springTemplateEngine;
    private final AuthorService authorService;
    private final BookService bookService;

    public bookDetails(SpringTemplateEngine springTemplateEngine, AuthorService authorService, BookService bookService) {
        this.springTemplateEngine = springTemplateEngine;
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        IWebExchange iWebExchange = JakartaServletWebApplication.buildApplication(getServletContext()).buildExchange(request,response);
        WebContext context = new WebContext(iWebExchange);
        String isbn = request.getParameter("isbn");
        context.setVariable("book", bookService.findBookByIsbn(isbn));
        springTemplateEngine.process("bookDetails.html", context, response.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
