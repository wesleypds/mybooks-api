package br.com.mybooks.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import br.com.mybooks.model.User;
import br.com.mybooks.model.entity.UserEntity;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = RoleMapper.class)
public interface UserMapper {
    UserMapper MAPPER = Mappers.getMapper(UserMapper.class);

    UserEntity toEntity(User model);
    User toModel(UserEntity entity);
}
