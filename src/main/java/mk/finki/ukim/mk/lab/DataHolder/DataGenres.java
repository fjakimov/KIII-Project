package mk.finki.ukim.mk.lab.DataHolder;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Component
public class DataGenres {
    public static List<String> genres = new ArrayList<>();
    String[] g = {"Thriller", "Horror", "Historical", "Romance", "Western"};
    @PostConstruct
    public void init(){
        IntStream.range(0, 5).forEach(index->genres.add(g[index]));
    }
}
