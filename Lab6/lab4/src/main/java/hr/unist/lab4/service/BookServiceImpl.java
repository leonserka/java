package hr.unist.lab4.service;

import hr.unist.lab4.dto.BookPatchRequest;
import hr.unist.lab4.exception.BookNotFoundException;
import hr.unist.lab4.model.Book;
import hr.unist.lab4.repository.BookRepository;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
                .orElseThrow(() -> new BookNotFoundException("Book with id " + id + " not found"));

        book.setTitle(newData.getTitle());
        book.setAuthor(newData.getAuthor());
        book.setYear(newData.getYear());
        book.setGenre(newData.getGenre());

        return repo.save(book);
    }

    @Override
    public Book patch(Long id, BookPatchRequest request) {
        Book book = repo.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book with id " + id + " not found"));

        if (request.getTitle() != null) book.setTitle(request.getTitle());
        if (request.getAuthor() != null) book.setAuthor(request.getAuthor());
        if (request.getYear() != null) book.setYear(request.getYear());

        return repo.save(book);
    }

    @Override
    public void delete(Long id) {
        if (!repo.existsById(id)) {
            throw new BookNotFoundException("Book with id " + id + " not found");
        }
        repo.deleteById(id);
    }

    @Override
    public Book getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book with id " + id + " not found"));
    }

    @Override
    public Page<Book> getAll(String title, String author, String genre, Integer year, Pageable pageable) {

        Specification<Book> spec = (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (title != null && !title.isBlank()) {
                predicates.add(cb.like(cb.lower(root.get("title")), "%" + title.toLowerCase() + "%"));
            }
            if (author != null && !author.isBlank()) {
                predicates.add(cb.like(cb.lower(root.get("author")), "%" + author.toLowerCase() + "%"));
            }
            if (genre != null && !genre.isBlank()) {
                predicates.add(cb.like(
                        cb.lower(cb.coalesce(root.get("genre"), "")),
                        "%" + genre.toLowerCase() + "%"
                ));
            }
            if (year != null) {
                predicates.add(cb.equal(root.get("year"), year));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };

        return repo.findAll(spec, pageable);
    }
}
