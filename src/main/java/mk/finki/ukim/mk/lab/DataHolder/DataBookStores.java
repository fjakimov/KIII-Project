package mk.finki.ukim.mk.lab.DataHolder;

import jakarta.annotation.PostConstruct;
import mk.finki.ukim.mk.lab.model.BookStore;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Component
public class DataBookStores {

    public static List<BookStore> bookStoreList = new ArrayList<>();
    public String[] names = {"Literatura", "Knizarnica-Akademska kniga","Kultura","House of books","I love books"};
    public String[] city = {"Skopje", "Skopje", "Skopje", "Skopje", "Skopje"};
    public String[] addresses = {"Булевар Партизански Одреди 1000", " Porta Bunjakovec, Булевар Партизански Одреди 23, Скопје 1000","Даме Груев 1000, Скопје 1000","Методија Шаторов- Шарло 1, Скопје 1000","Булевар Јане Сандански, Скопје 1000"};
    @PostConstruct
    public void init(){
        IntStream.range(0, 5).forEach(index-> bookStoreList.add(new BookStore(names[index], city[index],addresses[index])));
    }
}
