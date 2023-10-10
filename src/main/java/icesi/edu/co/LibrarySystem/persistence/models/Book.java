package icesi.edu.co.LibrarySystem.persistence.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Book {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private Data dataPublicher;
    private Author author;

    public Book(Book book) {this(book.getId(), book.getTitle(),book.getDataPublicher(), book.getAuthor());}

}
