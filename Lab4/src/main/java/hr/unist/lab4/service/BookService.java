package hr.unist.lab5.service;

import hr.unist.lab5.dto.BookPatchRequest;
import hr.unist.lab5.model.Book;
import java.util.List;

public interface BookService {

    Book create(Book book);
    Book update(Long id, Book book);
    Book patch(Long id, BookPatchRequest request);
    void delete(Long id);
    Book getById(Long id);
    List<Book> getAll();
}
