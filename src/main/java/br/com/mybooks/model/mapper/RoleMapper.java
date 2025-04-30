package br.com.mybooks.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.com.mybooks.model.Role;
import br.com.mybooks.model.entity.RoleEntity;

@Mapper
public interface RoleMapper {
    RoleMapper MAPPER = Mappers.getMapper(RoleMapper.class);

    RoleEntity toEntity(Role model);
    Role toModel(RoleEntity entity);
}
