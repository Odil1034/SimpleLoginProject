package uz.pdp.SimpleLoginProject.mapper;

import org.mapstruct.Mapping;
import uz.pdp.SimpleLoginProject.dtos.CreateBookDto;
import uz.pdp.SimpleLoginProject.model.Book;

public interface CreateBookMapper {

    @Mapping(target = "title", source = "title")
    CreateBookDto bookToCreateBookDto(Book book);


}
