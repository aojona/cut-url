package ru.kirill.cuturl.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

@Data
public class UrlRequest {

    @URL
    @NotBlank
    private String url;
}
