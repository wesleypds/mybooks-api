package br.com.mybooks.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.com.mybooks.model.Author;
import br.com.mybooks.model.entity.AuthorEntity;

@Mapper
public interface AuthorMapper {
    AuthorMapper MAPPER = Mappers.getMapper(AuthorMapper.class);

    AuthorEntity toEntity(Author model);
    Author toModel(AuthorEntity entity);
}
