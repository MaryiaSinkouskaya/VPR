package com.vpr.app.exceptions.handler;

import com.vpr.app.exceptions.InvalidCredentialsException;
import com.vpr.app.exceptions.UserAlreadyExistsException;
import com.vpr.app.exceptions.VprEntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.*;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    public static final String CANNOT_MODIFY_THE_ENTITY = "Cannot modify the entity because it is associated with other entities. Please resolve the dependencies and try again.";

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ProblemDetail> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException ex) {
        log.error(ex.getMessage());
        ProblemDetail problemDetail = ProblemDetail.forStatus(BAD_REQUEST);
        problemDetail.setTitle(BAD_REQUEST.getReasonPhrase());

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String errorMessage = error.getDefaultMessage();
            problemDetail.setDetail(errorMessage);
        });

        return ResponseEntity.status(BAD_REQUEST).body(problemDetail);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ProblemDetail> handleConstraintViolationException(
            DataIntegrityViolationException ex) {
        log.error(ex.getMessage());
        ProblemDetail problemDetail = ProblemDetail.forStatus(CONFLICT);
        problemDetail.setTitle(CONFLICT.getReasonPhrase());
        problemDetail.setDetail(CANNOT_MODIFY_THE_ENTITY);
        return ResponseEntity.status(CONFLICT).body(problemDetail);
    }

    @ExceptionHandler(VprEntityNotFoundException.class)
    public ResponseEntity<ProblemDetail> handleEntityNotFoundException(
            VprEntityNotFoundException ex) {
        log.error(ex.getMessage());
        ProblemDetail problemDetail = ProblemDetail.forStatus(NOT_FOUND);
        problemDetail.setTitle(NOT_FOUND.getReasonPhrase());
        problemDetail.setDetail(ex.getMessage());
        return ResponseEntity.status(NOT_FOUND).body(problemDetail);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ProblemDetail> handleUserExists(UserAlreadyExistsException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(CONFLICT);
        problemDetail.setTitle(CONFLICT.getReasonPhrase());
        problemDetail.setDetail(ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(problemDetail);
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<ProblemDetail> handleInvalidCredentials(InvalidCredentialsException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(UNAUTHORIZED);
        problemDetail.setTitle(UNAUTHORIZED.getReasonPhrase());
        problemDetail.setDetail(ex.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(problemDetail);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ProblemDetail> handleUserNotFound(UsernameNotFoundException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(NOT_FOUND);
        problemDetail.setTitle(NOT_FOUND.getReasonPhrase());
        problemDetail.setDetail(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(problemDetail);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ProblemDetail> handleException(Exception ex) {
        log.error(INTERNAL_SERVER_ERROR.getReasonPhrase());
        ProblemDetail problemDetail = ProblemDetail.forStatus(INTERNAL_SERVER_ERROR);
        problemDetail.setTitle(INTERNAL_SERVER_ERROR.getReasonPhrase());
        problemDetail.setDetail(ex.getMessage());
        return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(problemDetail);
    }
}
