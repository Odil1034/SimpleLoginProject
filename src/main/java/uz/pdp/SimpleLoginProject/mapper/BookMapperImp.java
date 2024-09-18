package uz.pdp.SimpleLoginProject.mapper;

import uz.pdp.SimpleLoginProject.dtos.CreateBookDto;
import uz.pdp.SimpleLoginProject.model.Book;

public class BookMapperImp implements CreateBookMapper {

    @Override
    public CreateBookDto bookToCreateBookDto(Book book) {
        if (book == null) {
            return null;
        }

        return CreateBookDto.builder()
                .title(book.getTitle())
                .author(book.getAuthor())
                .description(book.getDescription())
                .countOfPages(book.getCountOfPages())
                .build();
    }
}
