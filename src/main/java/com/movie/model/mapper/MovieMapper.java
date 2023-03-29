package com.movie.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.movie.model.dto.MovieDTO;
import com.movie.model.entity.MovieEntity;

@Mapper(componentModel = "spring")
public interface MovieMapper {
    		
    @Mapping(source = "id", target = "id")   
    @Mapping(source = "name", target = "name")
    public abstract MovieDTO toDTO(MovieEntity movieEntity);
   
}
