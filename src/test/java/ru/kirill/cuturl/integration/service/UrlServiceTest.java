package ru.kirill.cuturl.integration.service;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import org.springframework.beans.factory.annotation.Autowired;
import ru.kirill.cuturl.dto.UrlRequest;
import ru.kirill.cuturl.dto.UrlResponse;
import ru.kirill.cuturl.entity.Url;
import ru.kirill.cuturl.integration.IntegrationTestBase;
import ru.kirill.cuturl.integration.util.TestDtoBuilder;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UrlServiceTest extends IntegrationTestBase {

    private final UrlService urlService;
    private final TestDtoBuilder testDtoBuilder;
    private static Url expectedUrl;

    @BeforeAll
    void init() {
        expectedUrl = testDtoBuilder.getUrlFromRedis();
    }

    @Test
    void shouldCutUrl() {
        UrlRequest validUrlRequest = testDtoBuilder.createValidUrlRequest();
        UrlResponse actualResponse = urlService.cut(validUrlRequest);
        assertThat(actualResponse).isNotNull();
    }

    @ParameterizedTest
    @NullSource
    @MethodSource("getUrlRequestArgumentsForTest")
    void shouldThrowExceptionWhenCutInvalidUrl(UrlRequest urlRequest) {
        assertThrows(Exception.class, () -> urlService.cut(urlRequest));
    }

    public Stream<Arguments> getUrlRequestArgumentsForTest() {
        return Stream.of(Arguments.of(new UrlRequest()));
    }

    @Test
    void shouldFindUrlByExistingToken() {
        String token = expectedUrl.getToken();
        Url actualUrl = urlService.findUrlByToken(token);
        assertThat(actualUrl).isEqualTo(expectedUrl);
    }

    @ParameterizedTest
    @NullAndEmptySource
    void shouldNotFindUrlByNotExistingToken(String token) {
        assertThrows(Exception.class, () -> urlService.findUrlByToken(token));
    }
}