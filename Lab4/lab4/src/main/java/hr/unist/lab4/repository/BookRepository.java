package hr.unist.lab4.repository;

import hr.unist.lab4.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

}
