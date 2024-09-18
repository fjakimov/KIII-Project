package mk.finki.ukim.mk.lab.DataHolder;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import mk.finki.ukim.mk.lab.model.BookStore;
import mk.finki.ukim.mk.lab.repository.jpa.BookStoreRepository;
import org.springframework.stereotype.Component;

import java.util.stream.IntStream;

@Component
@RequiredArgsConstructor
public class DataBookStores {
    private final BookStoreRepository bookStoreRepository;

    private final String[] names = {"Literatura", "Knizarnica-Akademska kniga", "Kultura", "House of books", "I love books"};
    private final String[] cities = {"Skopje", "Skopje", "Skopje", "Skopje", "Skopje"};
    private final String[] addresses = {"Булевар Партизански Одреди 1000", "Porta Bunjakovec, Булевар Партизански Одреди 23, Скопје 1000", "Даме Груев 1000, Скопје 1000", "Методија Шаторов- Шарло 1, Скопје 1000", "Булевар Јане Сандански, Скопје 1000"};

    @PostConstruct
    public void init() {
        if (bookStoreRepository.count() == 0) {  // Only add bookstores if none exist
            IntStream.range(0, 5).forEach(index ->
                    bookStoreRepository.save(new BookStore(names[index], cities[index], addresses[index]))
            );
        }
    }
}
