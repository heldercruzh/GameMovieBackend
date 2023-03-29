package com.movie.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import com.movie.model.dto.QuestionDTO;
import com.movie.model.entity.QuestionEntity;

import lombok.RequiredArgsConstructor;

@Mapper(componentModel = "spring")
@RequiredArgsConstructor
public abstract class QuestionMapper {
    
	@Autowired
	GameMapper gameMapper;
	
	@Autowired
	MovieMapper movieMapper;
	    
	@Mapping(source = "id", target = "id") 
    @Mapping(expression = "java(gameMapper.toDTO(questionEntity.getGame()))", target = "game")   
    @Mapping(expression = "java(movieMapper.toDTO(questionEntity.getFirstMovie()))", target = "firstMovie")
    @Mapping(expression = "java(movieMapper.toDTO(questionEntity.getSecondMovie()))", target = "secondMovie")
    public abstract QuestionDTO toDTO(QuestionEntity questionEntity);
   
}
