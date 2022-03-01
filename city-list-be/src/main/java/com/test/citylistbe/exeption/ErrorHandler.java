package com.test.citylistbe.exeption;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(RestException.class)
    public @ResponseBody
    ErrorInfo handleRestException(HttpServletResponse response, RestException exception) {
        response.setStatus(exception.getHttpStatus());
        return new ErrorInfo(exception.getErrorCode(), exception.getMessage());
    }
}
