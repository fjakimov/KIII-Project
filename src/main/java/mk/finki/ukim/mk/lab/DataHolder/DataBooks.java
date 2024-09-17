package mk.finki.ukim.mk.lab.DataHolder;

import jakarta.annotation.PostConstruct;
import mk.finki.ukim.mk.lab.model.Author;
import mk.finki.ukim.mk.lab.model.Book;
import mk.finki.ukim.mk.lab.model.BookStore;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

@Component
public class DataBooks {

    public static List<Book> books = new ArrayList<>(5);
    Random random = new Random();
    String[]bookTitles = {"In Search of Lost Time", " Ulysses", "Hamlet", "The Odyssey", "The Divine Comedy"};
    String[] bookGenres = {"Thriller", "Horror", "Historical", "Romance", "Western"};
    private final DataBookStores dataBookStores;

    public DataBooks(DataBookStores dataBookStores) {
        this.dataBookStores = dataBookStores;
    }

    @PostConstruct
    public void init(){
        IntStream.range(0, 5).forEach(book->books.add(new Book(bookTitles[book], generateRandomISBN(), bookGenres[book], random.nextInt(1000, 2000), new ArrayList<>(), DataBookStores.bookStoreList.get(book))));
    }
    public static String generateRandomISBN() {
        Random random = new Random();
        // The first three digits are the EAN-13 prefix, which is typically 978 or 979 for book publishing.
        int prefix = 978 + random.nextInt(2);
        // The next 9 digits are random.
        String mainPart = String.format("%09d", random.nextInt(1000000000));
        // The last digit is the check digit, calculated based on the first 12 digits.
        String isbn12 = String.valueOf(prefix) + mainPart;
        int[] digits = new int[isbn12.length()];
        for (int i = 0; i < isbn12.length(); i++) {
            digits[i] = Character.getNumericValue(isbn12.charAt(i));
        }
        int sum = 0;
        for (int i = 0; i < 12; i++) {
            sum += (i % 2 == 0) ? digits[i] : 3 * digits[i];
        }
        int checkDigit = (10 - (sum % 10)) % 10;
        return isbn12 + checkDigit;
    }

}
