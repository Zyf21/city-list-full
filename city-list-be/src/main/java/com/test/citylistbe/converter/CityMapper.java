package com.test.citylistbe.converter;

import com.test.citylistbe.dto.CityDto;
import com.test.citylistbe.entity.City;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CityMapper {

    @Mapping(target = "photo", ignore = true)
    City updateEntityByDto(@MappingTarget City entity, CityDto CityDto);
}
