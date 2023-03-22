package ru.kirill.cuturl.encoder;

import com.google.common.hash.Hashing;

public class SHA256Encoder extends HashEncoder {

    public SHA256Encoder(byte tokenLength) {
        super(tokenLength, Hashing.sha256());
    }
}