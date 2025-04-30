package br.com.mybooks.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.com.mybooks.model.Book;
import br.com.mybooks.model.entity.BookEntity;

@Mapper(uses = {PublisherMapper.class, ReadingDataMapper.class, AuthorMapper.class})
public interface BookMapper {
    BookMapper MAPPER = Mappers.getMapper(BookMapper.class);

    BookEntity toEntity(Book model);
    Book toModel(BookEntity entity);
}
