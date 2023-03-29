package com.movie.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import com.movie.model.dto.GameDTO;
import com.movie.model.entity.GameEntity;

import lombok.RequiredArgsConstructor;

@Mapper(componentModel = "spring")
@RequiredArgsConstructor
public abstract class GameMapper {
    
	@Autowired
	UserMapper userMapper;
		
    @Mapping(source = "id", target = "id")
    @Mapping(expression = "java(userMapper.toDTO(gameEntity.getUser()))", target = "user")   
    @Mapping(source = "blOpen", target = "blOpen")
    public abstract GameDTO toDTO(GameEntity gameEntity);
   
}
