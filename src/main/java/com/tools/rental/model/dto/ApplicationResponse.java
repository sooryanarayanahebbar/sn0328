package com.tools.rental.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ApplicationResponse {
    private String message;
    private String code;
    private String path;
    private Object apiResponse;
    private List<String> errors;
}
