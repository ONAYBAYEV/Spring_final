package book.store.mapper;

import book.store.dto.BookDTO;
import book.store.model.BookModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;


@Mapper(componentModel = "spring")
public interface BookMapper {

    @Mapping(source = "name",target = "title")

    BookDTO toDto(BookModel bookModel);
    @Mapping(source = "title",target = "name")
    BookModel toModel(BookDTO bookDTO);

    List<BookDTO>   toDtoList(List<BookModel> bookModelList);

}
