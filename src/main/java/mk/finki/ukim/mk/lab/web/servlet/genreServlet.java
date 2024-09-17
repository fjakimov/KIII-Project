//package mk.finki.ukim.mk.lab.web.servlet;
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import mk.finki.ukim.mk.lab.service.BookService;
//import org.thymeleaf.context.WebContext;
//import org.thymeleaf.spring6.SpringTemplateEngine;
//import org.thymeleaf.web.IWebExchange;
//import org.thymeleaf.web.servlet.JakartaServletWebApplication;
//
//import java.io.IOException;
//
//@WebServlet(name = "bookGenres", urlPatterns = "/bookGenres")
//public class genreServlet extends HttpServlet {
//    private final SpringTemplateEngine springTemplateEngine;
//    private final BookService bookService;
//
//    public genreServlet(SpringTemplateEngine springTemplateEngine, BookService bookService) {
//        this.springTemplateEngine = springTemplateEngine;
//        this.bookService = bookService;
//    }
//
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        IWebExchange iWebExchange = JakartaServletWebApplication.buildApplication(getServletContext()).buildExchange(req, resp);
//        WebContext context = new WebContext(iWebExchange);
//        context.setVariable("genres", bookService.listGenres());
//        springTemplateEngine.process("bookGenres.html", context, resp.getWriter());
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String bookGenre = req.getParameter("genre");
//        resp.sendRedirect("/listing?genre="+bookGenre);
//    }
//}
