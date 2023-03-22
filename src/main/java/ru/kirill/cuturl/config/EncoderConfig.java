package ru.kirill.cuturl.config;

import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;
import ru.kirill.cuturl.encoder.Strategy;
import ru.kirill.cuturl.encoder.UrlEncoder;


@Configuration
@Validated
public class EncoderConfig {

    @Bean
    public UrlEncoder urlEncoder(@Value("${strategy.encoder}") Strategy strategy,
                                 @Value("${strategy.token-length}") @Min(value = 1) byte tokenLength) {
        return strategy.newInstance(tokenLength);
    }
}