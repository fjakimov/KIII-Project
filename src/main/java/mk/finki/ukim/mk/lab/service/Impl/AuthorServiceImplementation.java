package mk.finki.ukim.mk.lab.service.Impl;

import lombok.AllArgsConstructor;
import mk.finki.ukim.mk.lab.model.Author;
import mk.finki.ukim.mk.lab.repository.jpa.AuthorRepository;
import mk.finki.ukim.mk.lab.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthorServiceImplementation implements AuthorService {
    private final AuthorRepository authorRepository;
    @Override
    public List<Author> listAuthors() {
        return authorRepository.findAll();
    }

    @Override
    public Optional<Author> findById(Long id) {
        return authorRepository.findById(id);
    }
}
