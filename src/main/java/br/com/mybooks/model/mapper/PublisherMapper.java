package br.com.mybooks.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.com.mybooks.model.Publisher;
import br.com.mybooks.model.entity.PublisherEntity;

@Mapper
public interface PublisherMapper {
    PublisherMapper MAPPER = Mappers.getMapper(PublisherMapper.class);

    PublisherEntity toEntity(Publisher model);
    Publisher toModel(PublisherEntity entity);
}
