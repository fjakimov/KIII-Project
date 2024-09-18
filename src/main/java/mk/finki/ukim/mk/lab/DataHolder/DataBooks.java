//package mk.finki.ukim.mk.lab.DataHolder;
//
//import jakarta.annotation.PostConstruct;
//import lombok.RequiredArgsConstructor;
//import mk.finki.ukim.mk.lab.model.Book;
//import mk.finki.ukim.mk.lab.model.BookStore;
//import mk.finki.ukim.mk.lab.service.BookStoreService;
//import org.springframework.stereotype.Component;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Random;
//import java.util.stream.IntStream;
//
//@Component
//@RequiredArgsConstructor
//public class DataBooks {
//
//    public static List<Book> books = new ArrayList<>(5);
//    private final Random random = new Random();
//    private final String[] bookTitles = {"In Search of Lost Time", "Ulysses", "Hamlet", "The Odyssey", "The Divine Comedy"};
//    private final String[] bookGenres = {"Thriller", "Horror", "Historical", "Romance", "Western"};
//    private final BookStoreService bookStoreService;
//
//    @PostConstruct
//    public void init() {
//        List<BookStore> bookStores = bookStoreService.findAll();
//
//        if (bookStores.size() < 5) {
//            throw new IllegalStateException("Not enough BookStores available");
//        }
//
//        IntStream.range(0, Math.min(5, bookTitles.length)).forEach(book -> {
//            String title = bookTitles[book];
//            String genre = bookGenres[book];
//            BookStore store = bookStores.get(book);
//
//            books.add(new Book(title, generateRandomISBN(), genre, random.nextInt(1000, 2000), new ArrayList<>(), store));
//        });
//    }
//
//
//    public static String generateRandomISBN() {
//        Random random = new Random();
//        int prefix = 978 + random.nextInt(2);
//        String mainPart = String.format("%09d", random.nextInt(1000000000));
//        String isbn12 = String.valueOf(prefix) + mainPart;
//        int[] digits = new int[isbn12.length()];
//        for (int i = 0; i < isbn12.length(); i++) {
//            digits[i] = Character.getNumericValue(isbn12.charAt(i));
//        }
//        int sum = 0;
//        for (int i = 0; i < 12; i++) {
//            sum += (i % 2 == 0) ? digits[i] : 3 * digits[i];
//        }
//        int checkDigit = (10 - (sum % 10)) % 10;
//        return isbn12 + checkDigit;
//    }
//}
