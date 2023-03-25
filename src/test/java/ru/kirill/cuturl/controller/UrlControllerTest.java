package ru.kirill.cuturl.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.kirill.cuturl.dto.UrlRequest;
import ru.kirill.cuturl.IntegrationTestBase;
import ru.kirill.cuturl.util.TestDtoBuilder;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
class UrlControllerTest extends IntegrationTestBase {

    private static final String ENDPOINT = "/api/url";

    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;
    private final TestDtoBuilder testDtoBuilder;

    @Test
    @SneakyThrows
    void shouldCutUrlForValidRequest() {
        UrlRequest urlRequest = testDtoBuilder.createValidUrlRequest();
        String requestBody = objectMapper.writeValueAsString(urlRequest);
        mockMvc.perform(post(ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk());
    }

    @Test
    @SneakyThrows
    void shouldNotCutUrlForInvalidRequest() {
        UrlRequest urlRequest = testDtoBuilder.createInvalidUrlRequest();
        String requestBody = objectMapper.writeValueAsString(urlRequest);
        mockMvc.perform(post(ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isBadRequest());
    }

    @Test
    @SneakyThrows
    void shouldRedirectByTokenFromRedis() {
        String token = testDtoBuilder.getUrlFromRedis().getToken();
        mockMvc.perform(get(ENDPOINT + "/" + token))
                .andExpect(status().isFound());
    }

    @Test
    @SneakyThrows
    void shouldNotRedirectByNonExistentToken() {
        String token = testDtoBuilder.getNonExistentToken();
        mockMvc.perform(get(ENDPOINT + "/" + token))
                .andExpect(status().isBadRequest());
    }
}