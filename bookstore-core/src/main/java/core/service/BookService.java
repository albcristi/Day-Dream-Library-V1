package core.service;
import core.model.Books;
import java.util.List;
import java.util.Optional;

public interface BookService {
    Boolean addBook(Books book);

    Boolean removeBook(Long bookID);

    Boolean updateBook(Books updatedBook);

    List<Books> getAllBooks();

    List<Books>  filterByTitle(String searchString);

    List<Books>  filterByAuthor(String author);

    List<Books> sortByAuthorAndTitleAndID();

    Optional<Books> findOne(Long bookId);
}
