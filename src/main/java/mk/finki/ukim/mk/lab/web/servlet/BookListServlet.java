package mk.finki.ukim.mk.lab.web.servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import mk.finki.ukim.mk.lab.service.BookService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet(name = "BookListServlet", value = "/listBooks")
public class BookListServlet extends HttpServlet {

    private final SpringTemplateEngine springTemplateEngine;
    private final BookService bookService;

    public BookListServlet(SpringTemplateEngine springTemplateEngine, BookService bookService) {
        this.springTemplateEngine = springTemplateEngine;
        this.bookService = bookService;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        IWebExchange iWebExchange = JakartaServletWebApplication.buildApplication(getServletContext()).buildExchange(request, response);
        WebContext context = new WebContext(iWebExchange);
        context.setVariable("books", bookService.listBooks());
        springTemplateEngine.process("listBooks.html", context, response.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bookIsbn = request.getParameter("bookIsbn");
        response.sendRedirect("/author?isbn=" + bookIsbn);
    }
}
