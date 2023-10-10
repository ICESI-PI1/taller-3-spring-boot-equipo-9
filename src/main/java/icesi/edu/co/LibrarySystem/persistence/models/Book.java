package icesi.edu.co.LibrarySystem.persistence.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;




@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Book {
    @Id
    @GeneratedValue

    private Long id;
    private String title;
    private java.util.Date dataPublisher;

    @ManyToOne
    @JoinColumn(name = "author_id") // El nombre de la columna de la clave foránea en la tabla Book
    private Author author;





    public Book(Book book) {
        this(book.getId(), book.getTitle(),book.getDataPublisher(), book.getAuthor());
    }

}
