package ru.kirill.cuturl.encoder;


import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import java.nio.charset.StandardCharsets;

public class MurMur3Encoder extends UrlEncoder{

    private final HashFunction hashFunction;

    public MurMur3Encoder(byte tokenLength) {
        super(tokenLength);
        this.hashFunction = tokenLength > 8 ? Hashing.murmur3_128() :Hashing.murmur3_32_fixed();
    }

    @Override
    public String encode(String url) {
        return hashFunction
                .hashString(url, StandardCharsets.UTF_8)
                .toString()
                .substring(0,tokenLength);
    }
}