package com.hwiyeon.actions_docker_aks.error;

import java.time.format.DateTimeParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.hwiyeon.actions_docker_aks.error.exception.*;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<CustomExceptionResponse> IllegalArgumentException(
        IllegalArgumentException e) {
        return new ResponseEntity<>(CustomExceptionResponse.from(e.getMessage(),
            CustomErrorMessage.ILLEGAL_INPUT_VALUE_EXCEPTION),
            HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CustomExceptionResponse> handleMethodArgumentNotValidException(
        MethodArgumentNotValidException e) {
        return new ResponseEntity<>(CustomExceptionResponse.from(
            e.getBindingResult().getFieldError().getDefaultMessage(),
            CustomErrorMessage.ILLEGAL_INPUT_VALUE_EXCEPTION),
            HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<CustomExceptionResponse> handleAlreadyExistsException(AlreadyExistsException e) {
        return new ResponseEntity<>(CustomExceptionResponse.of(e.getMessage()), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(DateTimeParseException.class)
    public ResponseEntity<CustomExceptionResponse> hanleParseException() {
        return new ResponseEntity<>(CustomExceptionResponse.of(
                                        CustomErrorMessage.ILLEGAL_DATETIME_FORMAT_EXCEPTION),
                                    HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotExistsException.class)
    public ResponseEntity<CustomExceptionResponse> handleNotExistsException(
        NotExistsException e) {
        return new ResponseEntity<>(CustomExceptionResponse.of(e.getMessage()),
                                    HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PasswordNotMatchedException.class)
    public ResponseEntity<CustomExceptionResponse> handlePasswordNotMatchedException(
        PasswordNotMatchedException e) {
        return new ResponseEntity<>(CustomExceptionResponse.of(e.getMessage()),
                                    HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<CustomExceptionResponse> handleAuthenticationException(
        AuthenticationException e) {
        return new ResponseEntity<>(CustomExceptionResponse.of(e.getMessage()),
                                    HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(IllegalMemberRoleException.class)
    public ResponseEntity<CustomExceptionResponse> handleIllegalMemberRoleException(
        IllegalMemberRoleException e) {
        return new ResponseEntity<>(CustomExceptionResponse.of(e.getMessage()),
            HttpStatus.UNAUTHORIZED);
    }

}
