package com.codesoom.assignment.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@RestController
@ControllerAdvice
public class CusomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(TaskIdNotFoundException.class)
    protected final ResponseEntity<Object> handleTaskIdNotFoundException
            (Exception ex, WebRequest request) {
        System.out.println(request.getDescription(true));
        //요청 url
        ExceptionResponse exceptionResponse
                = new ExceptionResponse(ex.getMessage(), request.getDescription(true));
        return new ResponseEntity(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected final ExceptionResponse handleIllegalArgumentException
            (Exception ex, WebRequest request) {
        return new ExceptionResponse(ex.getMessage(), request.getDescription(false));
    }

    @Override
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported
            (HttpRequestMethodNotSupportedException ex
                    , HttpHeaders headers, HttpStatus status,
             WebRequest request) {
        return super.handleHttpRequestMethodNotSupported(ex, headers, status, request);
    }
}