package com.java.msorder.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.java.msorder.exception.ExceptionConstants.UNEXCEPTED_EXCEPTION_CODE;
import static com.java.msorder.exception.ExceptionConstants.UNEXCEPTED_EXCEPTION_MESSAGE;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Slf4j
@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    public ExceptionResponse handle(Exception exception){
        log.error("Exception: ",exception);
        return new ExceptionResponse(UNEXCEPTED_EXCEPTION_CODE,UNEXCEPTED_EXCEPTION_MESSAGE);
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public ExceptionResponse handle(NotFoundException exception){
        log.error("NotFoundException: ",exception);
        return  new ExceptionResponse(exception.getCode(), exception.getMessage());
    }

}
