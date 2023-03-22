package ru.kirill.cuturl.encoder;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class UrlEncoder {

    protected final byte tokenLength;

    public abstract String encode(String url);
}