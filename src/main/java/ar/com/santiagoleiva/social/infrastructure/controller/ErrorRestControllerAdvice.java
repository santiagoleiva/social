package ar.com.santiagoleiva.social.infrastructure.controller;

import ar.com.santiagoleiva.social.domain.exception.NonProcessableException;
import ar.com.santiagoleiva.social.domain.exception.NotFoundException;
import ar.com.santiagoleiva.social.infrastructure.controller.model.ApiErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorRestControllerAdvice {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ApiErrorResponse> handleNotFound(NotFoundException exception) {
        final ApiErrorResponse errorResponse = new ApiErrorResponse(HttpStatus.NOT_FOUND.name(), exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(NonProcessableException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ResponseEntity<ApiErrorResponse> handleNonProcessable(NonProcessableException exception) {
        final ApiErrorResponse errorResponse = new ApiErrorResponse(HttpStatus.UNPROCESSABLE_ENTITY.name(), exception.getMessage());
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(errorResponse);
    }

    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ApiErrorResponse> handleThrowable(Throwable exception) {
        final ApiErrorResponse errorResponse = new ApiErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.name(), exception.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

}
