package ru.kirill.cuturl.dto;

import lombok.Builder;
import lombok.Data;
import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
public class UrlResponse implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String shortUrl;

    private String expireAt;
}
