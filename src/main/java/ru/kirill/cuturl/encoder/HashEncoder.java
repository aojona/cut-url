package ru.kirill.cuturl.encoder;

import com.google.common.hash.HashFunction;
import java.nio.charset.StandardCharsets;

public class HashEncoder extends UrlEncoder {

    private final HashFunction hashFunction;

    public HashEncoder(byte tokenLength, HashFunction hashFunction) {
        super(tokenLength);
        this.hashFunction = hashFunction;
    }

    @Override
    public String encode(String url) {
        return hashFunction
                .hashString(url, StandardCharsets.UTF_8)
                .toString()
                .substring(0,tokenLength);
    }
}
