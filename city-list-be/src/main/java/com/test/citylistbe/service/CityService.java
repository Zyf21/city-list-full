package com.test.citylistbe.service;

import com.test.citylistbe.converter.CityMapper;
import com.test.citylistbe.dto.CityDto;
import com.test.citylistbe.entity.City;
import com.test.citylistbe.exeption.ErrorCodes;
import com.test.citylistbe.exeption.RestException;
import com.test.citylistbe.repositories.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class CityService {

    private final CityRepository cityRepository;
    private final CityMapper cityMapper;

    @Autowired
    public CityService(final CityRepository cityRepository,
                       final CityMapper cityMapper) {
        this.cityRepository = cityRepository;
        this.cityMapper = cityMapper;
    }

    public Page<City> findAllCities(Integer pageNumber, Integer pageSize, String cityName) {
        Pageable pageRequest = PageRequest.of(pageNumber, pageSize, Sort.by("id"));
        return cityRepository.findByNameContainingIgnoreCase(cityName, pageRequest);
    }

    public void updateCity(CityDto cityDto){
        City cityFromDB = cityRepository.findById(cityDto.getId())
                .orElseThrow(() -> new RestException(ErrorCodes.CITY_NOT_FOUND));
        cityRepository.save(cityMapper.updateEntityByDto(cityFromDB, cityDto));
    }

}
