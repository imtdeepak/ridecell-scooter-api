package com.ridecell.scooter.exception;

import com.ridecell.scooter.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

/**
 * Created by deepak on 5/3/19.
 */
@ControllerAdvice
@ResponseBody
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(ConstraintViolationException.class)
    public final ResponseEntity<ErrorResponse> handleConstraintViolation(
            ConstraintViolationException ex,
            WebRequest request) {
        List<String> details = ex.getConstraintViolations()
                .parallelStream()
                .map(e -> e.getMessage())
                .collect(Collectors.toList());

        ErrorResponse error = new ErrorResponse(BAD_REQUEST, details);
        return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(JpaSystemException.class)
    public final ResponseEntity<ErrorResponse> handleReportingSQLException(JpaSystemException ex, WebRequest request) {
        ArrayList details = new ArrayList<String>();
        details.add(ex.getMessage());
        details.add(ex.getLocalizedMessage());

        ErrorResponse error = new ErrorResponse(INTERNAL_SERVER_ERROR, details);
        return new ResponseEntity<ErrorResponse>(error, INTERNAL_SERVER_ERROR);
    }
}