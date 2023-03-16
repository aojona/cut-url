package ru.kirill.cuturl.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kirill.cuturl.dto.UrlRequest;
import ru.kirill.cuturl.dto.UrlResponse;
import ru.kirill.cuturl.mapper.UrlMapper;
import ru.kirill.cuturl.repository.UrlRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UrlService {

    private final UrlRepository urlRepository;
    private final UrlMapper urlMapper;

    public UrlResponse cut(UrlRequest urlDto) {
        return Optional.of(urlDto)
                .map(urlMapper::mapToEntity)
                .map(urlRepository::save)
                .map(urlMapper::mapToResponse)
                .orElseThrow();
    }
}
