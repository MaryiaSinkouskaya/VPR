package com.vpr.app.exceptions.handler;

import com.vpr.app.exceptions.VprEntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(VprEntityNotFoundException.class)
    public ResponseEntity<ProblemDetail> handleEntityNotFoundException(VprEntityNotFoundException ex) {
        log.error(NOT_FOUND.getReasonPhrase());
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
