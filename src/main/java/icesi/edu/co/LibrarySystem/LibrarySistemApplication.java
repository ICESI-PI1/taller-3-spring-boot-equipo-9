package icesi.edu.co.LibrarySystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("icesi.edu.co.LibrarySystem.persistence.models")
public class LibrarySistemApplication {
    public static void main(String[] args) {
        SpringApplication.run(LibrarySistemApplication.class, args);
    }
}
