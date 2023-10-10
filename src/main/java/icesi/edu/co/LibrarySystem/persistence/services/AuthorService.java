package icesi.edu.co.LibrarySystem.persistence.services;

import icesi.edu.co.LibrarySystem.persistence.models.Author;
import icesi.edu.co.LibrarySystem.persistence.repositories.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Author save(Author author) {
        return authorRepository.save(author);
    }

    public List<Author> findAll() {
        return (List<Author>) authorRepository.findAll();
    }

}
