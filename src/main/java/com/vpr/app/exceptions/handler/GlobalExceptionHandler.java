package com.vpr.app.exceptions.handler;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import com.vpr.app.exceptions.VprEntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(VprEntityNotFoundException.class)
  public ResponseEntity<ProblemDetail> handleEntityNotFoundException(
      VprEntityNotFoundException ex) {
    log.error(ex.getMessage());
    ProblemDetail problemDetail = ProblemDetail.forStatus(ex.getStatus());
    problemDetail.setTitle(ex.getStatus().getReasonPhrase());
    problemDetail.setDetail(ex.getMessage());
    return ResponseEntity.status(ex.getStatus()).body(problemDetail);
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
