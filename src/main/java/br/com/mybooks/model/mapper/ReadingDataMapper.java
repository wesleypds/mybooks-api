package br.com.mybooks.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.com.mybooks.model.ReadingData;
import br.com.mybooks.model.entity.ReadingDataEntity;

@Mapper
public interface ReadingDataMapper {
    ReadingDataMapper MAPPER = Mappers.getMapper(ReadingDataMapper.class);

    ReadingDataEntity toEntity(ReadingData model);
    ReadingData toModel(ReadingDataEntity entity);
}
