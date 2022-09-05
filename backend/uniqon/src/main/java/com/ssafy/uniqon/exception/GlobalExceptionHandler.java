package com.ssafy.uniqon.exception;

import com.ssafy.uniqon.dto.response.ResponseDto;
import com.ssafy.uniqon.exception.ex.CustomException;
import com.ssafy.uniqon.exception.ex.CustomValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomValidationException.class)
    public ResponseEntity<?> customValidation(CustomValidationException e) {

        return new ResponseEntity<ResponseDto>(new ResponseDto(400, e.getMessage(),

                e.getErrorMap()), HttpStatus.BAD_REQUEST);

    }


    @ExceptionHandler(CustomException.class)
    public ResponseEntity<?> customException(CustomException e) {

        return new ResponseEntity<>(new ResponseDto(e.getErrorCode().getStatus(), e.getErrorCode().getMessage(), null),

                HttpStatus.valueOf(e.getErrorCode().getStatus()));

    }

}
