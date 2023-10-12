package icesi.edu.co.LibrarySystem.persistence.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ManyToAny;



@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Book {

    @Id
    @GeneratedValue
    private Long id;
    private String title;

    @ManyToAny
    private Data dataPublisher;

    @ManyToOne
    private Author author;

    public Book(Book book) {

        this(book.getId(), book.getTitle(),book.getDataPublisher(), book.getAuthor());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Data getDataPublisher() {
        return dataPublisher;
    }

    public void setDataPublisher(Data dataPublisher) {
        this.dataPublisher = dataPublisher;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
