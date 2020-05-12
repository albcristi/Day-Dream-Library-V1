package web.converter;

import core.model.Books;
import org.springframework.stereotype.Component;
import web.dto.BookDto;

@Component
public class BookConverter extends BaseConverter<Books, BookDto> {
    @Override
    public Books convertDtoToModel(BookDto dtoObject) {
        Books book = Books.builder()
                .author(dtoObject.getAuthor())
                .title(dtoObject.getTitle())
                .price(dtoObject.getPrice())
                .build();
        book.setId(dtoObject.getId());
        return book;
    }

    @Override
    public BookDto convertModelToDto(Books modelObject) {
        BookDto bookDto = BookDto.builder()
                .author(modelObject.getAuthor())
                .title(modelObject.getTitle())
                .price(modelObject.getPrice())
                .build();
        bookDto.setId(modelObject.getId());
        return bookDto;
    }
}
