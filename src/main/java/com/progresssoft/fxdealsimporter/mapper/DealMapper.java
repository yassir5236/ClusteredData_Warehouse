package com.progresssoft.fxdealsimporter.mapper;


import com.progresssoft.fxdealsimporter.dto.DealRequestDto;
import com.progresssoft.fxdealsimporter.dto.DealResponseDto;
import com.progresssoft.fxdealsimporter.entity.Deal;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DealMapper {
    Deal toEntity(DealRequestDto dealRequestDto);
    DealResponseDto toResponseEntity(Deal deal);
}
