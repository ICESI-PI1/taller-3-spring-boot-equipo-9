package icesi.edu.co.LibrarySystem.persistence.repositories;
import icesi.edu.co.LibrarySystem.persistence.models.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BookRepository extends CrudRepository<Book,Long> {

}
