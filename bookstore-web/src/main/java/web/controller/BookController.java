package web.controller;

import core.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import web.converter.BookConverter;
import web.dto.BookDto;

import java.util.List;
import java.util.stream.Collectors;


@RestController
public class BookController {
    public static final Logger logger = LoggerFactory.getLogger(BookController.class);

    @Autowired
    private BookService bookService;

    @Autowired
    private BookConverter bookConverter;

    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public List<BookDto> getBooks(){
       logger.trace("getBooks: method entered --- no params");
       List<BookDto> booksDto = bookService.getAllBooks()
               .stream()
               .map(b->bookConverter.convertModelToDto(b))
               .collect(Collectors.toList());
       logger.trace("getBooks: method finished --- return value List<BookDto>={}",booksDto);
       return booksDto;
    }

    @RequestMapping(value = "/books", method = RequestMethod.POST)
    public Boolean saveBook(@RequestBody BookDto bookDto){
        logger.trace("saveBook: method entered --- bookDto={}",bookDto);
        Boolean result = bookService.addBook(bookConverter.convertDtoToModel(bookDto));
        logger.trace("saveBook: method finished --- result value Boolean={}", result);
        return result;
    }

    @RequestMapping(value = "/books/{id}", method = RequestMethod.PUT)
    public Boolean updateBook(@PathVariable Long id, @RequestBody BookDto bookDto){
        logger.trace("updateBook: method entered --- id ={}, bookDto={}",id,bookDto);
        Boolean result = bookService.updateBook(bookConverter.convertDtoToModel(bookDto));
        logger.trace("updateBook: method finished --- result value Boolean={}", result);
        return result;
    }

    @RequestMapping(value ="/books/{id}", method = RequestMethod.DELETE)
    public Boolean removeBook(@PathVariable Long id){
        logger.trace("removeBook: method entered --- id ={}",id);
        Boolean result = bookService.removeBook(id);
        logger.trace("removeBook: method finished --- result value Boolean={}", result);
        return result;
    }

    @RequestMapping(value = "/books-filter-title/{title}", method = RequestMethod.GET)
    public List<BookDto> getBooksFilterByTitle(@PathVariable String title){
        logger.trace("getBooksFilterByTitle: method entered --- title={}",title);
        List<BookDto> booksDto =bookService.filterByTitle(title)
                .stream()
                .map(b->bookConverter.convertModelToDto(b))
                .collect(Collectors.toList());
        logger.trace("getBooksFilterByTitle: method finished --- return value List={}",booksDto);
        return booksDto;
    }

    @RequestMapping(value = "/books-filter-author/{author}", method = RequestMethod.GET)
    public List<BookDto> getBooksFilteredByAuthor(@PathVariable String author){
        logger.trace("getBooksFilterByAuthor: method entered --- author={}",author);
        List<BookDto> booksDto = bookService.filterByAuthor(author)
                .stream()
                .map(b->bookConverter.convertModelToDto(b))
                .collect(Collectors.toList());
        logger.trace("getBooksFilterByAuthor: method finished --- return value List={}",booksDto);
        return booksDto;
    }

    @RequestMapping(value = "/books-sorted-ati", method = RequestMethod.GET)
    public List<BookDto> getBooksSortedByAuthorTitleAndId(){
        logger.trace("getBooksSortedByAuthorTitleAndId: method entered --- no params");
        List<BookDto> booksDto =bookService.sortByAuthorAndTitleAndID()
                .stream()
                .map(b->bookConverter.convertModelToDto(b))
                .collect(Collectors.toList());
        logger.trace("getBooksSortedByAuthorTitleAndId: method finished --- return value List={}",booksDto);
        return booksDto;
    }
}
