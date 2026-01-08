package hr.unist.lab6.service;

import hr.unist.lab6.dto.BookPatchRequest;
import hr.unist.lab6.exception.BookNotFoundException;
import hr.unist.lab6.exception.InvalidRequestException;
import hr.unist.lab6.model.Book;
import hr.unist.lab6.repository.BookRepository;
import hr.unist.lab6.specification.BookSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository repository;

    public BookServiceImpl(BookRepository repository) {
        this.repository = repository;
    }

    @Override
    public Page<Book> getAll(
            String title,
            String author,
            String genre,
            Integer publishedYear,
            Pageable pageable) {

        Specification<Book> spec = Specification.where(null);

        if (title != null) {
            spec = spec.and(BookSpecification.titleContains(title));
        }
        if (author != null) {
            spec = spec.and(BookSpecification.authorContains(author));
        }
        if (genre != null) {
            spec = spec.and(BookSpecification.genreEquals(genre));
        }
        if (publishedYear != null) {
            if (publishedYear < 0) {
                throw new InvalidRequestException("Published year cannot be negative");
            }
            spec = spec.and(BookSpecification.publishedYearEquals(publishedYear));
        }

        return repository.findAll(spec, pageable);
    }

    @Override
    public Book getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new BookNotFoundException("Book not found with id: " + id));
    }

    @Override
    public Book create(Book book) {
        return repository.save(book);
    }

    @Override
    public Book update(Long id, Book book) {
        Book existing = getById(id);
        existing.setTitle(book.getTitle());
        existing.setAuthor(book.getAuthor());
        existing.setGenre(book.getGenre());
        existing.setPublishedYear(book.getPublishedYear());
        return repository.save(existing);
    }

    @Override
    public Book patch(Long id, BookPatchRequest request) {
        Book book = getById(id);

        if (request.getTitle() != null) {
            book.setTitle(request.getTitle());
        }
        if (request.getAuthor() != null) {
            book.setAuthor(request.getAuthor());
        }
        if (request.getYear() != null) {
            book.setPublishedYear(request.getYear());
        }

        return repository.save(book);
    }

    @Override
    public void delete(Long id) {
        Book book = getById(id);
        repository.delete(book);
    }
}
