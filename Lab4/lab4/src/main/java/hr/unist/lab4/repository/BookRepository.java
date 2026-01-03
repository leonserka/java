package hr.unist.lab5.repository;

import hr.unist.lab5.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

}
