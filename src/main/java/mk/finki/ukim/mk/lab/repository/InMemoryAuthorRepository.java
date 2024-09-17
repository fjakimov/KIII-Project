package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.DataHolder.DataAuthors;
import mk.finki.ukim.mk.lab.model.Author;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryAuthorRepository {
    public List<Author> findAll(){
        return DataAuthors.authorList;
    }
    public Optional<Author> findById(Long id){
        return DataAuthors.authorList.stream().filter(author -> author.getId().equals(id)).findFirst();
    }
}
