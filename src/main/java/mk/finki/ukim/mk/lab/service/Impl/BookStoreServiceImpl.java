package mk.finki.ukim.mk.lab.service.Impl;

import lombok.AllArgsConstructor;
import mk.finki.ukim.mk.lab.model.BookStore;
import mk.finki.ukim.mk.lab.repository.InMemoryBookStoreRepository;
import mk.finki.ukim.mk.lab.repository.jpa.BookStoreRepository;
import mk.finki.ukim.mk.lab.service.BookStoreService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BookStoreServiceImpl implements BookStoreService {
    private final BookStoreRepository bookStoreRepository;
    @Override
    public List<BookStore> findAll() {
       return bookStoreRepository.findAll();
    }
}
