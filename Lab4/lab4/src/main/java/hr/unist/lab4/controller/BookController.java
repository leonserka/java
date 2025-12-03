package hr.unist.lab4.controller;

import hr.unist.lab4.dto.BookPatchRequest;
import hr.unist.lab4.model.Book;
import hr.unist.lab4.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    @GetMapping
    public List<Book> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<Book> create(@RequestBody Book book) {
        Book created = service.create(book);

        return ResponseEntity
                .created(URI.create("/api/books/" + created.getId()))
                .body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> update(
            @PathVariable Long id,
            @RequestBody Book book
    ) {
        return ResponseEntity.ok(service.update(id, book));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Book> patch(
            @PathVariable Long id,
            @RequestBody BookPatchRequest request
    ) {
        return ResponseEntity.ok(service.patch(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
