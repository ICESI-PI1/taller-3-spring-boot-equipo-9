package icesi.edu.co.LibrarySystem.persistence.controllers;

import icesi.edu.co.LibrarySystem.persistence.models.Author;
import icesi.edu.co.LibrarySystem.persistence.services.AuthorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/autores")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping
    public Author save(@RequestBody Author author) {
        return authorService.save(author);
    }

    @GetMapping
    public List<Author> findAll() {
        return authorService.findAll();
    }


}

