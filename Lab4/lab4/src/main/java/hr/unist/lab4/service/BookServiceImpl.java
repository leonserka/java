package hr.unist.lab4.service;

import hr.unist.lab4.dto.BookPatchRequest;
import hr.unist.lab4.model.Book;
import hr.unist.lab4.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository repo;

    public BookServiceImpl(BookRepository repo) {
        this.repo = repo;
    }

    @Override
    public Book create(Book book) {
        return repo.save(book);
    }

    @Override
    public Book update(Long id, Book newData) {
        Book book = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        book.setTitle(newData.getTitle());
        book.setAuthor(newData.getAuthor());
        book.setYear(newData.getYear());

        return repo.save(book);
    }

    @Override
    public Book patch(Long id, BookPatchRequest request) {
        Book book = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        if (request.getTitle() != null) {
            book.setTitle(request.getTitle());
        }
        if (request.getAuthor() != null) {
            book.setAuthor(request.getAuthor());
        }
        if (request.getYear() != null) {
            book.setYear(request.getYear());
        }

        return repo.save(book);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }

    @Override
    public Book getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));
    }

    @Override
    public List<Book> getAll() {
        return repo.findAll();
    }
}
