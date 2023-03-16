package ru.kirill.cuturl.config;

import jakarta.validation.constraints.Positive;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Validated
@ConfigurationProperties(prefix = "redis")
public record RedisProperties(@Positive long timeToLive) {
}