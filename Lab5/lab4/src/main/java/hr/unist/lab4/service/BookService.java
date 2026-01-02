package hr.unist.lab4.service;

import hr.unist.lab4.dto.BookPatchRequest;
import hr.unist.lab4.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookService {
    Book create(Book book);
    Book update(Long id, Book book);
    Book patch(Long id, BookPatchRequest request);
    void delete(Long id);
    Book getById(Long id);

    Page<Book> getAll(String title, String author, String genre, Integer year, Pageable pageable);
}