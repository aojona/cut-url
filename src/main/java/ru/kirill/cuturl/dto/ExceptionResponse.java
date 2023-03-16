package ru.kirill.cuturl.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExceptionResponse {

    private String message;
    private String path;
}
