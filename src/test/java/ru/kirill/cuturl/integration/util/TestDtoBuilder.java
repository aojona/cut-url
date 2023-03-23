package ru.kirill.cuturl.integration.util;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kirill.cuturl.dto.UrlRequest;
import ru.kirill.cuturl.entity.Url;
import ru.kirill.cuturl.repository.UrlRepository;

import java.time.LocalDateTime;


@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TestDtoBuilder {

    private static final String URL = "https://github.com/misotoy";
    private final UrlRepository urlRepository;

    public UrlRequest createValidUrlRequest() {
        return new UrlRequest(URL);
    }

    public UrlRequest createInvalidUrlRequest() {
        return new UrlRequest("dummy");
    }

    public Url getUrlFromRedis() {
        Url url = createUrl();
        return urlRepository.save(url);
    }

    private static Url createUrl() {
        return Url.builder()
                .originalUrl(URL)
                .timeToLive(100)
                .createdAt(LocalDateTime.now())
                .build();
    }

    public String getNonExistentToken() {
        return "dummy";
    }

}
