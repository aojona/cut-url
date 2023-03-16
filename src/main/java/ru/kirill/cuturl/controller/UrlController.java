package ru.kirill.cuturl.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kirill.cuturl.dto.UrlRequest;
import ru.kirill.cuturl.dto.UrlResponse;
import ru.kirill.cuturl.entity.Url;
import ru.kirill.cuturl.exception.ShortUrlNotCreatedException;
import ru.kirill.cuturl.service.UrlService;
import ru.kirill.cuturl.util.BindingResultUtil;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/url")
public class UrlController {

    private final UrlService urlService;

    @PostMapping
    public UrlResponse cutUrl(@RequestBody @Valid UrlRequest urlDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String errorMessage = BindingResultUtil.getErrorMessage(bindingResult);
            throw new ShortUrlNotCreatedException(errorMessage);
        }
        return urlService.cut(urlDTO);
    }

    @GetMapping("{token}")
    public ResponseEntity<Void> redirectByToken(@PathVariable String token) {
        Url url = urlService.findUrlByToken(token);
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .header(HttpHeaders.LOCATION, url.getOriginalUrl())
                .build();
    }
}
