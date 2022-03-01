package com.test.citylistbe.controller;

import com.test.citylistbe.dto.CityDto;
import com.test.citylistbe.entity.City;
import com.test.citylistbe.service.CityService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/cities")
public class CityController {

    private final CityService cityService;

    public CityController(final CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping
    @Operation(summary = "Get list of cities")
    public Page<City> getAllCities(@RequestParam(defaultValue = "0") Integer pageNumber,
                                    @RequestParam(defaultValue = "10") Integer pageSize,
                                    @RequestParam(defaultValue = "") String cityName) {
        return cityService.findAllCities(pageNumber, pageSize, cityName);
    }

    @PutMapping()
    @Operation(summary = "Update city by id")
    public void updateCity(@RequestBody CityDto cityDto) {
        System.out.println(cityDto);
        cityService.updateCity(cityDto);
    }
}
