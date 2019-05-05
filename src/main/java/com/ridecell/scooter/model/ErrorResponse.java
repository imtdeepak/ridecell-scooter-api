package com.ridecell.scooter.model;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;

/**
 * Created by deepak on 5/5/19.
 */
@Data
public class ErrorResponse {

    private String errorMessage;
    private int errorId;

    public ErrorResponse(HttpStatus badRequest, List<String> details) {
        this.errorId = badRequest.value();
        this.errorMessage = details.toString();

    }
}
