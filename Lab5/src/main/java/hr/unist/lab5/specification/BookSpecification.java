package hr.unist.lab5.specification;



import org.springframework.data.jpa.domain.Specification;
import hr.unist.lab5.model.Book;

public class BookSpecification {

    public static Specification<Book> titleContains(String title) {
        return (root, query, cb) ->
                cb.like(cb.lower(root.get("title")),
                        "%" + title.toLowerCase() + "%");
    }

    public static Specification<Book> authorContains(String author) {
        return (root, query, cb) ->
                cb.like(cb.lower(root.get("author")),
                        "%" + author.toLowerCase() + "%");
    }

    public static Specification<Book> genreEquals(String genre) {
        return (root, query, cb) ->
                cb.equal(root.get("genre"), genre);
    }

    public static Specification<Book> publishedYearEquals(Integer year) {
        return (root, query, cb) ->
                cb.equal(root.get("publishedYear"), year);
    }
}
