package ru.kirill.cuturl.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UrlResponse {

    private String shortUrl;
    private String expireAt;
}
