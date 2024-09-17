package mk.finki.ukim.mk.lab.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    String surname;
    String biography;
    LocalDate dateOfBirth;
    @ManyToOne
    Book book;
    public Author() {
    }

    public Author(String name, String surname, String biography) {
        this.name = name;
        this.surname = surname;
        this.biography = biography;
    }

    public Author(String name, String surname, String biography, LocalDate dateOfBirth,Book book) {
        this.name = name;
        this.surname = surname;
        this.biography = biography;
        this.dateOfBirth = dateOfBirth;
        this.book = book;
    }

}
