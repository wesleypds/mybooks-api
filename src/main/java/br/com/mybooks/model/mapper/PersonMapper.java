package br.com.mybooks.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import br.com.mybooks.model.Person;
import br.com.mybooks.model.entity.PersonEntity;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {UserMapper.class, BookMapper.class})
public interface PersonMapper {
    PersonMapper MAPPER = Mappers.getMapper(PersonMapper.class);

    PersonEntity toEntity(Person model);
    Person toModel(PersonEntity entity);
}
