package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.DataHolder.DataBookStores;
import mk.finki.ukim.mk.lab.model.BookStore;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryBookStoreRepository {
    public List<BookStore> findAll(){
        return DataBookStores.bookStoreList;
    }
    public Optional<BookStore> findById(Long id){
        return DataBookStores.bookStoreList.stream().filter(bookStore -> bookStore.getId().equals(id)).findFirst();
    }
}
