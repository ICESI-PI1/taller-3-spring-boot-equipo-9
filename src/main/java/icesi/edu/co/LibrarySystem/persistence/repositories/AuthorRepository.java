package icesi.edu.co.LibrarySystem.persistence.repositories;

import icesi.edu.co.LibrarySystem.persistence.models.Author;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Long> {

}
