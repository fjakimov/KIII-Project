package mk.finki.ukim.mk.lab.model;
import jakarta.persistence.*;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Data
@Entity
public class BookStore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String city;
    private String address;
    @OneToMany(mappedBy = "bookStore")
    private List<Book> books;

    public BookStore() {
    }

    public BookStore(String name, String city, String address) {
        this.name = name;
        this.city = city;
        this.address = address;
    }
}
