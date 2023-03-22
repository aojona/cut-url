package ru.kirill.cuturl.mapper;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.kirill.cuturl.config.RedisProperties;
import ru.kirill.cuturl.dto.UrlRequest;
import ru.kirill.cuturl.dto.UrlResponse;
import ru.kirill.cuturl.encoder.UrlEncoder;
import ru.kirill.cuturl.entity.Url;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
@RequiredArgsConstructor
public class UrlMapper implements Mapper<Url, UrlResponse, UrlRequest> {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private final RedisProperties redisProperties;
    private final UrlEncoder urlEncoder;
    private final HttpServletRequest request;

    @Override
    public UrlResponse mapToResponse(Url url) {

         return UrlResponse
                 .builder()
                 .shortUrl(getShortUrl(url))
                 .expireAt(getExpirationTime(url))
                 .build();
    }

    @Override
    public Url mapToEntity(UrlRequest urlRequest) {
        return Url
                .builder()
                .token(urlEncoder.encode(urlRequest.getUrl()))
                .originalUrl(urlRequest.getUrl())
                .timeToLive(getTimeToLive())
                .createdAt(LocalDateTime.now())
                .build();
    }

    private long getTimeToLive() {
        return redisProperties.timeUnit()
                .convert(redisProperties.timeToLive());
    }

    private String getShortUrl(Url url) {
        return request
                .getRequestURL()
                .append("/")
                .append(url.getToken())
                .toString();
    }

    private String getExpirationTime(Url url) {
        return url
                .getCreatedAt()
                .plusSeconds(url.getTimeToLive())
                .format(formatter);
    }
}