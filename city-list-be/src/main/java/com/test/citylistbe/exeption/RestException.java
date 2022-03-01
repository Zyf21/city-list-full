package com.test.citylistbe.exeption;

import lombok.Data;

@Data
public class RestException extends RuntimeException{

    private static final long serialVersionUID  = 4894822459960861194L;

    private int httpStatus;
    private String errorCode;


    public RestException(String message, int httpStatus, String errorCode) {
        super(message);
        this.httpStatus = httpStatus;
        this.errorCode = errorCode;
    }

    public RestException(ErrorCodes errorCode) {
        super(errorCode.getMessage());
        this.httpStatus = errorCode.getHttpStatus().value();
        this.errorCode = errorCode.getCode();
    }
}
