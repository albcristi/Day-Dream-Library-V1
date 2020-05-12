package core.service;

import core.model.BaseEntity;
import core.model.Books;
import core.model.validators.BookValidator;
import core.model.validators.BookstoreException;
import core.repository.BookRepository;
import core.repository.sort_pack.Sort;
import core.repository.sort_pack.SortAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

@Service
public class BookServiceImp implements BookService {
    public static final Logger logger = LoggerFactory.getLogger(BookServiceImp.class);

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookValidator bookValidator;


    private Boolean bookExists(String bookName, String author){
        try {
            logger.trace("bookExists: methods entered bookExists(bookName={},author={})", bookName, author);
            Iterable<Books> books = bookRepository.findAll();
            AtomicBoolean bool = new AtomicBoolean(false);
            books.forEach(book -> {
                if (book.getTitle().equals(bookName) && book.getAuthor().equals(author))
                    bool.set(true);
            });
            logger.trace("bookExists: exit method with return value = {}", bool);
            return bool.get();
        }
        catch (Exception exc){
            logger.trace("bookExists: failed with error message: {} ",exc.getMessage());
            return false;
        }
    }

    @Override
    @Transactional
    public Boolean addBook(Books book) {
        logger.trace("addBook: method entered --- addBook(book={}) ",book);
        logger.trace("addBook: validate input: validate({})",book);
        try {
            book.setId(generateNextId());
            bookValidator.validate(book);
            if (bookExists(book.getTitle(), book.getAuthor()) || bookRepository.findById(book.getId()).isPresent()) {
                logger.trace("addBook: method exits with return value boolean={}", "false");
                return false;
            }
            bookRepository.save(book);
            logger.trace("addBook: method exits with return value boolean={}", "true");
            return true;
        }
        catch (Exception exc){
            logger.trace("addBook  --- failed with Exception: {}",exc.getMessage());
            return false;
        }
    }

    @Override
    @Transactional
    public Boolean removeBook(Long bookID) {
        logger.trace("removeBook:   method entered: removeBook(bookID={})", bookID);
        AtomicBoolean bool = new AtomicBoolean(true);
        try {
            bookRepository.findById(bookID)
                    .ifPresentOrElse((b) -> {
                        bool.set(true);
                    }, () -> {
                        bool.set(false);
                    });
            if (bool.get()) {
                bookRepository.deleteById(bookID);
                logger.trace("removeBook:  book removed");
                logger.trace("removeBook:  method finished, return value={}", bool.get());
                return bool.get();
            }
            logger.trace("removeBook:  method finished, return value={}", bool.get());
            return bool.get();
        }
        catch (Exception exc){
            logger.trace("removeBook  --- failed with Exception: {}",exc.getMessage());
            return false;
        }
    }

    @Override
    @Transactional
    public Boolean updateBook(Books updatedBook) {
        try {
            logger.trace("updateBook:  method entered with updatedBook={}", updatedBook);
            AtomicBoolean atomicBoolean = new AtomicBoolean(true);
            bookRepository.findById(updatedBook.getId())
                    .ifPresentOrElse((b) -> {
                        logger.trace("updateBook: validation process");
                        bookValidator.validate(updatedBook);
                        b.setAuthor(updatedBook.getAuthor());
                        b.setTitle(updatedBook.getTitle());
                        b.setPrice(updatedBook.getPrice());
                        logger.trace("updateBook:   method finished with return value boolean={}", atomicBoolean.get());
                    }, () -> {
                        atomicBoolean.set(false);
                        logger.trace("updateBook:   method finished with return value boolean={}", atomicBoolean.get());
                    });
            return atomicBoolean.get();
        }
        catch (Exception exc){
            logger.trace("updateBook  --- failed with Exception: {}",exc.getMessage());
            return false;
        }
    }

    @Override
    public List<Books> getAllBooks() {
        logger.trace("getAllBooks:   method entered");
        List<Books> books = bookRepository.findAll();
        logger.trace("getAllBooks:   method finished with return value: {}",books);
        return books;
    }

    @Override
    public List<Books> filterByTitle(String searchString) {
        logger.trace("filterByTitle: method entered filterByTitle(searchString={})",searchString);
        List<Books> books = bookRepository.findAll()
                .stream()
                .filter(b->b.getTitle().indexOf(searchString)!=-1)
                .collect(Collectors.toList());
        logger.trace("filterByTitle: method finished with return value list<Book>={}",books);
        return books;
    }

    @Override
    public List<Books> filterByAuthor(String author) {
        logger.trace("filterByTitle: method entered filterByTitle(author={})",author);
        List<Books> books = bookRepository.findAll()
                .stream()
                .filter(b->b.getAuthor().indexOf(author)!=-1)
                .collect(Collectors.toList());
        logger.trace("filterByTitle: method finished with return value list<Book>={}",books);
        return books;
    }

    @Override
    public List<Books> sortByAuthorAndTitleAndID() {
        logger.trace("sortByAuthorAndTitleAndID: method entered");
        Sort sortObject = new Sort("author").and(new Sort(Boolean.FALSE,"title").and(new Sort("id")));
        SortAlgorithm<Long, Books> sortAlgorithm = new SortAlgorithm<Long, Books>(bookRepository.findAll(), sortObject);
        List<Books> lst = sortAlgorithm.sort();
        logger.trace("sortByAuthorAndTitleAndID: method finished with return value: list<Book>={}", lst);
        return lst;
    }

    @Override
    public Optional<Books> findOne(Long bookId) {
        logger.trace("findOne: method entered --- bookId={}",bookId);
        Optional<Books> book = bookRepository.findById(bookId);
        logger.trace("findOne: method finished --- return value optional<book>={}", book);
        return book;
    }

    private Long generateNextId(){
        if(getAllBooks().size()==0)
            return 1L;
        Long id = getAllBooks().stream()
                .map(BaseEntity::getId)
                .max(Comparator.naturalOrder())
                .get();
        return id+1;
    }
}
