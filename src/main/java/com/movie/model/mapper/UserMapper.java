package com.movie.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.movie.model.dto.UserDTO;
import com.movie.model.entity.UserEntity;
import com.movie.model.vo.UserVO;

import lombok.RequiredArgsConstructor;

@Mapper(componentModel = "spring")
@RequiredArgsConstructor
public abstract class UserMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "email", target = "email")
    @Mapping(expression = "java(null)", target = "score")
    @Mapping(source = "password", target = "password")   
    @Mapping(expression = "java(null)", target = "idProfile")
    @Mapping(expression = "java(null)", target = "dataAtualizacao")
    public abstract UserEntity toEntity(UserVO vo);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "score", target = "score") 
    public abstract UserDTO toDTO(UserEntity entity);
   
}
