package com.workshop.book.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BookNotFoundException.class)
    @ResponseBody
    public ResponseEntity<ApiError> handleBookNotFound(BookNotFoundException exception) {
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND.value(), exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
    }

    @ExceptionHandler(DuplicateIsbnException.class)
    @ResponseBody
    public ResponseEntity<ApiError> handleDuplicateIsbn(DuplicateIsbnException exception) {
        ApiError apiError = new ApiError(HttpStatus.CONFLICT.value(), exception.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiError);
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<ApiError> handleGenericError(Exception exception) {
        ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Something went wrong: " + exception.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiError);
    }
}
