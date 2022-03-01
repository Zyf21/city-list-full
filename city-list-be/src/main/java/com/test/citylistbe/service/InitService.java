package com.test.citylistbe.service;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.test.citylistbe.entity.City;
import com.test.citylistbe.repositories.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
public class InitService {

    private static final String INIT_CITY_DATA = "data/cities.csv";

    private final CityRepository cityRepository;

    @Autowired
    public InitService(final CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }


    @PostConstruct
    public void init() {
        if (cityRepository.count() == 0) {
            initCities();
        }
    }

    public void initCities() {
        List<City> cities = new ArrayList<>();

        try {

            ClassLoader classLoader = InitService.class.getClassLoader();
            InputStream inputStream = classLoader.getResourceAsStream(INIT_CITY_DATA);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            CSVParser parser = new CSVParserBuilder().withSeparator(',').build();
            CSVReader csvReader = new CSVReaderBuilder(bufferedReader)
                    .withSkipLines(1)
                    .withCSVParser(parser)
                    .build();
            List<String[]> allData = csvReader.readAll();

            for (String[] row : allData) {
                City city = new City();
                city.setId(Long.valueOf(row[0]));
                city.setName((String) Array.get(row, 1));
                city.setPhoto((String) Array.get(row, 2));
                cities.add(city);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        cityRepository.saveAll(cities);
    }
}
