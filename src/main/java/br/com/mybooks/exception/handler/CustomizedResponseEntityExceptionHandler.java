package br.com.mybooks.exception.handler;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.mybooks.exception.ExceptionResponse;
import br.com.mybooks.exception.InvalidJwtAuthenticationException;
import br.com.mybooks.exception.RequiredIsNullException;
import br.com.mybooks.exception.ResourceNotFoundException;


@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionResponse> handleAllExceptions(Exception ex, WebRequest request) {
        ex.printStackTrace();
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                                                        new Date(), 
                                                        ex.getMessage(), 
                                                        request.getDescription(false));
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exceptionResponse);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public final ResponseEntity<ExceptionResponse> handleNotFoundExceptions(Exception ex, WebRequest request) {
        ex.printStackTrace();
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                                                        new Date(), 
                                                        ex.getMessage(), 
                                                        request.getDescription(false));
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionResponse);
    }

    @ExceptionHandler(RequiredIsNullException.class)
    public final ResponseEntity<ExceptionResponse> handleBadRequestExceptions(Exception ex, WebRequest request) {
        ex.printStackTrace();
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                                                        new Date(), 
                                                        ex.getMessage(), 
                                                        request.getDescription(false));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse);
    }

    @ExceptionHandler(InvalidJwtAuthenticationException.class)
    public final ResponseEntity<ExceptionResponse> handleInvalidJwtAuthenticationExceptions(Exception ex, WebRequest request) {
        ex.printStackTrace();
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                                                        new Date(), 
                                                        ex.getMessage(), 
                                                        request.getDescription(false));
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(exceptionResponse);
    }

}
