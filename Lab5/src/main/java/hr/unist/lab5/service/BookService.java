package hr.unist.lab5.service;

import hr.unist.lab5.dto.BookPatchRequest;
import hr.unist.lab5.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookService {

    Page<Book> getAll(
            String title,
            String author,
            String genre,
            Integer publishedYear,
            Pageable pageable
    );

    Book getById(Long id);

    Book create(Book book);

    Book update(Long id, Book book);

    Book patch(Long id, BookPatchRequest request);

    void delete(Long id);
}
