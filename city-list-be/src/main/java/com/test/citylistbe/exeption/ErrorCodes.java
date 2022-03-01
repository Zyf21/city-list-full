package com.test.citylistbe.exeption;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;


@Getter
@AllArgsConstructor
public enum ErrorCodes {

    CITY_NOT_FOUND("000001", "City not found", HttpStatus.NOT_FOUND);

    private String code;
    private String message;
    private HttpStatus httpStatus;
}
