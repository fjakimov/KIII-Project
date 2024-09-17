package mk.finki.ukim.mk.lab.DataHolder;

import jakarta.annotation.PostConstruct;
import mk.finki.ukim.mk.lab.model.Author;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Component
public class DataAuthors {
    public static List<Author> authorList = new ArrayList<>(5);
    String[] names = {"William", "Charles", "Agatha", "Fyodor", "William"};
    String[] surnames = {"Shakespeare", "Dickens", "Christie", "Dostoevsky", "Faulkner"};
    String[] bio = {"William Shakespeare was an English playwright, poet, and actor who is widely regarded as the greatest writer in the English language and the world’s greatest dramatist.",
            "Charles John Huffam Dickens was an English writer and social critic who created some of the world’s best-known fictional characters and is regarded by many as the greatest novelist of the Victorian era.",
            "Dame Agatha Mary Clarissa Christie, Lady Mallowan, DBE was an English writer known for her 66 detective novels and 14 short story collections, particularly those revolving around fictional detectives Hercule Poirot and Miss Marple.",
            "Fyodor Mikhailovich Dostoevsky was a Russian novelist, short story writer, essayist, and journalist. His literary works explore the human condition in the troubled political, social, and spiritual atmospheres of 19th-century Russia, and engage with a variety of philosophical and religious themes.",
            "William Cuthbert Faulkner was an American writer known for his novels and short stories set in the fictional Yoknapatawpha County, based in Lafayette County, Mississippi."
    };
//    @PostConstruct
//    public void init(){
//        IntStream.range(0, 5).forEach(index->authorList.add(new Author((long) index, names[index],surnames[index],bio[index])));
//    }
}
