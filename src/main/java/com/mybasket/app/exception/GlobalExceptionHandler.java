package com.mybasket.app.exception;

import com.mybasket.app.dto.ErrorResponse;
import com.mybasket.app.dto.ValidationErrorResponse;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ErrorResponse> handleNoSuchElement(NoSuchElementException ex) {

        ErrorResponse errorResponse =
                new ErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND, 404);

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(
            ResourceNotFoundException ex) {

        ErrorResponse errorResponse =
                new ErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND, 404);

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> handleBadRequest(
            BadRequestException ex) {
        ErrorResponse errorResponse =
                new ErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST, 400);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ValidationErrorResponse>> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException ex) {


        List<ValidationErrorResponse> fieldsErrorList =
                ex.getBindingResult()
                        .getFieldErrors()
                        .stream()
                        .map(error ->
                                new ValidationErrorResponse(
                                        error.getField(),
                                        error.getDefaultMessage()
                                )
                        )
                        .toList();


        return new ResponseEntity<>(fieldsErrorList, HttpStatus.BAD_REQUEST);
    }
}