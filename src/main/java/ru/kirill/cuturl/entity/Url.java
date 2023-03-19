package ru.kirill.cuturl.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@RedisHash(value = "url")
@TypeAlias("Url")
@Builder
public class Url implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    private String token;

    private String originalUrl;

    @TimeToLive
    private long timeToLive;

    private LocalDateTime createdAt;
}
