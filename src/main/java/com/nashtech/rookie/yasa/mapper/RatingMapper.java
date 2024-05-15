package com.nashtech.rookie.yasa.mapper;

import com.nashtech.rookie.yasa.dto.request.CreateRatingDto;
import com.nashtech.rookie.yasa.dto.request.UpdateRatingDto;
import com.nashtech.rookie.yasa.dto.response.RatingDto;
import com.nashtech.rookie.yasa.entity.Rating;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RatingMapper {
    
    RatingMapper INSTANCE = Mappers.getMapper(RatingMapper.class);

    RatingDto toDto(Rating entity);
    Rating toEntity(CreateRatingDto dto);
    Rating updateEntity(@MappingTarget Rating entity, UpdateRatingDto dto);
}
