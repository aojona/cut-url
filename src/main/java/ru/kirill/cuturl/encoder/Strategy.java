package ru.kirill.cuturl.encoder;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Strategy {

    BASE62(Base62Encoder.class);

    private final Class<? extends UrlEncoder> clazz;

    public UrlEncoder newInstance(byte tokenLength) {
        try {
            return clazz.getDeclaredConstructor(byte.class).newInstance(tokenLength);
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(clazz + " has no default constructor");
        }
    }
}