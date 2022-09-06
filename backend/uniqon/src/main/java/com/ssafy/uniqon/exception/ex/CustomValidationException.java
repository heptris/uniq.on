package com.ssafy.uniqon.exception.ex;
import lombok.Data;

import java.util.Map;

@Data
public class CustomValidationException extends RuntimeException{

    private String message;
    private Map<String, String> errorMap;


    public CustomValidationException(String message, Map<String, String> errorMap) {
        this.message = message;
        this.errorMap = errorMap;
    }

}
