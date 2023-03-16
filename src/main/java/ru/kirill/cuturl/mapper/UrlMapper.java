package ru.kirill.cuturl.mapper;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.kirill.cuturl.config.RedisProperties;
import ru.kirill.cuturl.dto.UrlRequest;
import ru.kirill.cuturl.dto.UrlResponse;
import ru.kirill.cuturl.entity.Url;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UrlMapper implements Mapper<Url, UrlResponse, UrlRequest> {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private final RedisProperties redisProperties;
    private final HttpServletRequest request;

    @Override
    public UrlResponse mapToResponse(Url url) {

         return UrlResponse
                 .builder()
                 .shortUrl(getShortUrl(url))
                 .expireAt(getExpireAt(url))
                 .build();
    }

    @Override
    public Url mapToEntity(UrlRequest urlRequest) {
        return Url
                .builder()
                .token(UUID.randomUUID().toString())
                .originalUrl(urlRequest.getUrl())
                .timeToLive(redisProperties.timeToLive())
                .createdAt(LocalDateTime.now())
                .build();
    }

    private String getShortUrl(Url url) {
        return request
                .getRequestURL()
                .append("/")
                .append(url.getToken())
                .toString();
    }

    private String getExpireAt(Url url) {
        return url
                .getCreatedAt()
                .plusSeconds(url.getTimeToLive())
                .format(formatter);
    }
}
