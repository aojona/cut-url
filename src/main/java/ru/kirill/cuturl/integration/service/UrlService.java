package ru.kirill.cuturl.integration.service;

import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import ru.kirill.cuturl.dto.UrlRequest;
import ru.kirill.cuturl.dto.UrlResponse;
import ru.kirill.cuturl.entity.Url;
import ru.kirill.cuturl.exception.UrlNotFoundException;
import ru.kirill.cuturl.mapper.UrlMapper;
import ru.kirill.cuturl.repository.UrlRepository;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UrlService {

    private final UrlRepository urlRepository;
    private final UrlMapper urlMapper;

    @Cacheable(cacheNames = "url", key = "#urlDto.url")
    public UrlResponse cut(UrlRequest urlDto) {
        return Optional.of(urlDto)
                .map(urlMapper::mapToEntity)
                .map(urlRepository::save)
                .map(urlMapper::mapToResponse)
                .orElseThrow();
    }

    @Cacheable(cacheNames = "token", key = "#token")
    public Url findUrlByToken(String token) {
        return urlRepository
                .findById(token)
                .orElseThrow(() -> new UrlNotFoundException("url not found"));
    }
}
