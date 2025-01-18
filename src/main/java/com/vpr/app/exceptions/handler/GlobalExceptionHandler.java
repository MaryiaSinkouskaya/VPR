package com.vpr.app.exceptions.handler;

import com.vpr.app.exceptions.VprEntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.*;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    public static final String CANNOT_DELETE_THE_ENTITY = "Cannot delete the entity because it is associated with other entities. Please resolve the dependencies and try again.";

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ProblemDetail> handleConstraintViolationException(
            DataIntegrityViolationException ex) {
        log.error(ex.getMessage());
        ProblemDetail problemDetail = ProblemDetail.forStatus(CONFLICT);
        problemDetail.setTitle(CONFLICT.getReasonPhrase());
        problemDetail.setDetail(CANNOT_DELETE_THE_ENTITY);
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

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ProblemDetail> handleException(Exception ex) {
        log.error(INTERNAL_SERVER_ERROR.getReasonPhrase());
        ProblemDetail problemDetail = ProblemDetail.forStatus(INTERNAL_SERVER_ERROR);
        problemDetail.setTitle(INTERNAL_SERVER_ERROR.getReasonPhrase());
        problemDetail.setDetail(ex.getMessage());
        return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(problemDetail);
    }
}
